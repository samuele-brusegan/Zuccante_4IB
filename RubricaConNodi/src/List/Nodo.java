package List;

public class Nodo<T> {
    private Nodo<T> next;
    private T val;

    public Nodo(T val) {
        this.next = null;
        this.val = val;
    }

    public void setNext(Nodo<T> n) {
        this.next = n;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public T get() {
        return val;
    }

    public void set(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val+"";
    }
}
