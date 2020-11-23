package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithmeticExpression implements IExpression {
    IExpression expressionLeft;
    IExpression expressionRight;
    String arithmeticOperator;

    public ArithmeticExpression(String arithmeticOperator, IExpression expressionLeft, IExpression expressionRight) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.arithmeticOperator = arithmeticOperator;
    }
    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception {
        IValue expressionLeftValue = expressionLeft.evaluate(symbolTable, heap);
        if (!expressionLeftValue.getType().equals(new IntType())) {
            throw new Exception("Left side of the arithmetic expression is not integer.");
        }

        IValue expressionRightValue = expressionRight.evaluate(symbolTable, heap);
        if (!expressionRightValue.getType().equals(new IntType())) {
            throw new Exception("Right side of the arithmetic expression is not integer.");
        }

        IntValue expressionRightValueInt = (IntValue)expressionRightValue;
        IntValue expressionLeftValueInt = (IntValue)expressionLeftValue;

        return this.performOperation(symbolTable, expressionLeftValueInt, expressionRightValueInt);
    }

    @Override
    public String toString() {
        return this.expressionLeft.toString() + " " + this.arithmeticOperator + " " + this.expressionRight.toString();
    }

    private IntValue performOperation(IADTDictionary<String, IValue> symbolTable, IntValue expressionLeftValueInt, IntValue expressionRightValueInt) {
        switch (this.arithmeticOperator) {
            case "+" -> {
                return new IntValue(expressionLeftValueInt.getValue() + expressionRightValueInt.getValue());
            }
            case "-" -> {
                return new IntValue(expressionLeftValueInt.getValue() - expressionRightValueInt.getValue());
            }
            case "*" -> {
                return new IntValue(expressionLeftValueInt.getValue() * expressionRightValueInt.getValue());
            }
            case "/" -> {
                return new IntValue(expressionLeftValueInt.getValue() / expressionRightValueInt.getValue());
            }
        }
        return null;
    }

}
