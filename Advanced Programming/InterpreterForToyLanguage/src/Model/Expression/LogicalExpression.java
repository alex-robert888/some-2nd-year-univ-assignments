package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class LogicalExpression implements IExpression {
    IExpression expressionLeftSide;
    IExpression expressionRightSide;
    LogicalOperator logicalOperator;

    LogicalExpression(IExpression expressionLeftSide, IExpression expressionRightSide, LogicalOperator logicalOperator) {
        this.expressionLeftSide = expressionLeftSide;
        this.expressionRightSide = expressionRightSide;
        this.logicalOperator = logicalOperator;
    }

    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception {
        IValue expressionLeftSideValue = this.expressionLeftSide.evaluate(symbolTable, heap);
        if (expressionLeftSideValue.getType().equals(new BoolType())) {
            throw new Exception("Left side of the logical expression is not bool.");
        }

        IValue expressionRightSideValue = this.expressionRightSide.evaluate(symbolTable, heap);
        if (expressionRightSideValue.getType().equals(new BoolType())) {
            throw new Exception("Right side of the logical expression is not bool.");
        }

        BoolValue expressionLeftSideValueBool = (BoolValue)expressionLeftSideValue;
        BoolValue expressionRightSideValueBool = (BoolValue)expressionRightSideValue;

        return performOperation(symbolTable, expressionLeftSideValueBool, expressionRightSideValueBool);
    }

    @Override
    public String toString() {
        return this.expressionLeftSide.toString() + " " + this.getStringOperator() + " " + this.expressionLeftSide.toString();
    }

    @Override
    public IType checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType expressionLeftType = this.expressionLeftSide.checkTypes(typeCheckerTable);
        IType expressionRightType = this.expressionRightSide.checkTypes(typeCheckerTable);

        if (!expressionLeftType.equals(new BoolType()) || !expressionRightType.equals(expressionLeftType)) {
            throw new Exception("Logical expression: " + this.toString() + " can only operate with boolean expressions");
        }

        return new BoolType();
    }

    private BoolValue performOperation(IADTDictionary<String, IValue> symbolTable, BoolValue expressionRightValueInt, BoolValue expressionLeftValueInt) {
        switch (this.logicalOperator) {
            case AND -> {
                return new BoolValue(expressionLeftValueInt.getValue() && expressionRightValueInt.getValue());
            }
            case OR -> {
                return new BoolValue(expressionLeftValueInt.getValue() || expressionRightValueInt.getValue());
            }
        }
        return null;
    }

    private String getStringOperator() {
        switch (this.logicalOperator) {
            case AND -> {
                return "&&";
            }
            case OR -> {
                return "||";
            }
        }
        return null;
    }
}
