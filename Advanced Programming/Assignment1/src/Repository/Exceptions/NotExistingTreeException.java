package Repository.Exceptions;

public class NotExistingTreeException extends Exception {
    public NotExistingTreeException(String errorMessage) {
        super(errorMessage);
    }
}