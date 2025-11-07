package BST;

public class Node<T> {
    private Node<T> dx;
    private Node<T> sx;
    private final T val;

    public Node(T val) {
        this.dx = null;
        this.sx = null;
        this.val = val;
    }

    public void setDx(Node<T> n) {
        this.dx = n;
    }

    public Node<T> getDx() {
        return dx;
    }

    public Node<T> getSx() {
        return sx;
    }

    public void setSx(Node<T> sx) {
        this.sx = sx;
    }

    public void setChild(Node<T> n, boolean isLeft) {
        if (isLeft) this.sx = n;
        else        this.dx = n;
    }

    public T get() {
        return val;
    }

    @Override
    public String toString() {
        return val + "";
    }
}
