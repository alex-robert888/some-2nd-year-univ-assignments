package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RepositoryMemoryBased_Interpreter implements IRepository_Interpreter{
    ArrayList<ProgramState> programStates = new ArrayList<>();
    PrintWriter printWriter;
    String logFile;

    public RepositoryMemoryBased_Interpreter(ProgramState programState, String logFile) throws IOException {
        // this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
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

    @Override
    public void logProgramState() throws Exception {
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.logFile, true)));
        printWriter.println(this.programStates.get(this.programStates.size() - 1).toString());
        printWriter.close();
    }
}
