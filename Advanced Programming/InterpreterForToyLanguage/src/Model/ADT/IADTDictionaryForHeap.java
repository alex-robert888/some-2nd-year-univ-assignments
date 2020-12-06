package Model.ADT;

import Model.Value.IValue;

import java.util.Map;

public interface IADTDictionaryForHeap{
    int put(IValue value);
    boolean isKeyDefined(int key);
    IValue getValue(int key);
    IValue remove(int key);
    String toString();
    void update(int key, IValue value);
    void setContent(Map<Integer, IValue> content);
    Map<Integer, IValue> getContent();

}
