package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

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

        return programState;
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
