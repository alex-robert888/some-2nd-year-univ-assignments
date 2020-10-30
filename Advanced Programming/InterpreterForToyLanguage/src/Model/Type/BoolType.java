package Model.Type;

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

    public boolean defaultValue() {
        return false;
    }
}
