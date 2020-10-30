package Model.Statement;

import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState programState) throws Exception;
    IStatement deepCopy();
    String toString();
}
