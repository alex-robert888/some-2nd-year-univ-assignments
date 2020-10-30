package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.Value.IValue;

public class ValueExpression implements IExpression {
    IValue value;

    ValueExpression(IValue value) {
        this.value = value;
    }
    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable) throws Exception {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
