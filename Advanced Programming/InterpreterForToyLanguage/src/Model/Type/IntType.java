package Model.Type;

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

    public int defaultValue() {
        return 0;
    }
}
