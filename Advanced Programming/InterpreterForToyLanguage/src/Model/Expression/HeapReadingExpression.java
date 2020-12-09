package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class HeapReadingExpression implements IExpression {

    IExpression expression;
    public HeapReadingExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception {
        // Check if expression value is of type RefValue
        IValue expressionValue = this.expression.evaluate(symbolTable, heap);
        if (!(expressionValue instanceof RefValue)) {
            throw new Exception("Expression: " + this.toString() + "failed because the argument was not evaluated to a RefValue()");
        }

        // Get the address of the argument expression and use it to access the heap and get the value
        int argumentAddress = ((RefValue) expressionValue).getAddress();
        IValue argumentValue = heap.getValue(argumentAddress);
        if (argumentValue == null) {
            throw new Exception("Heap reading expression failed. There is no address like that in the heap.");
        }

        return argumentValue;
    }

    public String toString() {
        return String.format("rh(%s)", this.expression.toString());
    }

    @Override
    public IType checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType expressionType = this.expression.checkTypes(typeCheckerTable);
        if (!(expressionType instanceof RefType)) {
            throw new Exception("Heap reading expression: " + this.toString() + " can only operate with reference type expressions");
        }
        return ((RefType)expressionType).getInnerType();
    }
}
