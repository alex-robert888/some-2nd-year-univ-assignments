package View.Command;

import Controller.Controller_Interpreter;

public class RunAllStepsCommand extends Command {
    private Controller_Interpreter controller;

    public RunAllStepsCommand(String key, String description, Controller_Interpreter controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            this.controller.runAllStep();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
