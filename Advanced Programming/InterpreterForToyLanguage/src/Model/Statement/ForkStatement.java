package Model.Statement;

import Model.ADT.ADTStack;
import Model.ADT.IADTDictionary;
import Model.ADT.IADTStack;
import Model.ProgramState;
import Model.Type.IType;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        // New program state
        return new ProgramState(new ADTStack<>(), programState.getSymbolTable().deepCopy(), programState.getOutputList(), programState.getFileTable(), programState.getHeap(), statement);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(this.statement);
    }

    public String toString() {
        return String.format("fork(%s)", this.statement.toString());
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        this.statement.checkTypes(typeCheckerTable);
        return typeCheckerTable;
    }

    public IStatement getStatement() {
        return this.statement.deepCopy();
    }
}
