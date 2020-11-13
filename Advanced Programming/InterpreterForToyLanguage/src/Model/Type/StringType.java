package Model.Type;

import Model.Value.*;

public class StringType implements IType{
    public StringType() {

    }

    @Override
    public boolean equals(IType other) {
        return other instanceof StringType;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return new String("string");
    }
}
