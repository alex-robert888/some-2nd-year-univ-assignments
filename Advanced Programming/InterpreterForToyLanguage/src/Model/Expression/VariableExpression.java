package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.Value.IValue;

public class VariableExpression implements IExpression {
    String variableName;

    VariableExpression(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable) throws Exception {
        IValue value = symbolTable.getValue(variableName);
        if (value == null) {
            throw new Exception("Variable with name " + variableName + " was not declared.");
        }
        return value;
    }

    @Override
    public String toString() {
        return this.variableName;
    }
}
