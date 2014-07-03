package dbtools.cmdopts;

import com.google.common.base.Predicate;

import java.util.Map;

import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.Maps.*;

public class CmdEnv {
    private Map<Key, Object> env = newHashMap();

    public <T> T get(Key key, Class<T> type) {
        return type.cast(env.get(key));
    }

    public CmdEnv put(Key key, Object value) {
        env.put(key, value);
        return this;
    }

    public enum Key {

        SCHEMA(String.class) {
            @Override public Predicate<CmdOpt> match() {
                return Fn.schemaOpt("schema");
            }
        },
        SCHEMA_FILE(String.class) {
            @Override public Predicate<CmdOpt> match() {
                return Fn.schemaOpt("schemaFile");
            }
        };

        public final Class valueClass;

        private Key(Class valueClass) {
            this.valueClass = valueClass;
        }

//        public <T> T valueFrom(Cmd cmd, Class<T> clazz) {
//            return from(cmd.options())
//                    .firstMatch(match())
//                    .get()
//                    .value()
//                    .get();
//        }

        public Object valueFrom(Cmd cmd) {
            return from(cmd.options())
                    .firstMatch(match())
                    .get()
                    .value()
                    .get();
        }

        public abstract Predicate<CmdOpt> match();

        private static class Fn {
            static Predicate<CmdOpt> schemaOpt(final String name) {
                return new Predicate<CmdOpt>() {
                    public boolean apply(CmdOpt input) {
                        return input.name().equals(name);
                    }
                };
            }
        }
    }
}
