package Model.Statement;

import Model.ADT.ADTList;
import Model.ADT.IADTDictionary;
import Model.ADT.IADTList;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;

public class PrintStatement implements IStatement {

    IExpression expression;
    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTList<String> outputList = programState.getOutputList();
        outputList.append(this.expression.evaluate(programState.getSymbolTable(), programState.getHeap()).toString());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(this.expression);
    }

    @Override
    public String toString() {
        return new String("print(" + this.expression.toString() + ");");
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        this.expression.checkTypes(typeCheckerTable);
        return typeCheckerTable;
    }
}
