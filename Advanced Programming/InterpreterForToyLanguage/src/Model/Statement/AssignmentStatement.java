package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.ADT.IADTList;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

public class AssignmentStatement implements IStatement {
    String variableName;
    IExpression expression;

    public AssignmentStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTDictionary<String, IValue> symbolTable = programState.getSymbolTable();
        if (!symbolTable.isKeyDefined(this.variableName)) {
            throw new Exception("Assignment statement failed. Variable " + this.variableName + " was not declared.");
        }
        IValue variabeNewValue = this.expression.evaluate(symbolTable, programState.getHeap());
        IType variableType = symbolTable.getValue(this.variableName).getType();
        if (!variabeNewValue.getType().equals(variableType)) {
            throw new Exception("Assignment statement failed. Invalid attempt to assign a " + variabeNewValue.getType()
                    + " to a " + variableType + ".");
        }
        symbolTable.put(this.variableName, variabeNewValue);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression);
    }

    @Override
    public String toString() {
        return new String(this.variableName + " = " + this.expression.toString() + ";");
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType typeVariable = typeCheckerTable.getValue(variableName);
        IType typeExpression = expression.checkTypes(typeCheckerTable);

        if (!typeVariable.equals(typeExpression)) {
            throw new Exception("Assignment statement:  " + this.toString() + " can only operate with variables & expressions of same type.");
        }

        return typeCheckerTable;
    }

}
