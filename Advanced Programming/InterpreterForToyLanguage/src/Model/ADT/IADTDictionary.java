package Model.ADT;

import Model.Value.IValue;

import java.util.Map;

public interface IADTDictionary<T_Key, T_Value> {
    void put(T_Key key, T_Value value);
    boolean isKeyDefined(T_Key key);
    T_Value getValue(T_Key key);
    T_Value remove(T_Key key);
    String toString();
    Map<T_Key, T_Value> getContent();
    IADTDictionary<T_Key, T_Value> deepCopy();
}
