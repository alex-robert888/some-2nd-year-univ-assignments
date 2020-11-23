package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement{
    IExpression expressionFileName;
    String variableNameToStoreReadFileOutput;

    public ReadFileStatement(IExpression expressionFileName, String variableNameToStoreReadFileOutput) {
        this.expressionFileName = expressionFileName;
        this.variableNameToStoreReadFileOutput = variableNameToStoreReadFileOutput;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        // Verify that the variable name given as arguments was defined
        var symbolTable = programState.getSymbolTable();
        if (!symbolTable.isKeyDefined(this.variableNameToStoreReadFileOutput)) {
            throw new Exception(String.format("readFile statement failed. Variable name given as second argument was not defined."));
        }

        // Verify that the fileName method is a StringValue
        IValue expressionFileNameValue = this.expressionFileName.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!expressionFileNameValue.getType().equals(new StringType())) {
            throw new Exception("readFile statement failed. File name given as first argument of has not a StringType.");
        }

        // Get file descriptor from file table
        StringValue expressionFileNameStringValue = (StringValue)expressionFileNameValue;
        var fileTable = programState.getFileTable();
        BufferedReader bufferedReader = fileTable.getValue(expressionFileNameStringValue);
        if (bufferedReader == null) {
            throw new Exception(String.format("File %s was not opened.", expressionFileNameStringValue.getValue()));
        }

        // Read next line from file
        String currentLine = bufferedReader.readLine();
        IntValue currentLineToInteger = new IntValue(0);
        if (currentLine != null) {
            currentLineToInteger = new IntValue(Integer.parseInt(currentLine));
        }

        // Assign value read from file to the variable corresponding to the variable name
        symbolTable.put(this.variableNameToStoreReadFileOutput, currentLineToInteger);
        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(this.expressionFileName, this.variableNameToStoreReadFileOutput);
    }

    @Override
    public String toString() {
        return String.format("readFile(%s, %s);", this.expressionFileName, this.variableNameToStoreReadFileOutput);
    }
}
