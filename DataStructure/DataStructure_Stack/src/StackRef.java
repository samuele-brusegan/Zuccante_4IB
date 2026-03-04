import java.util.ArrayList;

public class StackRef<T> {
    private Nodo<T> sp;
    public int len;

    public StackRef() {
        this.sp = null;
        this.len = 0;
    }

    public T pop() {
        Nodo<T> tmp = sp;
        this.sp = sp.getNext();
        len--;
        return tmp.get();
    }

    public void push(T elem) {
        Nodo<T> n = new Nodo<>(elem);
        n.setNext(sp);
        this.sp = n;
        len++;
    }

    public int size() {
        return len;
    }

    @Override
    public String toString() {
        Nodo<T> p = sp;
        String out = "Stack{[ ";
        for (int i = 0; i < len; i++) {
            out += p.toString();
            if (p.getNext() != null) out += ", ";
            p = p.getNext();
        }
        return out + " ]}";
    }
}
