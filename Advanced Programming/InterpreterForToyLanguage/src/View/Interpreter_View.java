package View;

import View.Command.ICommand;

import java.util.Scanner;

public class Interpreter_View {
    ViewState viewState;

    public Interpreter_View() {
        this.viewState = ViewState.RUNNING;
    }

    public void run() {
        System.out.println("View initiated. Program is ready to use. \nType 'help' for list of commands");
        int exitStatus = this.runEventLoop();
        System.out.println("Program terminated with exit code " + exitStatus);
    }

    private int runEventLoop() {
        while(this.viewState == ViewState.RUNNING) {
            Scanner scannerCommand = new Scanner(System.in);
            String rawCommand = scannerCommand.nextLine();
            int commandExitStatus = this.executeCommand(rawCommand);
            if (commandExitStatus != 0) {
                return commandExitStatus;
            }
        }
        return 0;
    }

    private int executeCommand(String rawCommand) {
         String[] splitRawCommand = rawCommand.split("\\s+");
         ICommand processedCommand = null;
         switch (splitRawCommand[0]) {
             case "quit" -> this.viewState = ViewState.QUIT;
             case "run" -> processedCommand = new CommandRun(splitRawCommand);
         }
         if (processedCommand == null) {
             return -1;
         }
         return 0;
    }
}
