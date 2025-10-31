import java.util.ArrayList;
import java.util.Arrays;
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
