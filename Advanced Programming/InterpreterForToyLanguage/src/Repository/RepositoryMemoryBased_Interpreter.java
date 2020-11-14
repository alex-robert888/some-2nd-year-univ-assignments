package Repository;

import Model.ProgramState;

import java.util.ArrayList;

public class RepositoryMemoryBased_Interpreter implements IRepository_Interpreter{
    ArrayList<ProgramState> programStates = new ArrayList<>();
    ProgramState programState;
    String logFile;

    public RepositoryMemoryBased_Interpreter(ProgramState programState, String logFile) {
        this.programState = programState;
        this.logFile = logFile;
        this.programStates.add(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() throws RuntimeException {
        if (this.programStates.isEmpty()) {
            throw new RuntimeException();
        }
        return this.programStates.get(this.programStates.size() - 1);
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        this.programStates.add(newProgramState);
        System.out.println("Program state loaded into repository");
    }
}
