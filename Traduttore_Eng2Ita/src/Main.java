import BST.BinarySearchTree;
import BST.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
 
	public static String dictionaryFName = "src/IngleseItaliano.csv";
	public static void main(String[] args) {
		// Creazione arraylist con i Termini
		ArrayList<TermineEnOrdered> list = csv2List(dictionaryFName);
        /*Termine[] terms = {
            new Termine("ciao", "hello"),
            new Termine("mondo", "world"),
            new Termine("casa", "home"),
        };

        ArrayList<Termine> list = new ArrayList<>(Arrays.asList(terms));*/

		
		// Creazione albero en-it
        System.out.println("\n\n========== EN -> IT ==========\n");

		BinarySearchTree<TermineEnOrdered> bst = list2TreeEn(list);
//        bst.viewInOrder();
        System.out.println( "Ricerca con albero Ordinato:" + findInBstEn(bst, "home") );
        System.out.println( "Ricerca con albero NON Ordinato:" + reverseInefficientFindInBst(bst, "assoluto") );


        System.out.println("\n\n========== IT -> EN ==========\n");

        // OPZ: Ordinare array it-en
		//   +: Creazione Albero it-en
		
        ArrayList<TermineItOrdered> listIt = new ArrayList<>();
        for (TermineEnOrdered t : list) listIt.add(t.reverse());
        listIt.sort(null);
        
        BinarySearchTree<TermineItOrdered> bstIt = list2TreeIt(listIt);

        System.out.println( "Ricerca con albero Ordinato:" + findInBstIt(bstIt, "casa"));
        
		// Creare la UI

    }
	
	private static ArrayList<TermineEnOrdered> csv2List(String csvFile) {
		try {
			ArrayList<TermineEnOrdered> listaEnIt = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			
			String line;
			while((line = br.readLine()) != null) {
				String[] words = line.split(",");
				TermineEnOrdered t = new TermineEnOrdered(words[1], words[0]);
				
				listaEnIt.add(t);
			}
			
			return listaEnIt;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private static BinarySearchTree<TermineEnOrdered> list2TreeEn(ArrayList<TermineEnOrdered> list) {
		BinarySearchTree<TermineEnOrdered> bst = new BinarySearchTree<>();
		
		list2TreeRecursiveEn(list, bst);
		
		return bst;
	}
	private static void list2TreeRecursiveEn(ArrayList<TermineEnOrdered> splittedList, BinarySearchTree<TermineEnOrdered> bst) {
		
		if (splittedList.isEmpty()) return;
		
		//Pick mid list
		int i = ( splittedList.size() ) / 2;
		TermineEnOrdered midEl = splittedList.get(i);
		bst.addInOrder( midEl );

		//Split list in 2
		
		if (splittedList.size() == 1) return;
		
		ArrayList<TermineEnOrdered> leftList  = new ArrayList<>( splittedList.subList( 0 , i ) );
		ArrayList<TermineEnOrdered> rightList = new ArrayList<>( splittedList.subList(i+1, splittedList.size()) );
		
		list2TreeRecursiveEn(leftList, bst);
		list2TreeRecursiveEn(rightList, bst);
		
	}
    
    public  static TermineEnOrdered findInBstEn(BinarySearchTree<TermineEnOrdered> bst, String englishWord) {
        if (bst.getRoot() == null) return null;
        return findInBstRecursiveEn(bst.getRoot(), englishWord);
    }
    private static TermineEnOrdered findInBstRecursiveEn(Node<TermineEnOrdered> subTreeRoot, String englishNeedle) {
        if (subTreeRoot == null) return null;

        String currentEnglish = subTreeRoot.get().getEnglish();
        if (currentEnglish.equals(englishNeedle)) return subTreeRoot.get();

        if (currentEnglish.compareTo(englishNeedle) > 0) {
            return findInBstRecursiveEn(subTreeRoot.getSx(), englishNeedle);
        } else {
            return findInBstRecursiveEn(subTreeRoot.getDx(), englishNeedle);
        }
    }

    public  static TermineEnOrdered reverseInefficientFindInBst(BinarySearchTree<TermineEnOrdered> bst, String italianWord) {
        if (bst.getRoot() == null) return null;
        return reverseInefficientFindInBstRecursive(bst.getRoot(), italianWord);
    }
    private static TermineEnOrdered reverseInefficientFindInBstRecursive(Node<TermineEnOrdered> subTreeRoot, String italianNeedle) {
        if (subTreeRoot == null) return null;
        String currentItalian = subTreeRoot.get().getItalian();

        if (currentItalian.equals(italianNeedle)) return subTreeRoot.get();

        TermineEnOrdered t1 = reverseInefficientFindInBstRecursive(subTreeRoot.getSx(), italianNeedle);
        if (t1 != null) return t1;
        return reverseInefficientFindInBstRecursive(subTreeRoot.getDx(), italianNeedle);
    }
    
    //--------------------------------------------------------------------------//

    private static BinarySearchTree<TermineItOrdered> list2TreeIt(ArrayList<TermineItOrdered> list) {
        BinarySearchTree<TermineItOrdered> bst = new BinarySearchTree<>();

        list2TreeRecursiveIt(list, bst);

        return bst;
    }
    private static void list2TreeRecursiveIt(ArrayList<TermineItOrdered> splittedList, BinarySearchTree<TermineItOrdered> bst) {

        if (splittedList.isEmpty()) return;

        //Pick mid list
        int i = ( splittedList.size() ) / 2;
        TermineItOrdered midEl = splittedList.get(i);
        bst.addInOrder( midEl );

        //Split list in 2

        if (splittedList.size() == 1) return;

        ArrayList<TermineItOrdered> leftList  = new ArrayList<>( splittedList.subList( 0 , i ) );
        ArrayList<TermineItOrdered> rightList = new ArrayList<>( splittedList.subList(i+1, splittedList.size()) );

        list2TreeRecursiveIt(leftList, bst);
        list2TreeRecursiveIt(rightList, bst);

    }

    public  static TermineItOrdered findInBstIt(BinarySearchTree<TermineItOrdered> bst, String italianWord) {
        if (bst.getRoot() == null) return null;
        return findInBstRecursiveIt(bst.getRoot(), italianWord);
    }
    private static TermineItOrdered findInBstRecursiveIt(Node<TermineItOrdered> subTreeRoot, String italianNeedle) {
        if (subTreeRoot == null) return null;

        String currentItalian = subTreeRoot.get().getItalian();
        if (currentItalian.equals(italianNeedle)) return subTreeRoot.get();

        if (currentItalian.compareTo(italianNeedle) > 0) {
            return findInBstRecursiveIt(subTreeRoot.getSx(), italianNeedle);
        } else {
            return findInBstRecursiveIt(subTreeRoot.getDx(), italianNeedle);
        }
    }
}
