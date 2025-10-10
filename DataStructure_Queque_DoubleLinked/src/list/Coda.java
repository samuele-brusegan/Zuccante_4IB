package list;

public class Coda<T> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int len;

    public Coda() {
        this.head = null;
        this.tail = null;
        this.len = 0;
    }

    public void enqueue(T elem) {
        // riceviamo un elemento di tipo T
        // Creo nuovo nodo
        Nodo<T> n = new Nodo<>(elem);
        if ( tail != null ) {
            this.tail.setNext(n);
            n.setPrev(this.tail);
            this.len++;
        }
        this.tail = n;
        if (head == null) head = n;
    }

    public T dequeue() {
        Nodo<T> oldHead = this.head;
        Nodo<T> newHead = this.head.getNext();
        this.head = newHead;
        this.head.setPrev(null);
        this.len--;
        return oldHead.get();
    }

    public int size() {
        return this.len;
    }

    public boolean isEmpty() {
        return (this.len == 0);
    }

    @Override
    public String toString() {
        Nodo<T> n = this.head;
        StringBuilder out = new StringBuilder("{ ");
        for (int i = 0; n != null; i++) {
            out.append(n.toString());
            n = n.getNext();
            if ( n != null ) out.append(", ");
        }
        out.append(" }");
        return out.toString();
    }
}
