package Model;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.ADT.IADTList;
import Model.ADT.IADTStack;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;

public class ProgramState {
    private final IADTStack<IStatement> executionStack;
    private final IADTDictionary<String, IValue> symbolTable;
    private final IADTList<String> outputList;
    private final IADTDictionary<StringValue, BufferedReader> fileTable;
    private final IADTDictionaryForHeap heap;
    private final IStatement originalProgram;
    private int nonstaticId = 1;
    public static int staticId;

    public ProgramState(IADTStack<IStatement> executionStack, IADTDictionary<String, IValue> symbolTable,
                        IADTList<String> outputList, IADTDictionary<StringValue, BufferedReader> fileTable,
                        IADTDictionaryForHeap heap, IStatement originalProgram)
    {
        this.executionStack = executionStack;
        this.executionStack.push(originalProgram);

        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram.deepCopy();
        this.setProgramID();
    }

    public synchronized void setProgramID() {
        this.nonstaticId = staticId++;
    }

    public IADTStack<IStatement> getExecutionStack() {
        return this.executionStack;
    }

    public IADTDictionary<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }

    public IADTList<String> getOutputList() {
        return this.outputList;
    }

    public IADTDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IADTDictionaryForHeap getHeap() {
        return this.heap;
    }

    public int getId() { return this.nonstaticId; }

    public String toString() {
        return "----- Program State -----\n== ID:" + this.nonstaticId + "\n== Execution stack: \n" + this.executionStack + "\n== Symbol Table: \n"
                + this.symbolTable.toString() + "\n== Output: \n" + this.outputList.toString() + "\n== File Table: \n" +
                this.fileTable.toString() + "\n== Heap: \n" + this.heap.toString();
    }

    public boolean isNotCompleted() {
        return !(this.executionStack.isEmpty());
    }

    public ProgramState runOneStep() throws Exception {
        if(this.executionStack.isEmpty()) {
            throw new Exception("Execution stack is empty");
        }
        IStatement currentStatement = this.executionStack.pop();
        return currentStatement.execute(this);
        //this.repositoryMemoryBased_interpreter.updateProgramState(programState);
        //System.out.println(newProgramState.toString());; // MAYBE HERE AND PRECEDING LINE ARE NOT CORRECT
    }

}
