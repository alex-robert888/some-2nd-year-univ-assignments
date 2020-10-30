package Model.Statement;

import Model.ProgramState;

public class NoOperationStatement implements IStatement{
    public NoOperationStatement() {

    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return new String("");
    }
}
