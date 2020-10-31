package Model.ADT;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringJoiner;

public class ADTStack<T> implements IADTStack<T>{
    Deque<T> stack;

    public ADTStack() {
        stack = new LinkedList<>();
    }

    @Override
    public T pop() {
        return this.stack.pop();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        Iterator<T> dequeIterator = this.stack.iterator();
        StringJoiner stringJoiner = new StringJoiner(" | ");
        while (dequeIterator.hasNext()) {
            stringJoiner.add(dequeIterator.next().toString());
        }
        return stringJoiner.toString();
    }
}
