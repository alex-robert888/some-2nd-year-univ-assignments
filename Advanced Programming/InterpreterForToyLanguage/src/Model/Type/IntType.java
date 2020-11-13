package Model.Type;

import Model.Value.*;

public class IntType implements IType{
    public IntType() {

    }
    @Override
    public boolean equals(IType other) {
        return other instanceof IntType;
    }

    @Override
    public String toString() {
        return new String("int");
    }
    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
