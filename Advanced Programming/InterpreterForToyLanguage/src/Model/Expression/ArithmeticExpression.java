package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithmeticExpression implements IExpression {
    IExpression expressionLeft;
    IExpression expressionRight;
    ArithmeticOperator arithmeticOperator;

    ArithmeticExpression(IExpression expressionLeft, IExpression expressionRight, ArithmeticOperator arithmeticOperator) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.arithmeticOperator = arithmeticOperator;
    }
    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable) throws Exception {
        IValue expressionLeftValue = expressionLeft.evaluate(symbolTable);
        if (expressionLeftValue.getType().equals(new IntType())) {
            throw new Exception("Left side of the arithmetic expression is not integer.");
        }

        IValue expressionRightValue = expressionRight.evaluate(symbolTable);
        if (expressionRightValue.getType().equals(new IntType())) {
            throw new Exception("Right side of the arithmetic expression is not integer.");
        }

        IntValue expressionRightValueInt = (IntValue)expressionRightValue;
        IntValue expressionLeftValueInt = (IntValue)expressionLeftValue;

        return this.performOperation(symbolTable, expressionLeftValueInt, expressionRightValueInt);
    }

    @Override
    public String toString() {
        return this.expressionLeft.toString() + " " + this.getStringOperator() + " " + this.expressionRight.toString();
    }

    private IntValue performOperation(IADTDictionary<String, IValue> symbolTable, IntValue expressionRightValueInt, IntValue expressionLeftValueInt) {
        switch (this.arithmeticOperator) {
            case ADDITION -> {
                return new IntValue(expressionLeftValueInt.getValue() + expressionRightValueInt.getValue());
            }
            case SUBTRACTION -> {
                return new IntValue(expressionLeftValueInt.getValue() - expressionRightValueInt.getValue());
            }
            case MULTIPLICATION -> {
                return new IntValue(expressionLeftValueInt.getValue() * expressionRightValueInt.getValue());
            }
            case DIVISION -> {
                return new IntValue(expressionLeftValueInt.getValue() / expressionRightValueInt.getValue());
            }
        }
        return null;
    }

    private String getStringOperator() {
        switch (this.arithmeticOperator) {
            case ADDITION -> {
                return "+";
            }
            case SUBTRACTION -> {
                return "-";
            }
            case MULTIPLICATION -> {
                return "*";
            }
            case DIVISION -> {
                return "/";
            }
        }
        return null;
    }
}
