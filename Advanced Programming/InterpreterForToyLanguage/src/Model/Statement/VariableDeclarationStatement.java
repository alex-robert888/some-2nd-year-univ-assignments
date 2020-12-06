package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ProgramState;
import Model.Type.*;
import Model.Value.*;

public class VariableDeclarationStatement implements IStatement{
    String variableName;
    IType type;

    public VariableDeclarationStatement(String variableName, IType type) {
        this.variableName = variableName;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTDictionary<String, IValue> symbolTable = programState.getSymbolTable();
        if (symbolTable.isKeyDefined(this.variableName)) {
            throw new Exception("Variable declaration statement failed. Variable " + this.variableName + " is already declared.");
        }

        if (this.type.equals(new IntType())) {
            symbolTable.put(this.variableName, new IntValue(0));
        }
        else if (this.type.equals(new BoolType())) {
            symbolTable.put(this.variableName, new BoolValue(false));
        }
        else if (this.type.equals(new StringType())) {
            symbolTable.put(this.variableName, new StringValue(""));
        }
        else if (this.type.equals(new RefType(new IntType()))) {
            symbolTable.put(this.variableName, new RefValue(0, new IntType())); // null address and of ref type int
        }
        else if (this.type.equals(new RefType(new RefType(new IntType())))) {
            symbolTable.put(this.variableName, new RefValue(0, new RefType(new IntType()))); // null address and of ref type int
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(this.variableName, this.type);
    }

    @Override
    public String toString() {
        return new String(this.type.toString() + " " + this.variableName + ";");
    }
}
