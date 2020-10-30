package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.Type.BoolType;
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
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable) throws Exception {
        IValue expressionLeftSideValue = this.expressionLeftSide.evaluate(symbolTable);
        if (expressionLeftSideValue.getType().equals(new BoolType())) {
            throw new Exception("Left side of the logical expression is not bool.");
        }

        IValue expressionRightSideValue = this.expressionRightSide.evaluate(symbolTable);
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
