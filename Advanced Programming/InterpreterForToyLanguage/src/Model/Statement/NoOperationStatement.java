package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ProgramState;
import Model.Type.IType;

public class NoOperationStatement implements IStatement{
    public NoOperationStatement() {

    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return new String("");
    }

    @Override
    public IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception {
        return typeCheckerTable;
    }
}
