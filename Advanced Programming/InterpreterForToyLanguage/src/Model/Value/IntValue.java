package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue {
    int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public boolean equals(IValue other) {
        return other instanceof IntValue;
    }

    public int getValue() {
        return this.value;
    }
}
