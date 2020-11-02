package Exception;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String errorMessage, Throwable err) {
        super("Attempt to get current program state from empty repository", err);
    }
}