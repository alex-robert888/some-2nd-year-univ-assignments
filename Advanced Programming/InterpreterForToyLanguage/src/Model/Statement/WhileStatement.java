package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ADT.IADTStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;

import java.sql.Statement;

public class WhileStatement implements IStatement {

    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IValue expressionValue = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!expressionValue.getType().equals(new BoolType())) {
            throw new Exception("IF statement failed. Expression of IF has not a boolean value.");
        }

        IADTStack<IStatement> executionStack = programState.getExecutionStack();
        BoolValue expressionValueCastToBool = (BoolValue) expressionValue;
        if (!expressionValueCastToBool.getValue()) {
            return programState;

        }
        executionStack.push(this.deepCopy());
        executionStack.push(this.statement);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(this.expression, this.statement);
    }

    @Override
    public String toString() {
        return String.format("while(%s) %s", this.expression.toString(), this.statement.toString());
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IType expressionType = this.expression.checkTypes(typeCheckerTable);
        if (!expressionType.equals(new BoolType())) {
            throw new Exception("While statement: " + this.toString() + " can only have a bool type expression");
        }

        this.statement.checkTypes(typeCheckerTable.deepCopy());

        return typeCheckerTable;
    }
}
