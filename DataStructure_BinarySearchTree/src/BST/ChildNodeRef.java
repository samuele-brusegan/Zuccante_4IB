package BST;

public class ChildNodeRef<T> {
    Node<T> father;
    boolean isLeft;
    public ChildNodeRef(Node<T> father, boolean isLeft) {
        this.father = father;
        this.isLeft = isLeft;
    }

    public Node<T> getFather() {
        return father;
    }

    public boolean isChildOnLeft() {
        return isLeft;
    }
}
