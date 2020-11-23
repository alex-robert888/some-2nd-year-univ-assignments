package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class NewStatement implements IStatement {

    String variableName;
    IExpression expression;

    public NewStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTDictionary<String, IValue> symbolTable = programState.getSymbolTable();

        // Check if variable exists
        IValue variableValue = symbolTable.getValue(this.variableName);
        if (variableValue == null) {
            throw new Exception("New  statement failed. Variable " + this.variableName + " was not declared.");
        }

        // Check if variable is of RefType
        if (!(variableValue.getType() instanceof RefType)) {
            throw new Exception("New  statement failed. Variable " + this.variableName + " is not of RefType.");
        }

        // Check if expression value is of same type as the location type of the variable value
        IValue expressionValue = this.expression.evaluate(symbolTable, programState.getHeap());
        if (!expressionValue.getType().equals(((RefValue)variableValue).getLocationType())) {
            throw new Exception("New  statement failed. Expression " + this.expression.toString() + " is not of type " + variableValue.getType());
        }

        // Add ref to heap and get its address
        IADTDictionaryForHeap heap = programState.getHeap();
        int key = heap.put(expressionValue);

        // Update in the SymbolTable the variable with the address and type
        symbolTable.put(this.variableName, new RefValue(key, expressionValue.getType()));

        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(this.variableName, this.expression);
    }

    @Override
    public String toString () {
        return String.format("new(%s, %s)", this.variableName, this.expression.toString());
    }
}
