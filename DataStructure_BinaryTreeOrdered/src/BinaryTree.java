import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
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

    public boolean addInOrder(T val){
        if (root == null) { root = new NodoImpl<>(val); return true; }
        return addInOrder(root, val);
    }

    private boolean addInOrder(NodoImpl<T> subRootTree, T val){

        //Se val è maggiore o uguale di subRootTree.get() allora lo aggiungo a dx
        //Se val è       minore      di subRootTree.get() allora lo aggiungo a sx

        boolean cond = val.compareTo(subRootTree.get()) >= 0;

        //Aggiungo
        if (cond && subRootTree.getDx() == null) {
            subRootTree.setDx(new NodoImpl<>(val));
            return true;
        } else if (subRootTree.getSx() == null) {
            subRootTree.setSx(new NodoImpl<>(val));
            return true;
        }

        //Scendo nell'albero
        if (cond)   return addInOrder(subRootTree.getDx(), val);
        else        return addInOrder(subRootTree.getSx(), val);

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
        System.out.print("|"+subTreeRoot+"|");
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
    }
    private void viewInOrder(NodoImpl<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        System.out.print("|"+subTreeRoot+"|");
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
    }
    private void viewPostOrder(NodoImpl<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
        System.out.print("|"+subTreeRoot+"|");
    }


    private NodoImpl<T> findRefToNode(NodoImpl<T> searchTreeRoot, T val){
        if(searchTreeRoot == null) return null;
        if (searchTreeRoot.get().equals(val)) return searchTreeRoot;

        NodoImpl<T> refFromLeft = findRefToNode(searchTreeRoot.getDx(), val);
        if(refFromLeft != null) return refFromLeft;

        return findRefToNode(searchTreeRoot.getSx(), val);
    }

    @Override
    public String toString() {
        if (root == null) {
            return "╔═══════════════════════════════════╗\n" +
                    "║     BINARY SEARCH TREE            ║\n" +
                    "╚═══════════════════════════════════╝\n\n" +
                    "Albero vuoto\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════╗\n");
        sb.append("║     BINARY SEARCH TREE            ║\n");
        sb.append("╚═══════════════════════════════════╝\n\n");

        buildTreeString(root, sb, "", "", "");

        return sb.toString();
    }

    private void buildTreeString(NodoImpl<T> node, StringBuilder sb, String prefix, String childPrefix, String nodePrefix) {
        if (node == null) return;

        sb.append(prefix);
        sb.append(nodePrefix);
        sb.append(node.get());
        sb.append("\n");

        List<NodoImpl<T>> children = new ArrayList<>();
        if (node.getSx() != null) children.add(node.getSx());
        if (node.getDx() != null) children.add(node.getDx());

        for (int i = 0; i < children.size(); i++) {
            boolean isLast = (i == children.size() - 1);
            String newPrefix = childPrefix + (isLast ? "└── " : "├── ");
            String newChildPrefix = childPrefix + (isLast ? "    " : "│   ");

            buildTreeString(children.get(i), sb, newPrefix, newChildPrefix, "");
        }
    }

    private void collectNodesByLevel(NodoImpl<T> node, List<List<NodePosition>> levels, int level, int position) {
        if (node == null) return;

        // Assicurati che la lista abbia abbastanza livelli
        while (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(new NodePosition(node, position));

        // Visita i figli
        collectNodesByLevel(node.getSx(), levels, level + 1, position * 2);
        collectNodesByLevel(node.getDx(), levels, level + 1, position * 2 + 1);
    }

    private int calculateWidth(NodoImpl<T> node) {
        if (node == null) return 0;
        if (node.getSx() == null && node.getDx() == null) return 1;
        return calculateWidth(node.getSx()) + calculateWidth(node.getDx());
    }

    // Classe helper per tenere traccia dei nodi e delle loro posizioni
    private class NodePosition {
        NodoImpl<T> node;
        int position;

        NodePosition(NodoImpl<T> node, int position) {
            this.node = node;
            this.position = position;
        }
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
