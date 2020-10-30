package Model;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTList;
import Model.ADT.IADTStack;
import Model.Statement.IStatement;
import Model.Value.IValue;

public class ProgramState {
    IADTStack<IStatement> executionStack;
    IADTDictionary<String, IValue> symbolTable;
    IADTList<String> outputList;
    IStatement originalProgram;

    public ProgramState(IADTStack<IStatement> executionStack, IADTDictionary<String, IValue> symbolTable,
                        IADTList<String> outputList, IStatement originalProgram)
    {
        this.executionStack = executionStack;
        this.executionStack.push(originalProgram);
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.originalProgram = originalProgram.deepCopy();
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

}
