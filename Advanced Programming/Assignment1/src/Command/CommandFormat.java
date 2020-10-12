package Command;

public class CommandFormat {
    private final String name;
    private final int numberOfParameters;

    CommandFormat(String name, int numberOfParameters) {
        this.name = name;
        this.numberOfParameters = numberOfParameters;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfParameters() {
        return this.numberOfParameters;
    }
}
