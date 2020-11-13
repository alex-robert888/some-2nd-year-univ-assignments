package Model.ADT;

public interface IADTDictionary<T_Key, T_Value> {
    void put(T_Key key, T_Value value);
    boolean isKeyDefined(T_Key key);
    T_Value getValue(T_Key key);
    T_Value remove(T_Key key);
    String toString();
}
