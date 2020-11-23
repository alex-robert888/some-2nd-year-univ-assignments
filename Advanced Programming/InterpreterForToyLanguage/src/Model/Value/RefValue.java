package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public IType getType() {
        return new RefType(this.locationType);
    }

    @Override
    public boolean equals(IValue other) {
        return other instanceof RefValue;
    }

    public int getAddress() {
        return this.address;
    }

    public IType getLocationType() {
        return this.locationType;
    }

    public String toString() {
        return String.format("RefValue(%d, %s)", this.address, this.locationType.toString());
    }
}
