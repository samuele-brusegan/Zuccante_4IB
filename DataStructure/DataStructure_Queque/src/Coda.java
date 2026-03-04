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
            this.len++;
        }
        this.tail = n;
        if (head == null) head = n;
    }

    public T dequeue() {
        Nodo<T> oldHead = this.head;
        Nodo<T> newHead = this.head.getNext();
        this.head = newHead;
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
        String out = "{ ";
        for (int i = 0; n != null; i++) {
            out += n.toString();
            n = n.getNext();
            if ( n != null ) out += ", ";
        }
        out += " }";
        return out;
    }
}
