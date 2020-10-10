package View;


import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import Controller.ControllerTrees;
import Model.Command;

public class ViewTrees {
    ControllerTrees controllerTrees;
    boolean viewRunning = true;

    public ViewTrees(ControllerTrees controllerTrees) {
        this.controllerTrees = controllerTrees;
    }

    public void run() {
        while (viewRunning) {
            Scanner scannerCommand = new Scanner(System.in);
            String rawCommand = scannerCommand.nextLine();
            try {
                Command processedCommand = new Command(rawCommand);
                this.executeCommand(processedCommand);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void executeCommand(Command processedCommand) throws Exception{
        String[] commandParameters = processedCommand.getParameters();
        switch (processedCommand.getName()) {
            case "exit" -> this.viewRunning = false;
            case "add" -> this.controllerTrees.addTree(commandParameters[0], Integer.parseInt(commandParameters[1]));
            case "remove" -> this.controllerTrees.removeTree(commandParameters[0], Integer.parseInt(commandParameters[1]));
            case "filter" -> {
                ArrayList<String> filteredTrees = this.controllerTrees.filterTrees(Integer.parseInt(commandParameters[0]));
                for (String tree : filteredTrees) {
                    System.out.println(tree);
                }
            }
        }
    }
}
