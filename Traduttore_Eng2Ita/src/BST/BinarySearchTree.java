package BST;

@SuppressWarnings("unused")
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
//    Node<T> super.root;
    int height;

    public BinarySearchTree() {}

    public Node<T> getRoot() {
        return super.root;
    }

    public  void    addInOrder(T val){
//	    System.out.println("Adding new value (" + val + ") in the tree at " + (getClass().getName() + "@" + Integer.toHexString(hashCode())));
        if (super.root == null) {
			super.root = new Node<>(val);
//            System.out.println("Aggiungo come Radice: " + super.root.encode());
			return;
		}
  
		addInOrder(super.root, val);
    }
    private boolean addInOrder(Node<T> subRootTree, T val){

        //Se val è maggiore o uguale di subRootTree.get() allora lo aggiungo a dx
        //Se val è       minore      di subRootTree.get() allora lo aggiungo a sx

        boolean cond = val.compareTo(subRootTree.get()) >= 0;

        //Aggiungo
        if (cond && subRootTree.getDx() == null) {
            subRootTree.setDx(new Node<>(val));
            return true;
        } else if (subRootTree.getSx() == null) {
            subRootTree.setSx(new Node<>(val));
            return true;
        }

        //Scendo nell'albero
        if (cond)   return addInOrder(subRootTree.getDx(), val);
        else        return addInOrder(subRootTree.getSx(), val);

    }


    /**
     * Searches for the specified value in the binary tree.
     * The search starts from the super.root of the tree and traverses recursively through its nodes.
     *
     * @param val the value to be searched for in the binary tree
     * @return true if the value is found in the binary tree, false otherwise
     */
    //Altro
    public boolean find(int val) {
        if (super.root == null) return false;
        return find(super.root, val);
    }
    private boolean find(Node<T> subTreeRoot, int val) {
        if (subTreeRoot.get().equals(val)) return true;
        return find(subTreeRoot.getSx(), val) || find(subTreeRoot.getDx(), val);
    }


    /**
     * Finds the node containing the minimum value in the binary tree starting from the given subtree super.root.
     * The minimum value is located at the leftmost node of the subtree.
     *
     * @param subTreeRoot the super.root of the subtree being searched for the minimum value.
     *                    If null, it indicates an empty subtree.
     * @return the reference to the node containing the minimum value in the subtree.
     *         Returns null if the input subtree is empty.
     */
    private Node<T> findRefToMin(Node<T> subTreeRoot) {
        if (subTreeRoot == null) return null;
        if (subTreeRoot.getSx() == null) return subTreeRoot;
        return findRefToMin(subTreeRoot.getSx());
    }

    /**
     * Counts the number of leaf nodes in the binary tree.
     * A leaf node is defined as a node without any children.
     *
     * @return the count of leaf nodes in the binary tree. If the tree is empty, it returns 0.
     */
    public int leafCounter(){
        if (super.root == null) return 0;
        return leafCounter(super.root);
    }
    private int leafCounter(Node<T> subTreeRoot) {
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
     * The search starts from the super.root of the tree and traverses recursively.
     *
     * @param val the value of the node to be searched for in the binary tree
     * @return the node containing the specified value if found; null otherwise
     */
    public Node<T> findNode(int val) {
        if (super.root == null) return null;
        return findNode(super.root, val);
    }
    private Node<T> findNode(Node<T> subTreeRoot, int val) {
        if (subTreeRoot.get().equals(val)) return subTreeRoot;

        Node<T> resL = findNode(subTreeRoot.getSx(), val);
        Node<T> resR = findNode(subTreeRoot.getDx(), val);

        if (resL != null) return resL;
        if (resR != null) return resR;

        return null;
    }

    /**
     * Computes the height of the binary tree. The height of a binary tree is defined as
     * the number of edges on the longest path from the super.root to a leaf node.
     *
     * @return the height of the binary tree. If the super.root is null (empty tree), it returns 0.
     */
    public  int findHeight(){
        if (super.root == null) return 0;
        return findHeight(super.root);
    }
    private int findHeight(Node<T> subTreeRoot) {
        if (subTreeRoot == null) return 0;
        return 1 + Math.max(findHeight(subTreeRoot.getSx()), findHeight(subTreeRoot.getDx()));
    }

    /**
     * Calculates the total number of nodes present in the binary tree.
     *
     * @return the total count of nodes in the tree. If the tree is empty, it returns 0.
     */
    public  int howManyNodes(){
        if (super.root == null) return 0;
        return howManyNodes(super.root);
    }
    private int howManyNodes(Node<T> subTreeRoot) {
        if (subTreeRoot == null) return 0;
        return 1 + howManyNodes(subTreeRoot.getSx()) + howManyNodes(subTreeRoot.getDx());
    }


    /**
     * Calculates the sum of all the node values in the binary tree.
     *
     * @return the total sum of all node values in the tree. If the tree is empty, it returns 0.
     */
    public  int sumNodes(){
        if (super.root == null) return 0;
        return sumNodes(super.root);
    }
    private int sumNodes(Node<T> subTreeRoot) {
        if(subTreeRoot == null) return 0;
        return (Integer) subTreeRoot.get() + sumNodes(subTreeRoot.getSx()) + sumNodes(subTreeRoot.getDx());
    }


    /**
     * Determines the level of a node containing the specified value in the binary tree.
     * The level is defined as the distance (in edges) between the super.root and the node.
     *
     * @param needleVal the value of the node whose level needs to be determined
     * @return the level of the node if it exists in the tree, or Integer.MAX_VALUE if the node
     *         is not found
     */
    public  int getLevel(T needleVal){
        Node<T> needle = findRefToNode(super.root, needleVal);
        return getLevel(super.root, needle);
    }
    private int getLevel(Node<T> subTreeRoot, Node<T> needle) {
        if (subTreeRoot == null) return Integer.MAX_VALUE;
        if (subTreeRoot == needle) return 0;

        int resL = 1 + getLevel(subTreeRoot.getSx(), needle);
        int resR = 1 + getLevel(subTreeRoot.getDx(), needle);

        if (resL != Integer.MAX_VALUE) return resL;
	    //noinspection RedundantIfStatement
	    if (resR != Integer.MAX_VALUE) return resR;

        //Se non ho trovato il nodo
        return Integer.MAX_VALUE;
    }

    public boolean remove(T valueToBeRemoved) {
        ChildNodeRef<T> container = findRefToFatherNode(super.root, valueToBeRemoved);
        if (container == null) return false;

        //Trovo il padre e il nodo da rimuovere
        Node<T> father = container.getFather();
        Node<T> nodeToBeRemoved = null;
        if ( container.isChildOnLeft()) nodeToBeRemoved = container.getFather().getSx();
        if (!container.isChildOnLeft()) nodeToBeRemoved = container.getFather().getDx();
        if (  nodeToBeRemoved == null ) return false;

        //Conto il numero di figli del nodo da rimuovere
        int nFigli = 0;
        if (nodeToBeRemoved.getSx() != null) nFigli++;
        if (nodeToBeRemoved.getDx() != null) nFigli++;

        switch (nFigli) {
            //CASO 1: nFigli == 0 (Foglia)
            case 0 -> {
                //Rimuovo il nodo figlio
                father.setChild(null, container.isChildOnLeft());

                return true;
            }

            //CASO 2: nFigli == 1 (Ha un figlio)
            case 1 -> {
                boolean isGrandSonLeft = nodeToBeRemoved.getSx() != null;
                Node<T> grandSon = isGrandSonLeft ? nodeToBeRemoved.getSx() : nodeToBeRemoved.getDx();

                father.setChild(grandSon, container.isChildOnLeft());

                return true;
            }

            //CASO 3: nFigli == 2 (Ha due figli)
            case 2 -> {
                //Trovo il minimo del ramo destro
                Node<T> minNode = findRefToMin(super.root);
                //Sostituisco il minimo al nodo da rimuovere.
                  // Rimuovere il nodo minimo

                if ( !this.remove(minNode.get()) ) return false;

                  // Sostituire il nodo da rimuovere con il minimo
                father.setChild(minNode, container.isChildOnLeft());

                return true;
            }

            default -> {
                System.err.println("L'albero non è binario!");

                return false;
            }
        }
    }
}