package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

public class StringValue implements IValue{
    String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return '"' + this.value + '"';
    }

    @Override
    public boolean equals(IValue other) {
        return other instanceof StringValue;
    }

    public String getValue() {
        return this.value;
    }
}
