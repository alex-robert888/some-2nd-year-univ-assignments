package Model.Type;

import Model.Value.IValue;
import Model.Value.RefValue;

public class RefType implements IType {
    private final IType innerType;

    public RefType(IType innerType) {
        this.innerType = innerType;
    }

    @Override
    public boolean equals(IType other) {
        return other instanceof RefType && this.innerType.equals(((RefType) other).getInnerType());
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, this.innerType);
    }

    public IType getInnerType() {
        return this.innerType;
    }

    public String toString() {
        return "Ref(" + this.innerType.toString() + ")";
    }
}
