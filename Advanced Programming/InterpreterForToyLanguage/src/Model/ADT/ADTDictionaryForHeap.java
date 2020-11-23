package Model.ADT;

import Model.Value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ADTDictionaryForHeap implements IADTDictionaryForHeap {
    Map<Integer, IValue> map;
    Integer nextFreeKey;

    public ADTDictionaryForHeap() {
        map = new HashMap<>();
        nextFreeKey = 1;
    }
    @Override
    public int put(IValue value) {
        this.map.put(nextFreeKey, value);
        return nextFreeKey++;
    }

    @Override
    public void update(int key, IValue value) {
        this.map.put(key, value);
    }

    @Override
    public boolean isKeyDefined(int key) {
        return this.map.containsKey(key);
    }

    @Override
    public IValue getValue(int key) {
        return this.map.get(key);
    }

    @Override
    public IValue remove(int key) {
        return this.map.remove(key);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map.Entry<Integer, IValue> entry : map.entrySet()) {
            stringJoiner.add(entry.getKey().toString() + " --> " + entry.getValue().toString() + "\n");
        }
        return stringJoiner.toString();
    }
}
