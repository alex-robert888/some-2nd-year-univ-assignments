package Model.Expression;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTDictionaryForHeap;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalExpression implements IExpression {
    String relation;
    IExpression leftExpression;
    IExpression rightExpression;

    public RelationalExpression(String relation, IExpression leftExpression, IExpression rightExpression) {
        this.relation = relation;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IADTDictionary<String, IValue> symbolTable, IADTDictionaryForHeap heap) throws Exception {
        IValue expressionLeftValue =  this.leftExpression.evaluate(symbolTable, heap);
        if (!expressionLeftValue.getType().equals(new IntType())) {
            throw new Exception("Left side of the relational expression is not integer.");
        }

        IValue expressionRightValue =  this.rightExpression.evaluate(symbolTable, heap);
        if (!expressionRightValue.getType().equals(new IntType())) {
            throw new Exception("Right side of the relational expression is not integer.");
        }

        IntValue expressionRightValueInt = (IntValue)expressionRightValue;
        IntValue expressionLeftValueInt = (IntValue)expressionLeftValue;

        return this.performComparison(symbolTable, expressionLeftValueInt, expressionRightValueInt);
    }

    @Override
    public IType checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType leftExpressionType = this.leftExpression.checkTypes(typeCheckerTable);
        IType rightExpressionType = this.rightExpression.checkTypes(typeCheckerTable);

        if (!leftExpressionType.equals(new IntType()) || !rightExpressionType.equals(new IntType())) {
            throw new Exception("Relational expression: " + this.toString() + " can only operate with integer expressions");
        }

        return new BoolType();
    }

    private IValue performComparison(IADTDictionary<String, IValue> symbolTable, IntValue expressionLeftValueInt, IntValue expressionRightValueInt) {
        switch (this.relation) {
            case "<" -> {
                return new BoolValue(expressionLeftValueInt.getValue() < expressionRightValueInt.getValue());
            }
            case "<=" -> {
                return new BoolValue(expressionLeftValueInt.getValue() <= expressionRightValueInt.getValue());
            }
            case "==" -> {
                return new BoolValue(expressionLeftValueInt.getValue() == expressionRightValueInt.getValue());
            }
            case "!=" -> {
                return new BoolValue(expressionLeftValueInt.getValue() != expressionRightValueInt.getValue());
            }
            case ">=" -> {
                return new BoolValue(expressionLeftValueInt.getValue() >= expressionRightValueInt.getValue());
            }
            case ">" -> {
                return new BoolValue(expressionLeftValueInt.getValue() > expressionRightValueInt.getValue());
            }
        }
        return null;
    }
}
