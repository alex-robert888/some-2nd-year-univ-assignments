package Model.ADT;

import java.util.Deque;

public interface IADTStack<T> {
    T pop();
    void push(T element);
    boolean isEmpty();
    String toString();
}
