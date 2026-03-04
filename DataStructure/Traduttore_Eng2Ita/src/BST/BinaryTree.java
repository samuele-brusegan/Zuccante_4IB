package BST;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BinaryTree<T> {

    protected Node<T> root;

    public void add(T father, T child, boolean isLeft) {
        if (root == null) { root = new Node<>(child); return; }
        //Cerco il nodo padre
        Node<T> refToParent = findRefToNode(root, father);
        //Aggiungo il nodo figlio
        Node<T> newNode = new Node<>(child);

        if (isLeft) refToParent.setSx(newNode);
        else        refToParent.setDx(newNode);
    }

    /**
     * Searches for a reference to a node containing the specified value in the binary tree.
     * The search starts from the given subtree root and traverses recursively.
     *
     * @param searchTreeRoot the root of the subtree where the search begins; can be null
     * @param val the value to be searched for in the binary tree
     * @return a reference to the node containing the specified value if found; null otherwise
     */
    protected Node<T> findRefToNode(Node<T> searchTreeRoot, T val){
        if(searchTreeRoot == null) return null;
        if (searchTreeRoot.get().equals(val)) return searchTreeRoot;

        Node<T> refFromLeft = findRefToNode(searchTreeRoot.getDx(), val);
        if(refFromLeft != null) return refFromLeft;

        return findRefToNode(searchTreeRoot.getSx(), val);
    }
    /**
     * Searches for a reference to the parent node of a node containing the specified value
     * in the binary tree. The search starts from the given subtree root and traverses recursively.
     *
     * @param searchTreeRoot the root of the subtree where the search begins; can be null
     * @param val the value of the child node for which the parent reference is to be found
     * @return a reference to the parent node of the node containing the specified value, including
     *         information about whether the node is on the left or right of the parent;
     *         null if no such parent or child is found
     */
    protected ChildNodeRef<T> findRefToFatherNode(Node<T> searchTreeRoot, T val){
        if(searchTreeRoot == null) return null;
        if (searchTreeRoot.getDx().get().equals(val)) return new ChildNodeRef<>(searchTreeRoot, false);
        if (searchTreeRoot.getSx().get().equals(val)) return new ChildNodeRef<>(searchTreeRoot, true);

        Node<T> refFromLeft = findRefToNode(searchTreeRoot.getDx(), val);
        if(refFromLeft != null) return null;

        return findRefToFatherNode(searchTreeRoot.getSx(), val);
    }

    //Mask Visits
    public void viewPreOrder() {
        viewPreOrder(root);

    }
    public void viewInOrder() {
	    if (root == null) {
		    System.err.println("L'albero è vuoto");
		    return;
	    }
        viewInOrder(root);

    }
    public void viewPostOrder() {
        if (root == null) {
            System.err.println("L'albero è vuoto");
            return;
        }
        viewPostOrder(root);
    }

    //Recursive Visits
    private void viewPreOrder(Node<T> subTreeRoot) {
        System.out.print("("+subTreeRoot+")");
        if(subTreeRoot.getSx() != null) viewPreOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPreOrder(subTreeRoot.getDx());
    }
    private void viewInOrder(Node<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewInOrder(subTreeRoot.getSx());
        System.out.print("("+subTreeRoot+")");
        if(subTreeRoot.getDx() != null) viewInOrder(subTreeRoot.getDx());
    }
    private void viewPostOrder(Node<T> subTreeRoot) {
        if(subTreeRoot.getSx() != null) viewPostOrder(subTreeRoot.getSx());
        if(subTreeRoot.getDx() != null) viewPostOrder(subTreeRoot.getDx());
        System.out.print("("+subTreeRoot+")");
    }

    private String viewInOrderStr(Node<T> subTreeRoot) {
        if (subTreeRoot == null) return "()";
        String out = "(";
        if(subTreeRoot.getSx() != null) out += viewInOrderStr(subTreeRoot.getSx());
        out += subTreeRoot;
        if(subTreeRoot.getDx() != null) out += viewInOrderStr(subTreeRoot.getDx());
        return out+")";
    }

    //To string
    @Override
    public String toString() {
        return viewInOrderStr(root);
    }

    public String coolToString() {
        if (root == null) return "Albero vuoto";

        StringBuilder sb = new StringBuilder();
        int height = getHeight(root);
        // Calcola la larghezza necessaria considerando gli spazi extra per l'ultimo livello
        int leafNodes = (int) Math.pow(2, height - 1);
        int maxWidth = leafNodes * 3 + (leafNodes - 1) * 2; // 3 caratteri per nodo (spazio+valore+spazio) + 2 spazi tra nodi

        List<List<String>> lines = new ArrayList<>();
        buildLines(root, 0, 0, maxWidth - 1, maxWidth, lines, height);

        for (List<String> line : lines) {
            for (String s : line) {
                sb.append(s);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    private int getHeight(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getSx()), getHeight(node.getDx()));
    }
    private void buildLines(Node<T> node, int level, int left, int right, int width, List<List<String>> lines, int maxHeight) {
        if (node == null) return;

        // Assicura che ci siano abbastanza righe (sia per il nodo che per i collegamenti)
        while (lines.size() <= level * 2 + 1) {
            List<String> newLine = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                newLine.add(" ");
            }
            lines.add(newLine);
        }

        // Calcola la posizione centrale
        int mid = (left + right) / 2;

        // Inserisce il valore del nodo
        String nodeStr = node.get().toString();

        // Determina se questo è l'ultimo livello
        boolean isLastLevel = (level == maxHeight - 1);

        // Se è l'ultimo livello, aggiungi spazi prima e dopo
        if (isLastLevel) {
            nodeStr = " " + nodeStr + " ";
        }

        // Centra il nodo nella sua posizione
        int startPos = mid - nodeStr.length() / 2;
        startPos = Math.max(0, Math.min(width - nodeStr.length(), startPos));

        for (int i = 0; i < nodeStr.length() && startPos + i < width; i++) {
            lines.get(level * 2).set(startPos + i, String.valueOf(nodeStr.charAt(i)));
        }

        // Disegna i collegamenti e i figli
        if (node.getSx() != null) {
            int leftChildMid = (left + mid - 1) / 2;

            // Disegna la linea verso sinistra
            for (int i = leftChildMid + 1; i < mid && i < width; i++) {
                if (lines.get(level * 2 + 1).get(i).equals(" ")) {
                    lines.get(level * 2 + 1).set(i, "-");
                }
            }
            if (leftChildMid < width) {
                lines.get(level * 2 + 1).set(leftChildMid, "+");
            }

            buildLines(node.getSx(), level + 1, left, mid - 1, width, lines, maxHeight);
        }

        if (node.getDx() != null) {
            int rightChildMid = (mid + 1 + right) / 2;

            // Disegna la linea verso dest                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ra
            for (int i = mid + 1; i < rightChildMid && i < width; i++) {
                if (lines.get(level * 2 + 1).get(i).equals(" ")) {
                    lines.get(level * 2 + 1).set(i, "-");
                }
            }
            if (rightChildMid < width) {
                lines.get(level * 2 + 1).set(rightChildMid, "+");
            }

            buildLines(node.getDx(), level + 1, mid + 1, right, width, lines, maxHeight);
        }
    }
}
