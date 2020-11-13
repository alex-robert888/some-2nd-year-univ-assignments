package Model.Type;

import Model.Value.IValue;

public interface IType {
    String toString();
    boolean equals(IType other);
    IValue defaultValue();
}
