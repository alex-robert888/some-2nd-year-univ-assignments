package Repository;

import Model.ProgramState;

import java.util.ArrayList;

public interface IRepository_Interpreter {
    ProgramState getCurrentProgramState();
    void addProgramState(ProgramState newProgramState);
}
