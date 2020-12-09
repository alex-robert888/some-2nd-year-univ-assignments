package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.IType;
import Model.Value.IValue;

public class VariableExpression implements IExpression {
    String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception {
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

    @Override
    public IType checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        return typeCheckerTable.getValue(this.variableName);
    }
}
