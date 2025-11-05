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

    public  void    addInOrder(T val){
        if (root == null) { root = new NodoImpl<>(val); return; }
        addInOrder(root, val);
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


    /**
     * Searches for a reference to a node containing the specified value in the binary tree.
     * The search starts from the given subtree root and traverses recursively.
     *
     * @param searchTreeRoot the root of the subtree where the search begins; can be null
     * @param val the value to be searched for in the binary tree
     * @return a reference to the node containing the specified value if found; null otherwise
     */
    private NodoImpl<T> findRefToNode(NodoImpl<T> searchTreeRoot, T val){
        if(searchTreeRoot == null) return null;
        if (searchTreeRoot.get().equals(val)) return searchTreeRoot;

        NodoImpl<T> refFromLeft = findRefToNode(searchTreeRoot.getDx(), val);
        if(refFromLeft != null) return refFromLeft;

        return findRefToNode(searchTreeRoot.getSx(), val);
    }

    //To string
    @Override
    public String toString() {
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
    private int getHeight(NodoImpl<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getSx()), getHeight(node.getDx()));
    }
    private void buildLines(NodoImpl<T> node, int level, int left, int right, int width, List<List<String>> lines, int maxHeight) {
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

    /**
     * Searches for the specified value in the binary tree.
     * The search starts from the root of the tree and traverses recursively through its nodes.
     *
     * @param val the value to be searched for in the binary tree
     * @return true if the value is found in the binary tree, false otherwise
     */
    //Altro
    public boolean find(int val) {
        if (root == null) return false;
        return find(root, val);
    }
    private boolean find(NodoImpl<T> subTreeRoot, int val) {
        if (subTreeRoot.get().equals(val)) return true;
        return find(subTreeRoot.getSx(), val) || find(subTreeRoot.getDx(), val);
    }

    /**
     * Counts the number of leaf nodes in the binary tree.
     * A leaf node is defined as a node without any children.
     *
     * @return the count of leaf nodes in the binary tree. If the tree is empty, returns 0.
     */
    public int leafCounter(){
        if (root == null) return 0;
        return leafCounter(root);
    }
    private int leafCounter(NodoImpl<T> subTreeRoot) {
        int counter = 0;
        //Incremento del contatore
        if (subTreeRoot.getSx() == null && subTreeRoot.getDx() == null) counter++;
        //OPPURE ricorro
        if (subTreeRoot.getSx() != null) counter += leafCounter(subTreeRoot.getSx());
        if (subTreeRoot.getDx() != null) counter += leafCounter(subTreeRoot.getDx());
        return counter;
    }

    /**
     * Searches for a node with the specified value in the binary tree.
     * The search starts from the root of the tree and traverses recursively.
     *
     * @param val the value of the node to be searched for in the binary tree
     * @return the node containing the specified value if found; null otherwise
     */
    public NodoImpl<T> findNode(int val) {
        if (root == null) return null;
        return findNode(root, val);
    }
    private NodoImpl<T> findNode(NodoImpl<T> subTreeRoot, int val) {
        if (subTreeRoot.get().equals(val)) return subTreeRoot;

        NodoImpl<T> resL = findNode(subTreeRoot.getSx(), val);
        NodoImpl<T> resR = findNode(subTreeRoot.getDx(), val);

        if (resL != null) return resL;
        if (resR != null) return resR;

        return null;
    }

    /**
     * Computes the height of the binary tree. The height of a binary tree is defined as
     * the number of edges on the longest path from the root to a leaf node.
     *
     * @return the height of the binary tree. If the root is null (empty tree), returns 0.
     */
    public  int findHeight(){
        if (root == null) return 0;
        return findHeight(root);
    }
    private int findHeight(NodoImpl<T> subTreeRoot) {
        if (subTreeRoot == null) return 0;
        return 1 + Math.max(findHeight(subTreeRoot.getSx()), findHeight(subTreeRoot.getDx()));
    }

    /**
     * Calculates the total number of nodes present in the binary tree.
     *
     * @return the total count of nodes in the tree. If the tree is empty, returns 0.
     */
    public  int howManyNodes(){
        if (root == null) return 0;
        return howManyNodes(root);
    }
    private int howManyNodes(NodoImpl<T> subTreeRoot) {
        if (subTreeRoot == null) return 0;
        return 1 + howManyNodes(subTreeRoot.getSx()) + howManyNodes(subTreeRoot.getDx());
    }


    /**
     * Calculates the sum of all the node values in the binary tree.
     *
     * @return the total sum of all node values in the tree. If the tree is empty, returns 0.
     */
    public  int sumNodes(){
        if (root == null) return 0;
        return sumNodes(root);
    }
    private int sumNodes(NodoImpl<T> subTreeRoot) {
        if(subTreeRoot == null) return 0;
        return (Integer) subTreeRoot.get() + sumNodes(subTreeRoot.getSx()) + sumNodes(subTreeRoot.getDx());
    }


    /**
     * Determines the level of a node containing the specified value in the binary tree.
     * The level is defined as the distance (in edges) between the root and the node.
     *
     * @param needleVal the value of the node whose level needs to be determined
     * @return the level of the node if it exists in the tree, or Integer.MAX_VALUE if the node
     *         is not found
     */
    public  int getLevel(T needleVal){
        NodoImpl<T> needle = findRefToNode(root, needleVal);
        return getLevel(root, needle);
    }
    private int getLevel(NodoImpl<T> subTreeRoot, NodoImpl<T> needle) {
        if (subTreeRoot == null) return Integer.MAX_VALUE;
        if (subTreeRoot == needle) return 0;

        int resL = 1 + getLevel(subTreeRoot.getSx(), needle);
        int resR = 1 + getLevel(subTreeRoot.getDx(), needle);

        if (resL != Integer.MAX_VALUE) return resL;
        if (resR != Integer.MAX_VALUE) return resR;

        //Se non ho trovato il nodo
        return Integer.MAX_VALUE;
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
