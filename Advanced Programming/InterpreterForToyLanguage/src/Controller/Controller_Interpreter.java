package Controller;

import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.RepositoryMemoryBased_Interpreter;

import java.beans.Expression;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller_Interpreter {
    RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter;

    public Controller_Interpreter(RepositoryMemoryBased_Interpreter repositoryMemoryBased_interpreter) {
        this.repositoryMemoryBased_interpreter = repositoryMemoryBased_interpreter;
    }

    Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,IValue> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public ProgramState runOneStep() throws Exception {
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

    public ProgramState runAllStep() throws Exception {
        ProgramState programState = this.repositoryMemoryBased_interpreter.getCurrentProgramState();
        var executionStack= programState.getExecutionStack();
        System.out.println(programState.toString());
        this.repositoryMemoryBased_interpreter.logProgramState();
        while(!executionStack.isEmpty()) {
            IStatement currentStatement = executionStack.pop();
            programState = currentStatement.execute(programState);
            this.repositoryMemoryBased_interpreter.logProgramState();
            System.out.println(programState.toString());
        }
        return programState;
    }
}
