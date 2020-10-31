package Controller;

import Model.ProgramState;
import Model.Statement.IStatement;
import Repository.RepositoryMemoryBased_Interpreter;

import java.beans.Expression;

public class Controller_Interpreter {
    RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter;

    public Controller_Interpreter(RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter) {
        this.repositoryMemoryBased_interpreter = repositoryMemoryBased_interpreter;
    }

    public void addProgramStateToRepository(ProgramState programState) {
        this.repositoryMemoryBased_interpreter.addProgramState(programState);
    }

    public ProgramState runOneStep(String[] splitRawCommand) throws Exception {
        ProgramState programState = this.repositoryMemoryBased_interpreter.getCurrentProgramState();
        var executionStack= programState.getExecutionStack();
        if(executionStack.isEmpty()) {
            throw new Exception("Execution stack is empty");
        }
        IStatement currentStatement = executionStack.pop();
        programState = currentStatement.execute(programState);
        //this.repositoryMemoryBased_interpreter.updateProgramState(programState);
        System.out.println(programState.toString());
        return programState;
    }

    public ProgramState runAllStep(String[] splitRawCommand) throws Exception {
        ProgramState programState = this.repositoryMemoryBased_interpreter.getCurrentProgramState();
        var executionStack= programState.getExecutionStack();
        System.out.println(programState.toString());
        while(!executionStack.isEmpty()) {
            IStatement currentStatement = executionStack.pop();
            programState = currentStatement.execute(programState);
            System.out.println(programState.toString());
        }
        return programState;
    }
}
