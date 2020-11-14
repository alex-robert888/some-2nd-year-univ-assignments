package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;


public class OpenFileStatement implements IStatement {
    IExpression expression;

    public OpenFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        // Verify that the argument for the openFile method is a StringValue
        IValue expressionValue = this.expression.evaluate(programState.getSymbolTable());
        if (!expressionValue.getType().equals(new StringType())) {
            throw new Exception("openFile statement failed. Expression of openFile has not a StringType.");
        }

        // Verify that the file is not already open (present in the file table)
        StringValue expressionStringValue = (StringValue) expressionValue;
        var fileTable = programState.getFileTable();
        if (fileTable.isKeyDefined(expressionStringValue)) {
            throw new Exception(String.format("openFile statement failed. File %s already opened.", expressionStringValue.getValue()));
        }

        // Open the file from the path given as argument. Catch potential IO errors.
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(((StringValue) expressionValue).getValue()));
        }
        catch (Exception exception) {
            throw exception;
            // throw new Exception(String.format("openFile statement failed. Failed to open file: %s.", expressionValue));
        }

        // Add file descriptor to file table
        fileTable.put(expressionStringValue, bufferedReader);

        return programState;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileStatement(this.expression);
    }

    @Override
    public String toString() {
        return String.format("openFile(%s);", this.expression.toString());
    }
}
