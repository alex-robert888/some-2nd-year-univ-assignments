package Model.ADT;
import java.util.Deque;
import java.util.LinkedList;

public class ADTStack<T> implements IADTStack<T>{
    Deque<T> stack = new LinkedList<T>();

    public ADTStack() {
    }

    @Override
    public T pop() {
        return this.stack.pop();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }
}
