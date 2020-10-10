package Model;


import java.util.Arrays;

public class Command {
    String rawFormat;
    String name;
    String[] parameters;
    int length;
    final CommandFormat[] validCommandFormats;

    public Command(String rawFormat) throws Exception {
        this.rawFormat = rawFormat;
        this.name = "";
        this.parameters = null;
        this.length = 0;
        this.validCommandFormats = new CommandFormat[] {
            new CommandFormat("exit", 0),
            new CommandFormat("add", 2),
            new CommandFormat("remove", 2),
            new CommandFormat("filter", 1)
        };

        this.processCommand();
    }

    private void processCommand() throws Exception {
        String[] splitCommand = this.rawFormat.split("\\s+");
        if (!this.isValid(splitCommand)) {
            throw new Exception("Invalid command!");
        }
        this.name = splitCommand[0];
        if (splitCommand.length == 1)   return;
        this.parameters = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
    }

    public String getName() {
        return this.name;
    }

    public String[] getParameters() {
        if (parameters == null) {
            return new String[] { "" };
        }
        return this.parameters.clone();
    }

    public int getLength() {
        return this.length;
    }

    private boolean isValid(String[] splitCommand) {
        for (CommandFormat commandFormat : this.validCommandFormats) {
            if (splitCommand[0].equals(commandFormat.getName()) && splitCommand.length - 1 == commandFormat.getNumberOfParameters()) {
                return true;
            }
        }
        return false;
    }
}
