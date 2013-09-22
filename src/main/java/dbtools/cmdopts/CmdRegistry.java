package dbtools.cmdopts;

import com.google.common.base.Functions;

import java.lang.reflect.Method;
import java.util.Map;

import static com.google.common.base.Throwables.*;
import static com.google.common.collect.Maps.*;
import static java.lang.String.*;

/**
 *
 */
public class CmdRegistry {
    private Map<String, MethodPair> commandMethods = newHashMap();

    private class MethodPair {
        Object instance;
        Method method;
    }

    public CmdRegistry(Object... handlers) {

        for (final Object handler : handlers) {
            for (final Method m : handler.getClass().getDeclaredMethods()) {
                Command cmd = m.getAnnotation(Command.class);

                if (cmd != null) {
                    commandMethods.put(cmd.value(), new MethodPair() {{
                            instance = handler;
                            method = m;
                        }});
                }
            }
        }
    }

    public void processCommand(Cmd cmd) {
        MethodPair cmdHandler = Functions.forMap(commandMethods, defaultMethod())
                .apply(cmd.name());

        try {
            cmdHandler.method.invoke(cmdHandler.instance, cmd);
        } catch (Exception e) {
            throw propagate(getRootCause(e));
        }
    }

    private void invalidCmd(Cmd cmd) {
        throw new IllegalArgumentException(format("Unrecognized option %s", cmd.name()));
    }

    private MethodPair defaultMethod() {
        try {
            final Object thiz = this;
            final Method m = getClass().getDeclaredMethod("invalidCmd", new Class[]{Cmd.class});
            m.setAccessible(true);

            return new MethodPair() {{
                instance = thiz;
                method = m;
            }};

        } catch (NoSuchMethodException e) {
            throw propagate(e);
        }
    }
}
