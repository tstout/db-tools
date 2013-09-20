package dbtools.cmdopts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface OptHandler {
    void run(CmdOpt opt);

    public class Base implements OptHandler {

        public void run(CmdOpt opt) {

        }
    }


    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Command {
        String value();
    }
}
