package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class HeapWritingStatement implements IStatement {

    private String variableName;
    private IExpression expression;

    public HeapWritingStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        // Check if variable was defined
        IValue variable = symbolTable.getValue(this.variableName);
        if (variable == null) {
            throw new Exception("Statement " + this.toString() + " failed. Variable given as first argument was not declared");
        }

        // Check if variable is of ref type
        if (!(variable.getType() instanceof RefType)) {
            throw new Exception("Statement " + this.toString() + " failed. Variable is not of RefType");
        }

        // Check if is in the heap
        IValue value = heap.getValue(((RefValue)variable).getAddress());
        if (value == null) {
            throw new Exception("Statement " + this.toString() + " failed. Variable is not in heap.");
        }

        IValue expressionValue = this.expression.evaluate(symbolTable, heap);
        if (!expressionValue.getType().equals(value.getType())) {
            throw new Exception("Statement " + this.toString() + " failed. Different types");
        }

        heap.update(((RefValue)variable).getAddress(), expressionValue);

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(this.variableName, this.expression);
    }

    public String toString() {
        return String.format("wH(%s, %s)", this.variableName, this.expression.toString());
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType variableType = typeCheckerTable.getValue(this.variableName);
        IType expressionType = this.expression.checkTypes(typeCheckerTable);
        if (!variableType.equals(new RefType(expressionType))) {
            throw new Exception("Heap writing statement: " + this.toString() + " have variable and expression of different types");
        }
        return typeCheckerTable;
    }
}
