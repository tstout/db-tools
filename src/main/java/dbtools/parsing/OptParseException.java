package dbtools.parsing;

public class OptParseException extends RuntimeException {
    public OptParseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
