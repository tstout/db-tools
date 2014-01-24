package db.io.operations;

import com.google.common.base.Function;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.CaseFormat.*;
import static com.google.common.base.Throwables.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;
import static com.google.common.reflect.Reflection.*;

class ValueFactory<T> {
    private final Class<T> interfaceType;
    private final Handler handler;
    private final SetView<String> methodNames;

    ValueFactory(Class<T> interfaceType, DataSet dataSet) {
        this.interfaceType = interfaceType;
        this.methodNames = methodNames(dataSet, interfaceType);
        this.handler = new Handler(interfaceType, dataSet, methodNames);
    }

    // TODO -  this is the wrong API, rethink this...

    T newRow(int row, DataSet dataSet) {
        return newProxy(interfaceType, new Handler(interfaceType, dataSet, methodNames));
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
    }

    static class Handler implements InvocationHandler {
        private Map<Method, Object> values = newHashMap();
        private final static Function<String, String> mToCol = Fn.mToCol();

        // TODO - memoize the method name intersection by interfaceType

        Handler(Class<?> interfaceType, DataSet dataSet, Set<String> methodNames) {

            for (String mName : methodNames) {
                try {
                    Column col = dataSet.get(0, mToCol.apply(mName));
                    values.put(interfaceType.getMethod(mName), col.val(col.type()));
                } catch (NoSuchMethodException e) {
                    throw propagate(e);
                }
            }
        }

        @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return values.get(method);
        }
    }
}
