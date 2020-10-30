package View.Command;

public interface ICommand {
    boolean validateSelf();
    void executeSelf();
}
