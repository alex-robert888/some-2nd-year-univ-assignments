package Model.Statement;

import Model.ADT.ADTList;
import Model.ADT.IADTList;
import Model.Expression.IExpression;
import Model.ProgramState;

public class PrintStatement implements IStatement {

    IExpression expression;
    PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTList<String> outputList = programState.getOutputList();
        outputList.append(this.expression.evaluate(programState.getSymbolTable()).toString());
        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression);
    }

    @Override
    public String toString() {
        return new String("print(" + this.expression.toString() + ");");
    }
}
