package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.Value.IValue;

public interface IExpression {
    IValue evaluate(IADTDictionary<String, IValue> symbolTable) throws Exception;
    String toString();
}
