public class Main {
    private static int testPassati = 0;
    private static int testFalliti = 0;
    
    public static void main(String[] args) {
        System.out.println("=== INIZIO TEST SUITE ListaOrdinata ===\n");
        
        testInserimentoOrdinato();
        testRimozione();
        testSize();
        testListaVuota();
        testDuplicati();
        testStrings();
        testRimozioneTesta();
        testRimozioneCoda();
        
        System.out.println("\n=== RIEPILOGO TEST ===");
        System.out.println("Test passati: " + testPassati);
        System.out.println("Test falliti: " + testFalliti);
        
        if (testFalliti > 0) {
            System.out.println("\n⚠️  CI SONO ERRORI DA FIXARE NELLA STRUTTURA DATI!");
        } else {
            System.out.println("\n✓ Tutti i test sono passati!");
        }
    }
    
    private static void testInserimentoOrdinato() {
        System.out.println("--- Test: Inserimento Ordinato ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(9);
        lista.add(3);
        
        String risultato = listaToString(lista);
        String atteso = "[1, 2, 3, 5, 8, 9]";
        
        verificaTest("Inserimento ordinato", atteso, risultato);
    }
    private static void testRimozione() {
        System.out.println("\n--- Test: Rimozione Elemento Centrale ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        
        boolean rimosso = lista.remove(3);
        String risultato = listaToString(lista);
        String atteso = "[1, 2, 4, 5]";
        
        verificaTest("Rimozione elemento esistente restituisce true", true, rimosso);
        verificaTest("Lista dopo rimozione", atteso, risultato);
    }
    private static void testRimozioneTesta() {
        System.out.println("\n--- Test: Rimozione Testa ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        lista.add(1);
        lista.add(2);
        lista.add(3);
        
        boolean rimosso = lista.remove(1);
        String risultato = listaToString(lista);
        String atteso = "[2, 3]";
        
        verificaTest("Rimozione testa restituisce true", true, rimosso);
        verificaTest("Lista dopo rimozione testa", atteso, risultato);
    }
    private static void testRimozioneCoda() {
        System.out.println("\n--- Test: Rimozione Coda ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        lista.add(1);
        lista.add(2);
        lista.add(3);
        
        boolean rimosso = lista.remove(3);
        String risultato = listaToString(lista);
        String atteso = "[1, 2]";
        
        verificaTest("Rimozione coda restituisce true", true, rimosso);
        verificaTest("Lista dopo rimozione coda", atteso, risultato);
    }
    private static void testSize() {
        System.out.println("\n--- Test: Size ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        verificaTest("Size lista vuota", 0, lista.size());
        
        lista.add(5);
        verificaTest("Size dopo 1 inserimento", 1, lista.size());
        
        lista.add(2);
        lista.add(8);
        verificaTest("Size dopo 3 inserimenti", 3, lista.size());
        
        lista.remove(5);
        verificaTest("Size dopo 1 rimozione", 2, lista.size());
        
        lista.remove(999); // elemento non esistente
        verificaTest("Size dopo rimozione fallita", 2, lista.size());
    }
    private static void testListaVuota() {
        System.out.println("\n--- Test: Lista Vuota ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        verificaTest("Rimozione da lista vuota restituisce false", false, lista.remove(1));
        verificaTest("Size lista vuota", 0, lista.size());
        
        String risultato = listaToString(lista);
        verificaTest("Lista vuota rappresentazione", "[]", risultato);
    }
    private static void testDuplicati() {
        System.out.println("\n--- Test: Duplicati ---");
        ListaOrdinata<Integer> lista = new ListaOrdinata<>();
        
        lista.add(5);
        lista.add(3);
        lista.add(5);
        lista.add(1);
        lista.add(3);
        
        String risultato = listaToString(lista);
        String atteso = "[1, 3, 3, 5, 5]";
        
        verificaTest("Inserimento con duplicati", atteso, risultato);
        verificaTest("Size con duplicati", 5, lista.size());
        
        // Rimuove solo la prima occorrenza
        lista.remove(3);
        String risultatoDopo = listaToString(lista);
        String attesoDopo = "[1, 3, 5, 5]";
        
        verificaTest("Rimozione prima occorrenza duplicato", attesoDopo, risultatoDopo);
        verificaTest("Size dopo rimozione duplicato", 4, lista.size());
    }
    private static void testStrings() {
        System.out.println("\n--- Test: String (Comparable) ---");
        ListaOrdinata<String> lista = new ListaOrdinata<>();
        
        lista.add("mela");
        lista.add("banana");
        lista.add("arancia");
        lista.add("kiwi");
        
        String risultato = listaToString(lista);
        String atteso = "[arancia, banana, kiwi, mela]";
        
        verificaTest("Ordinamento stringhe", atteso, risultato);
    }
    
    // Utility per convertire la lista in stringa
    private static <T extends Comparable<T>> String listaToString(ListaOrdinata<T> lista) {
        return lista.toString();
    }
    
    // Metodi di verifica
    private static void verificaTest(String nomeTest, Object atteso, Object risultato) {
        if (atteso.equals(risultato)) {
            System.out.println("  ✓ " + nomeTest);
            testPassati++;
        } else {
            System.out.println("  ✗ " + nomeTest);
            System.out.println("    Atteso:    " + atteso);
            System.out.println("    Ottenuto:  " + risultato);
            System.out.println("    🔧 ERRORE DA FIXARE!");
            testFalliti++;
        }
    }
    private static void verificaTest(String nomeTest, boolean atteso, boolean risultato) {
        verificaTest(nomeTest, (Object) atteso, (Object) risultato);
    }
    private static void verificaTest(String nomeTest, int atteso, int risultato) {
        verificaTest(nomeTest, (Object) atteso, (Object) risultato);
    }
}