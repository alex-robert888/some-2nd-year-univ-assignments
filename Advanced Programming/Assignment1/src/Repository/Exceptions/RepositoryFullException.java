package Repository.Exceptions;

public class RepositoryFullException extends Exception {
    public RepositoryFullException(String errorMessage) {
        super(errorMessage);
    }
}