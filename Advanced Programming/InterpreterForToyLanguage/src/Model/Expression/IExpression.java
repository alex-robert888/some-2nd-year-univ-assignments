package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.IType;
import Model.Value.IValue;

public interface IExpression {
    IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception;
    String toString();
    IType checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception;
}
