package Model.Statement;

import Model.ADT.IADTDictionary;
import Model.ProgramState;
import Model.Type.IType;

public interface IStatement {
    ProgramState execute(ProgramState programState) throws Exception;
    IStatement deepCopy();
    String toString();
    IADTDictionary<String, IType> checkTypes(IADTDictionary<String, IType> typeCheckerTable) throws Exception;
}
