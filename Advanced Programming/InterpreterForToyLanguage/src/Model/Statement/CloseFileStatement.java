package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class CloseFileStatement implements IStatement{
    IExpression expressionFileName;

    public CloseFileStatement(IExpression expressionFileName) {
        this.expressionFileName = expressionFileName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        // Verify that the argument for the openFile method is a StringValue
        IValue expressionValue = this.expressionFileName.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!expressionValue.getType().equals(new StringType())) {
            throw new Exception("closeFile statement failed. File name given as argument has not a StringType.");
        }

        // Get file descriptor from file table
        var fileTable = programState.getFileTable();
        StringValue expressionStringValue = (StringValue) expressionValue;
        BufferedReader bufferedReader = fileTable.getValue(expressionStringValue);
        if (bufferedReader == null) {
            throw new Exception(String.format("File %s was not opened.", this.expressionFileName.toString()));
        }

        // Close file and remove it from file table
        bufferedReader.close();
        fileTable.remove(expressionStringValue);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileStatement(this.expressionFileName);
    }

    @Override
    public String toString() {
        return String.format("closeFile(%s);", this.expressionFileName.toString());
    }
}
