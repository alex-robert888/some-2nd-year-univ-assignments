package Model;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTList;
import Model.ADT.IADTStack;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.util.Iterator;

public class ProgramState {
    IADTStack<IStatement> executionStack;
    IADTDictionary<String, IValue> symbolTable;
    IADTList<String> outputList;
    IADTDictionary<StringValue, BufferedReader> fileTable;
    IStatement originalProgram;

    public ProgramState(IADTStack<IStatement> executionStack, IADTDictionary<String, IValue> symbolTable,
                        IADTList<String> outputList, IADTDictionary<StringValue, BufferedReader> fileTable, IStatement originalProgram)
    {
        this.executionStack = executionStack;
        this.executionStack.push(originalProgram);
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram.deepCopy();
    }

    public IADTStack<IStatement> getExecutionStack() {
        return this.executionStack;
    }  /// CHANGE TO DEEP COPY!

    public IADTDictionary<String, IValue> getSymbolTable() {
        return this.symbolTable;
    }  /// CHANGE TO DEEP COPY & STRING VALUE!

    public IADTList<String> getOutputList() {
        return this.outputList;
    } /// CHANGE TO DEEP COPY!

    public IADTDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    } /// CHANGE TO DEEP COPY!

    public String toString() {
        return "----- Program State -----\n== Execution stack: \n" + this.executionStack + "\n== Symbol Table: \n"
                + this.symbolTable.toString() + "\n== Output: \n" + this.outputList.toString() + "\n";
    }
}
