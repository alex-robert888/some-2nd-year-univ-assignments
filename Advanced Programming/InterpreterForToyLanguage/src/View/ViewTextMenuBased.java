package View;

import View.Command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewTextMenuBased {
    private Map<String, Command> commandsMap;

    public ViewTextMenuBased() {
        commandsMap = new HashMap<>();
    }

    public void addCommand(Command command) {
        this.commandsMap.put(command.getKey(), command);
    }

    public void printMenu() {
        System.out.println("\n----- MAIN MENU -----\n");
        for (Command command : commandsMap.values()) {
            System.out.printf("%s : %s%n", command.getKey(), command.getDescription());
        }
    }

    public void show () {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            this.printMenu();
            System.out.println("Enter option: ");
            String key = scanner.nextLine();
            Command inputCommand = this.commandsMap.get(key);
            if (inputCommand == null) {
                System.out.println("Invalid option.");
                continue;
            }
            inputCommand.execute();
        }
    }
}
