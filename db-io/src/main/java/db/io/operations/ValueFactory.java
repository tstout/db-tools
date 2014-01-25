package db.io.operations;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.CaseFormat.*;
import static com.google.common.base.Throwables.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;
import static com.google.common.reflect.Reflection.*;

class ValueFactory<T> {
    private final Class<T> interfaceType;
    private final Set<String> methodNames;

    ValueFactory(Class<T> interfaceType, DataSet dataSet) {
        this.interfaceType = interfaceType;
        this.methodNames = methodNames(dataSet, interfaceType);
    }

    Collection<T> create(final DataSet dataSet) {
        final List<T> rows = newArrayList();

        dataSet.each(new DataSet.Action() {
            public void exec(Collection<Column> row) {
                rows.add(newProxy(interfaceType,
                        new Handler(interfaceType, row, methodNames)));
            }
        });

        return rows;
    }

    private SetView<String> methodNames(DataSet dataSet, Class<?> interfaceType) {
        return intersection(methodNamesFromDataSet(dataSet), methodNamesFromInterface(interfaceType));
    }

    private Set<String> methodNamesFromDataSet(DataSet dataSet) {
        return newHashSet(
                from(dataSet.columnNames())
                        .transform(Fn.dbColToMethod()));
    }

    private Set<String> methodNamesFromInterface(Class<?> interfaceType) {
        return newHashSet(
                from(Arrays.asList(interfaceType.getMethods()))
                        .transform(Fn.toMethodName()));
    }

    static class Fn {

        static Function<Method, String> toMethodName() {
            return new Function<Method, String>() {
                public String apply(Method input) {
                    return input.getName();
                }
            };
        }

        static Function<String, String> dbColToMethod() {
            return new Function<String, String>() {
                public String apply(String input) {
                    return LOWER_UNDERSCORE.to(LOWER_CAMEL, input.toLowerCase());
                }
            };
        }

        static Function<String, String> mToCol() {
            return new Function<String, String>() {
                public String apply(String input) {
                    return LOWER_CAMEL.to(LOWER_UNDERSCORE, input.toLowerCase());
                }
            };
        }

        static Predicate<Column> methodNameMatches(final Method method) {
            return new Predicate<Column>() {
                public boolean apply(Column input) {
                    return input.name()
                            .toLowerCase()
                            .equals(dbColToMethod()
                                    .apply(method.getName()));
                }
            };
        }

        static Function<String, Method> intfToMethods(final Class<?> type) {
            return new Function<String, Method>() {
                public Method apply(String input) {
                    try {
                        return type.getMethod(input);
                    } catch (NoSuchMethodException e) {
                        throw propagate(e);
                    }
                }
            };
        }
    }

    static class Handler implements InvocationHandler {
        private Map<Method, Object> values = newHashMap();
        private final static Function<String, String> mToCol = Fn.mToCol();

        // TODO - memoize the method name intersection by interfaceType

        Handler(Class<?> interfaceType, Collection<Column> row, Set<String> methodNames) {
            for (Method m : from(methodNames).transform(Fn.intfToMethods(interfaceType))) {
                Column col = from(row).firstMatch(Fn.methodNameMatches(m)).get();
                values.put(m, col.val(col.type()));
            }
        }

        @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return values.get(method);
        }
    }
}
