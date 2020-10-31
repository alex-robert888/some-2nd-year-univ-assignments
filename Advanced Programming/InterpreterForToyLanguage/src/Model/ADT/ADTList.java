package Model.ADT;

import java.util.ArrayList;
import java.util.StringJoiner;

public class ADTList<T> implements IADTList<T> {
    ArrayList<T> list = new ArrayList<>();

    public ADTList() {

    }

    @Override
    public void append(T value) {
        list.add(value);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (T outputElement : list) {
            stringJoiner.add(outputElement.toString());
        }
        return stringJoiner.toString();
    }
}
