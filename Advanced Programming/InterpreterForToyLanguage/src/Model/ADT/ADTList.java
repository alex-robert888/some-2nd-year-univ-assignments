package Model.ADT;

import java.util.ArrayList;

public class ADTList<T> implements IADTList<T> {
    ArrayList<T> list = new ArrayList<>();

    public ADTList() {

    }
    public void append(T value) {
        list.add(value);
    }
}
