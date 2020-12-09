package Model.Statement;

import Model.ADT.ADTStack;
import Model.ADT.IADTDictionary;
import Model.ADT.IADTStack;
import Model.ProgramState;
import Model.Type.IType;

import java.sql.Statement;
import java.util.Deque;

public class CompoundStatement implements IStatement {
    IStatement leftStatement;
    IStatement rightStatement;

    public CompoundStatement(IStatement leftStatement, IStatement rightStatement) {
        this.leftStatement = leftStatement;
        this.rightStatement = rightStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        IADTStack<IStatement> executionStack = programState.getExecutionStack();
        executionStack.push(this.rightStatement);
        executionStack.push(this.leftStatement);
        return null;
    }

    @Override
    public IStatement deepCopy() { // is this OK?
        return new CompoundStatement(this.leftStatement, this.rightStatement);
    }

    @Override
    public String toString() {
        return new String(this.leftStatement.toString() + " " + this.rightStatement.toString() + " ");
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        IADTDictionary<String, IType> typeCheckerTableOfLeftStatement = this.leftStatement.checkTypes(typeCheckerTable);
        return this.rightStatement.checkTypes(typeCheckerTableOfLeftStatement);
    }
}
