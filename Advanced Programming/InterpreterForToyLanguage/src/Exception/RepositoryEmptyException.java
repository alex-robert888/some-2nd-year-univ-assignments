package Exception;

class IncorrectFileExtensionException extends RuntimeException {
    public IncorrectFileExtensionException(String errorMessage, Throwable err) {
        super("Attempt to get current program state from empty repository", err);
    }
}