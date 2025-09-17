import java.util.ArrayList;

public class Stack<T> {
    private final ArrayList<T> stack;

    public Stack() {
        this.stack = new ArrayList<>();
    }

    public T pop() {
        if (stack.isEmpty()) return null;

        T tmp = stack.getLast();
        stack.removeLast();
        return tmp;
    }

    public void push(T elem) {
        stack.add(elem);
    }

    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return "Stack{" + stack + '}';
    }
}