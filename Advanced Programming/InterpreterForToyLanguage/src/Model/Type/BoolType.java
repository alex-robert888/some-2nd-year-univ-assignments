package Model.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;

public class BoolType implements IType{
    public BoolType() {

    }
    @Override
    public boolean equals(IType other) {
        return other instanceof BoolType;
    }

    public String toString() {
        return "bool";
    }

    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
