package View.Command;

import Controller.Controller_Interpreter;

public class RunOneStepCommand extends Command{
    Controller_Interpreter controller;

    public RunOneStepCommand(String key, String description, Controller_Interpreter controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.runOneStep();
        }
        catch (Exception exception) {
            // System.out.println(exception);
            exception.printStackTrace(new java.io.PrintStream(System.out));
        }
    }
}
