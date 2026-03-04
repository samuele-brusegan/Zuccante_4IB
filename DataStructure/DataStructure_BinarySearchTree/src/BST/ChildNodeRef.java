package BST;

/**
 * Represents a reference to a child node within a binary tree structure.
 * It holds information about the parent node and whether the child is
 * on the left or right side of the parent.
 *
 * @param <T> The type of the value in the parent node and associated child nodes.
 */
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
