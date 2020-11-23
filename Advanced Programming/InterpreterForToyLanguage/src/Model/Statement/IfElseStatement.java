package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfElseStatement implements IStatement{
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfElseStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IValue expressionValue = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!expressionValue.getType().equals(new BoolType())) {
            throw new Exception("IF statement failed. Expression of IF has not a boolean value.");
        }

        var executionStack = programState.getExecutionStack();
        BoolValue expressionValueBool = (BoolValue)expressionValue;
        if (expressionValueBool.getValue()) {
            executionStack.push(this.thenStatement);
        }
        else {
            executionStack.push(this.elseStatement);
        }
        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new IfElseStatement(this.expression, this.thenStatement, this.elseStatement);
    }

    @Override
    public String toString() {
        return new String("(IF(" + this.expression.toString() + ") THEN\n\t" + this.thenStatement.toString() +
                "ELSE\n\t" + this.elseStatement.toString()) + "\n);";
    }
}
