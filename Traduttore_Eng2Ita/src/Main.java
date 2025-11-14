import BST.BinarySearchTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
 
	public static String dictionaryFName = "IngleseItaliano.csv";
	public static void main(String[] args) {
		// Creazione arraylist con i Termini
//		ArrayList<Termine> list = csv2List(dictionaryFName);
		ArrayList<Termine> list = new ArrayList<>();
		Termine[] terms = {new Termine("ciao", "hello"), new Termine("mondo", "world"), new Termine("casa", "home")};
		
		//noinspection ManualArrayToCollectionCopy
		for (Termine term : terms) //noinspection UseBulkOperation
			list.add(term);
		
		
		// Creazione albero en-it
		BinarySearchTree<Termine> bst = list2Tree(list);
		bst.viewInOrder();
		
		// OPZ: Ordinare array it-en
		//   +: Creazione Albero it-en
		
		// Creare la UI
    }
	
	private static ArrayList<Termine> csv2List(String csvFile) {
		try {
			ArrayList<Termine> listaEnIt = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			
			String line;
			while((line = br.readLine()) != null) {
				String[] words = line.split(",");
				
				Termine t = new Termine(words[0], words[1]);
				
				listaEnIt.add(t);
			}
			
			return listaEnIt;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static BinarySearchTree<Termine> list2Tree(ArrayList<Termine> list) {
		BinarySearchTree<Termine> bst = new BinarySearchTree<>();
		
		list2TreeRecursive(list, bst);
		
		return bst;
	}
	
	private static void list2TreeRecursive(ArrayList<Termine> splittedList, BinarySearchTree<Termine> bst) {
		
		if (splittedList.isEmpty()) return;
		
		//Pick mid list
		int i = ( splittedList.size() - 1 ) / 2;
		
		bst.addInOrder( splittedList.get(i) );
		System.out.println( bst );
		
		//Split list in 2
		
		if (splittedList.size() == 1) return;
		
		ArrayList<Termine> leftList  = new ArrayList<>( splittedList.subList( 0 , i ) );
		ArrayList<Termine> rightList = new ArrayList<>( splittedList.subList(i+1, splittedList.size() -1) );
		
		list2TreeRecursive(leftList, bst);
		list2TreeRecursive(rightList, bst);
		
	}
}
