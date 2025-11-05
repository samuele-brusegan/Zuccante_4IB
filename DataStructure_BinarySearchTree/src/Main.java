import java.util.*;

public class Main {
    private static final String ANSI_RESET  = "\u001B[0m";
    private static final String ANSI_GREEN  = "\u001B[32m";
    private static final String ANSI_RED    = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE   = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN   = "\u001B[36m";
    private static final String ANSI_BOLD   = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader();

        System.out.println("\n" + ANSI_CYAN + "╔════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(       ANSI_CYAN + "║  Cosa vuoi fare?                       ║" + ANSI_RESET);
        System.out.println(       ANSI_CYAN + "╚════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(     ANSI_YELLOW + "1." + ANSI_RESET + " Esegui test automatici");
        System.out.println(     ANSI_YELLOW + "2." + ANSI_RESET + " Usa l'albero interattivamente");
        System.out.print("\nScelta: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            runAutomatedTests();
        } else if (choice == 2) {
            interactiveMenu(scanner);
        } else {
            System.out.println(ANSI_RED + "Scelta non valida!" + ANSI_RESET);
        }

        scanner.close();
    }

    private static void printHeader() {
        System.out.println(ANSI_PURPLE + ANSI_BOLD);
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║        🌳  BINARY SEARCH TREE - TEST SUITE  🌳           ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println(ANSI_RESET);
    }

    private static void runAutomatedTests() {
        System.out.println("\n" + ANSI_CYAN + ANSI_BOLD + "🔬 Inizio Test Automatici..." + ANSI_RESET + "\n");

        int totalTests = 0;
        int passedTests = 0;

        // Test 1: Creazione albero vuoto
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 1: Creazione albero e inserimento root" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.root = new NodoImpl<>(50);
        if (tree1.root.get() == 50) {
            printTestResult(true, "Root inserita correttamente: 50", null);
            passedTests++;
        } else {
            String debug = "Valore root attuale: " + tree1.root.get();
            printTestResult(false, "Root non inserita correttamente", debug);
        }

        // Test 2: Inserimento ordinato - caso base
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 2: Inserimento ordinato - valori base" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree2 = new BinaryTree<>();
        tree2.root = new NodoImpl<>(50);
        tree2.addInOrder(40);  // Dovrebbe andare a sinistra
        tree2.addInOrder(70);  // Dovrebbe andare a destra

        boolean test2Passed = tree2.root.getSx() != null && tree2.root.getSx().get() == 30 &&
                tree2.root.getDx() != null && tree2.root.getDx().get() == 70;

        if (!test2Passed) {
            StringBuilder debug = new StringBuilder();
            debug.append("\n  Stato albero:\n");
            debug.append("  Root: ").append(tree2.root.get()).append("\n");
            debug.append("  Figlio sinistro: ").append(tree2.root.getSx() == null ? "null" : tree2.root.getSx().get()).append(" (atteso: 30)\n");
            debug.append("  Figlio destro: ").append(tree2.root.getDx() == null ? "null" : tree2.root.getDx().get()).append(" (atteso: 70)");
            printTestResult(false, "30 a sinistra, 70 a destra", debug.toString());
        } else {
            printTestResult(true, "30 a sinistra, 70 a destra", null);
            passedTests++;
        }

        // Test 3: Inserimento multiplo ordinato
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 3: Inserimento multiplo ordinato" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree3 = new BinaryTree<>();
        tree3.root = new NodoImpl<>(50);
        tree3.addInOrder(30);
        tree3.addInOrder(70);
        tree3.addInOrder(20);  // Sinistra di 30
        tree3.addInOrder(40);  // Destra di 30
        tree3.addInOrder(60);  // Sinistra di 70
        tree3.addInOrder(80);  // Destra di 70

        System.out.println("Struttura attesa:");
        System.out.println("        50");
        System.out.println("       /  \\");
        System.out.println("      30   70");
        System.out.println("     / \\  / \\");
        System.out.println("    20 40 60 80");

        boolean test3Passed =
                tree3.root.getSx() != null && tree3.root.getSx().getSx() != null && tree3.root.getSx().getSx().get() == 20 &&
                        tree3.root.getSx() != null && tree3.root.getSx().getDx() != null && tree3.root.getSx().getDx().get() == 40 &&
                        tree3.root.getDx() != null && tree3.root.getDx().getSx() != null && tree3.root.getDx().getSx().get() == 60 &&
                        tree3.root.getDx() != null && tree3.root.getDx().getDx() != null && tree3.root.getDx().getDx().get() == 80;

        if (!test3Passed) {
            StringBuilder debug = new StringBuilder();
            debug.append("\n  Struttura attuale:\n");
            debug.append("  Root: ").append(tree3.root.get()).append("\n");
            debug.append("  30->sx: ").append(tree3.root.getSx() != null && tree3.root.getSx().getSx() != null ? tree3.root.getSx().getSx().get() : "null").append(" (atteso: 20)\n");
            debug.append("  30->dx: ").append(tree3.root.getSx() != null && tree3.root.getSx().getDx() != null ? tree3.root.getSx().getDx().get() : "null").append(" (atteso: 40)\n");
            debug.append("  70->sx: ").append(tree3.root.getDx() != null && tree3.root.getDx().getSx() != null ? tree3.root.getDx().getSx().get() : "null").append(" (atteso: 60)\n");
            debug.append("  70->dx: ").append(tree3.root.getDx() != null && tree3.root.getDx().getDx() != null ? tree3.root.getDx().getDx().get() : "null").append(" (atteso: 80)\n");
            debug.append("  Struttura completa:\n");
            debug.append(getTreeStructure(tree3.root, "  ", true));
            printTestResult(false, "Tutti i nodi inseriti correttamente", debug.toString());
        } else {
            printTestResult(true, "Tutti i nodi inseriti correttamente", null);
            passedTests++;
        }

        // Test 4: Inserimento valori duplicati (>= va a destra)
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 4: Gestione valori duplicati (>= va a destra)" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree4 = new BinaryTree<>();
        tree4.root = new NodoImpl<>(50);
        tree4.addInOrder(50);  // Duplicato, dovrebbe andare a destra

        boolean test4Passed = tree4.root.getDx() != null && tree4.root.getDx().get() == 50;
        if (!test4Passed) {
            String debug = "\n  Figlio destro: " + (tree4.root.getDx() == null ? "null" : tree4.root.getDx().get()) + " (atteso: 50)\n" +
                    "  Figlio sinistro: " + (tree4.root.getSx() == null ? "null" : tree4.root.getSx().get()) + " (atteso: null)";
            printTestResult(false, "Valore duplicato inserito a destra", debug);
        } else {
            printTestResult(true, "Valore duplicato inserito a destra", null);
            passedTests++;
        }

        // Test 5: Inserimento ordinato crescente
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 5: Inserimento sequenziale crescente" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree5 = new BinaryTree<>();
        tree5.root = new NodoImpl<>(10);
        tree5.addInOrder(20);
        tree5.addInOrder(30);
        tree5.addInOrder(40);

        boolean test5Passed =
                tree5.root.getDx() != null && tree5.root.getDx().get() == 20 &&
                        tree5.root.getDx().getDx() != null && tree5.root.getDx().getDx().get() == 30 &&
                        tree5.root.getDx().getDx().getDx() != null && tree5.root.getDx().getDx().getDx().get() == 40;

        if (!test5Passed) {
            StringBuilder debug = new StringBuilder();
            debug.append("\n  Percorso atteso: 10->dx(20)->dx(30)->dx(40)\n");
            debug.append("  Percorso attuale: 10");
            NodoImpl<Integer> current = tree5.root.getDx();
            int depth = 0;
            while (current != null && depth < 4) {
                debug.append("->dx(").append(current.get()).append(")");
                current = current.getDx();
                depth++;
            }
            debug.append("\n  Struttura completa:\n");
            debug.append(getTreeStructure(tree5.root, "  ", true));
            printTestResult(false, "Albero sbilanciato a destra creato correttamente", debug.toString());
        } else {
            printTestResult(true, "Albero sbilanciato a destra creato correttamente", null);
            passedTests++;
        }

        // Test 6: Inserimento ordinato decrescente
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 6: Inserimento sequenziale decrescente" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<Integer> tree6 = new BinaryTree<>();
        tree6.root = new NodoImpl<>(40);
        tree6.addInOrder(30);
        tree6.addInOrder(20);
        tree6.addInOrder(10);

        boolean test6Passed =
                tree6.root.getSx() != null && tree6.root.getSx().get() == 30 &&
                        tree6.root.getSx().getSx() != null && tree6.root.getSx().getSx().get() == 20 &&
                        tree6.root.getSx().getSx().getSx() != null && tree6.root.getSx().getSx().getSx().get() == 10;

        if (!test6Passed) {
            StringBuilder debug = new StringBuilder();
            debug.append("\n  Percorso atteso: 40->sx(30)->sx(20)->sx(10)\n");
            debug.append("  Percorso attuale: 40");
            NodoImpl<Integer> current = tree6.root.getSx();
            int depth = 0;
            while (current != null && depth < 4) {
                debug.append("->sx(").append(current.get()).append(")");
                current = current.getSx();
                depth++;
            }
            debug.append("\n  Struttura completa:\n");
            debug.append(getTreeStructure(tree6.root, "  ", true));
            printTestResult(false, "Albero sbilanciato a sinistra creato correttamente", debug.toString());
        } else {
            printTestResult(true, "Albero sbilanciato a sinistra creato correttamente", null);
            passedTests++;
        }

        // Test 7: Visite dell'albero (output testing)
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 7: Verifica visite dell'albero" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        System.out.println(ANSI_YELLOW + "Albero di test: 50, 30, 70, 20, 40" + ANSI_RESET);
        BinaryTree<Integer> tree7 = new BinaryTree<>();
        tree7.root = new NodoImpl<>(50);
        tree7.addInOrder(30);
        tree7.addInOrder(70);
        tree7.addInOrder(20);
        tree7.addInOrder(40);

        System.out.println("\n" + ANSI_CYAN + "PreOrder (Radice-Sinistra-Destra):" + ANSI_RESET);
        tree7.viewPreOrder();

        System.out.println("\n" + ANSI_CYAN + "InOrder (Sinistra-Radice-Destra):" + ANSI_RESET);
        tree7.viewInOrder();

        System.out.println("\n" + ANSI_CYAN + "PostOrder (Sinistra-Destra-Radice):" + ANSI_RESET);
        tree7.viewPostOrder();

        printTestResult(true, "Visite eseguite (verifica visiva richiesta)", null);
        passedTests++;

        // Test 8: Test con stringhe
        System.out.println("\n" + ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "Test 8: Test con tipo String" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + ANSI_RESET);
        totalTests++;
        BinaryTree<String> tree8 = new BinaryTree<>();
        tree8.root = new NodoImpl<>("dog");
        tree8.addInOrder("cat");
        tree8.addInOrder("elephant");
        tree8.addInOrder("ant");

        boolean test8Passed =
                tree8.root.getSx() != null && tree8.root.getSx().get().equals("cat") &&
                        tree8.root.getDx() != null && tree8.root.getDx().get().equals("elephant") &&
                        tree8.root.getSx().getSx() != null && tree8.root.getSx().getSx().get().equals("ant");

        if (!test8Passed) {
            StringBuilder debug = new StringBuilder();
            debug.append("\n  Struttura attuale:\n");
            debug.append("  Root: ").append(tree8.root.get()).append("\n");
            debug.append("  Figlio sx: ").append(tree8.root.getSx() == null ? "null" : tree8.root.getSx().get()).append(" (atteso: cat)\n");
            debug.append("  Figlio dx: ").append(tree8.root.getDx() == null ? "null" : tree8.root.getDx().get()).append(" (atteso: elephant)\n");
            debug.append("  cat->sx: ").append(tree8.root.getSx() != null && tree8.root.getSx().getSx() != null ? tree8.root.getSx().getSx().get() : "null").append(" (atteso: ant)\n");
            debug.append("  Struttura completa:\n");
            debug.append(getTreeStructureString(tree8.root, "  ", true));
            printTestResult(false, "Albero di stringhe ordinato correttamente", debug.toString());
        } else {
            printTestResult(true, "Albero di stringhe ordinato correttamente", null);
            passedTests++;
        }

        // Calcolo punteggio finale
        printFinalScore(passedTests, totalTests);
    }

    private static void printTestResult(boolean passed, String message, String debugInfo) {
        if (passed) {
            System.out.println(ANSI_GREEN + "✓ PASSED: " + ANSI_RESET + message);
        } else {
            System.out.println(ANSI_RED + "✗ FAILED: " + ANSI_RESET + message);
            if (debugInfo != null && !debugInfo.isEmpty()) {
                System.out.println(ANSI_YELLOW + "  📋 Debug Info:" + ANSI_RESET + debugInfo);
            }
        }
    }

    private static String getTreeStructure(NodoImpl<Integer> node, String prefix, boolean isTail) {
        if (node == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(node.get()).append("\n");

        if (node.getSx() != null || node.getDx() != null) {
            if (node.getSx() != null) {
                sb.append(getTreeStructure(node.getSx(), prefix + (isTail ? "    " : "│   "), node.getDx() == null));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("├── ").append("null\n");
            }
            if (node.getDx() != null) {
                sb.append(getTreeStructure(node.getDx(), prefix + (isTail ? "    " : "│   "), true));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("└── ").append("null\n");
            }
        }
        return sb.toString();
    }

    private static String getTreeStructureString(NodoImpl<String> node, String prefix, boolean isTail) {
        if (node == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(node.get()).append("\n");

        if (node.getSx() != null || node.getDx() != null) {
            if (node.getSx() != null) {
                sb.append(getTreeStructureString(node.getSx(), prefix + (isTail ? "    " : "│   "), node.getDx() == null));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("├── ").append("null\n");
            }
            if (node.getDx() != null) {
                sb.append(getTreeStructureString(node.getDx(), prefix + (isTail ? "    " : "│   "), true));
            } else {
                sb.append(prefix).append(isTail ? "    " : "│   ").append("└── ").append("null\n");
            }
        }
        return sb.toString();
    }


    private static void printTestResult(boolean passed, String message) {
        if (passed) {
            System.out.println(ANSI_GREEN + "✓ PASSED: " + ANSI_RESET + message);
        } else {
            System.out.println(ANSI_RED + "✗ FAILED: " + ANSI_RESET + message);
        }
    }

    private static void printFinalScore(int passed, int total) {
        System.out.println("\n" + ANSI_PURPLE + "═══════════════════════════════════════════════════════════" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "                    RISULTATI FINALI                        " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "═══════════════════════════════════════════════════════════" + ANSI_RESET);

        double percentage = (double) passed / total * 100;

        System.out.println("\n" + ANSI_BOLD + "Test superati: " + ANSI_RESET + passed + " / " + total);
        System.out.println(ANSI_BOLD + "Percentuale: " + ANSI_RESET + String.format("%.2f%%", percentage));

        // Barra di progresso
        System.out.print("\n[");
        int barLength = 50;
        int filledLength = (int) (barLength * passed / total);
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                System.out.print(ANSI_GREEN + "█" + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + "░" + ANSI_RESET);
            }
        }
        System.out.println("]");

        // Valutazione
        System.out.println("\n" + ANSI_BOLD + "Valutazione: " + ANSI_RESET);
        if (percentage == 100) {
            System.out.println(ANSI_GREEN + "🌟 ECCELLENTE! Tutti i test superati! 🌟" + ANSI_RESET);
        } else if (percentage >= 80) {
            System.out.println(ANSI_CYAN + "👍 OTTIMO! La maggior parte dei test superati!" + ANSI_RESET);
        } else if (percentage >= 60) {
            System.out.println(ANSI_YELLOW + "⚠ DISCRETO. Ci sono ancora problemi da risolvere." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "❌ INSUFFICIENTE. Molti test falliti." + ANSI_RESET);
        }

        System.out.println("\n" + ANSI_PURPLE + "═══════════════════════════════════════════════════════════" + ANSI_RESET);
    }

    private static void interactiveMenu(Scanner scanner) {
        BinaryTree<Integer> userTree = new BinaryTree<>();
        boolean hasRoot = false;

        while (true) {
            System.out.println("\n" + ANSI_CYAN + "╔════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "║        MENU ALBERO BINARIO             ║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "╚════════════════════════════════════════╝" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1." + ANSI_RESET + " Inserisci root");
            System.out.println(ANSI_YELLOW + "2." + ANSI_RESET + " Aggiungi nodo ordinato");
            System.out.println(ANSI_YELLOW + "3." + ANSI_RESET + " Visualizza PreOrder");
            System.out.println(ANSI_YELLOW + "4." + ANSI_RESET + " Visualizza InOrder");
            System.out.println(ANSI_YELLOW + "5." + ANSI_RESET + " Visualizza PostOrder");
            System.out.println(ANSI_YELLOW + "6." + ANSI_RESET + " Visualizza struttura albero");
            System.out.println(ANSI_YELLOW + "7." + ANSI_RESET + " Crea albero di esempio");
            System.out.println(ANSI_YELLOW + "8." + ANSI_RESET + " Genera albero casuale");
            System.out.println(ANSI_YELLOW + "0." + ANSI_RESET + " Esci");
            System.out.print("\nScelta: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Inserisci valore root: ");
                    int rootVal = scanner.nextInt();
                    userTree = new BinaryTree<>();
                    userTree.root = new NodoImpl<>(rootVal);
                    hasRoot = true;
                    System.out.println(ANSI_GREEN + "✓ Root inserita: " + rootVal + ANSI_RESET);
                    break;

                case 2:
                    if (!hasRoot) {
                        System.out.println(ANSI_RED + "❌ Errore: Devi prima inserire la root!" + ANSI_RESET);
                        break;
                    }
                    System.out.print("Inserisci valore da aggiungere: ");
                    int val = scanner.nextInt();
                    userTree.addInOrder(val);
                    System.out.println(ANSI_GREEN + "✓ Valore " + val + " aggiunto!" + ANSI_RESET);
                    break;

                case 3:
                    if (!hasRoot) {
                        System.out.println(ANSI_RED + "❌ Albero vuoto!" + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_CYAN + "\nPreOrder:" + ANSI_RESET);
                    userTree.viewPreOrder();
                    break;

                case 4:
                    if (!hasRoot) {
                        System.out.println(ANSI_RED + "❌ Albero vuoto!" + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_CYAN + "\nInOrder:" + ANSI_RESET);
                    userTree.viewInOrder();
                    break;

                case 5:
                    if (!hasRoot) {
                        System.out.println(ANSI_RED + "❌ Albero vuoto!" + ANSI_RESET);
                        break;
                    }
                    System.out.println(ANSI_CYAN + "\nPostOrder:" + ANSI_RESET);
                    userTree.viewPostOrder();
                    break;

                case 6:
                    if (!hasRoot) {
                        System.out.println(ANSI_RED + "❌ Albero vuoto!" + ANSI_RESET);
                        break;
                    }
                    System.out.println(userTree);
                    break;

                case 7:
                    System.out.println(ANSI_YELLOW + "Creazione albero di esempio: 50, 30, 70, 20, 40, 60, 80" + ANSI_RESET);
                    userTree = new BinaryTree<>();
                    userTree.root = new NodoImpl<>(50);
                    userTree.addInOrder(30);
                    userTree.addInOrder(70);
                    userTree.addInOrder(20);
                    userTree.addInOrder(40);
                    userTree.addInOrder(60);
                    userTree.addInOrder(80);
                    hasRoot = true;
                    System.out.println(ANSI_GREEN + "✓ Albero di esempio creato!" + ANSI_RESET);
                    System.out.println(userTree);
                    break;

                case 8:
                    userTree = generateRandomTree(scanner);
                    hasRoot = userTree.root != null;
                    if (hasRoot) {
                        System.out.println(ANSI_GREEN + "✓ Albero casuale generato!" + ANSI_RESET);
                        System.out.println(userTree);
                    }
                    break;

                case 0:
                    System.out.println(ANSI_PURPLE + "\n👋 Arrivederci!" + ANSI_RESET);
                    return;

                default:
                    System.out.println(ANSI_RED + "❌ Scelta non valida!" + ANSI_RESET);
            }
        }
    }

    private static void printTree(NodoImpl<Integer> node, String prefix, boolean isTail) {
        if (node == null) return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + ANSI_BOLD + node.get() + ANSI_RESET);

        if (node.getSx() != null || node.getDx() != null) {
            if (node.getSx() != null) {
                printTree(node.getSx(), prefix + (isTail ? "    " : "│   "), node.getDx() == null);
            }
            if (node.getDx() != null) {
                printTree(node.getDx(), prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    private static BinaryTree<Integer> generateRandomTree(Scanner scanner) {
        System.out.println("\n" + ANSI_CYAN + "╔════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "║      GENERATORE ALBERO CASUALE         ║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╚════════════════════════════════════════╝" + ANSI_RESET);

        System.out.print("\nQuanti nodi vuoi generare? (1-50): ");
        int nodeCount = scanner.nextInt();

        if (nodeCount < 1 || nodeCount > 50) {
            System.out.println(ANSI_RED + "❌ Numero non valido! Uso valore predefinito: 10" + ANSI_RESET);
            nodeCount = 10;
        }

        System.out.print("Range valori (min): ");
        int minValue = scanner.nextInt();
        System.out.print("Range valori (max): ");
        int maxValue = scanner.nextInt();

        if (minValue >= maxValue) {
            System.out.println(ANSI_RED + "❌ Range non valido! Uso range predefinito: 1-100" + ANSI_RESET);
            minValue = 1;
            maxValue = 100;
        }

        System.out.print("\nVuoi permettere valori duplicati? (s/n): ");
        scanner.nextLine(); // Consuma il newline
        String allowDuplicates = scanner.nextLine().toLowerCase();
        boolean duplicatesAllowed = allowDuplicates.equals("s") || allowDuplicates.equals("si");

        BinaryTree<Integer> tree = new BinaryTree<>();
        Random random = new Random();
        Set<Integer> usedValues = new HashSet<>();

        System.out.println("\n" + ANSI_YELLOW + "🎲 Generazione in corso..." + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "Valori inseriti: " + ANSI_RESET);

        int insertedCount = 0;
        int attempts = 0;
        int maxAttempts = nodeCount * 10; // Evita loop infiniti

        while (insertedCount < nodeCount && attempts < maxAttempts) {
            int value = random.nextInt(maxValue - minValue + 1) + minValue;

            if (duplicatesAllowed || !usedValues.contains(value)) {
                if (insertedCount == 0) {
                    tree.root = new NodoImpl<>(value);
                } else {
                    tree.addInOrder(value);
                }

                usedValues.add(value);
                System.out.print(value);
                if (insertedCount < nodeCount - 1) {
                    System.out.print(", ");
                }
                insertedCount++;
            }
            attempts++;
        }

        System.out.println("\n");

        if (insertedCount < nodeCount) {
            System.out.println(ANSI_YELLOW + "⚠ Attenzione: Generati solo " + insertedCount + " nodi (range troppo piccolo o troppi duplicati)" + ANSI_RESET);
        }

        // Statistiche
        System.out.println(ANSI_CYAN + "📊 Statistiche albero:" + ANSI_RESET);
        System.out.println("   • Nodi totali: " + insertedCount);
        System.out.println("   • Range: [" + minValue + ", " + maxValue + "]");
        System.out.println("   • Duplicati: " + (duplicatesAllowed ? "Sì" : "No"));
        System.out.println("   • Altezza massima teorica: ~" + (int)Math.ceil(Math.log(insertedCount + 1) / Math.log(2)));

        return tree;
    }
}