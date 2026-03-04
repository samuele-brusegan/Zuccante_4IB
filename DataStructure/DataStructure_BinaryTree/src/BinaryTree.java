public class BinaryTree<T> {
    NodoImpl<T> root;
    int height;

    public BinaryTree() {}

    public void add(T father, T child, boolean isLeft) {
        //Cerco il nodo padre
        NodoImpl<T> refToParent = findRefToNode(root, father);
        //Aggiungo il nodo figlio
        NodoImpl<T> newNode = new NodoImpl<>(child);

        if (isLeft) refToParent.setSx(newNode);
        else        refToParent.setDx(newNode);
    }

    //Mask Visits
    public void viewPreOrder() {
        viewPreOrder(root);
    }
    public void viewInOrder() {
        viewInOrder(root);
    }
    public void viewPostOrder() {
        viewPostOrder(root);
    }

    //Recursive Visits
    private void viewPreOrder(NodoImpl<T> subTreeRoot) {
        System.out.println("|"+subTreeRoot+"|");
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
    }
    private void viewInOrder(NodoImpl<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        System.out.println("|"+subTreeRoot+"|");
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
    }
    private void viewPostOrder(NodoImpl<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
        System.out.println("|"+subTreeRoot+"|");
    }


    private NodoImpl<T> findRefToNode(NodoImpl<T> searchTreeRoot, T val){
        if(searchTreeRoot == null) return null;
        if (searchTreeRoot.get().equals(val)) return searchTreeRoot;

        NodoImpl<T> refFromLeft = findRefToNode(searchTreeRoot.getDx(), val);
        if(refFromLeft != null) return refFromLeft;

        return findRefToNode(searchTreeRoot.getSx(), val);
    }
}

class NodoImpl<T> {
    private NodoImpl<T> dx;
    private NodoImpl<T> sx;
    private final T val;

    public NodoImpl(T val) {
        this.dx = null;
        this.sx = null;
        this.val = val;
    }

    public void setDx(NodoImpl<T> n) {
        this.dx = n;
    }

    public NodoImpl<T> getDx() {
        return dx;
    }

    public NodoImpl<T> getSx() {
        return sx;
    }

    public void setSx(NodoImpl<T> sx) {
        this.sx = sx;
    }

    public T get() {
        return val;
    }

    @Override
    public String toString() {
        return val + "";
    }
}
