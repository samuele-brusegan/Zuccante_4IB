import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist<Brano> playlist = new Playlist<>();
        boolean running = true;

        System.out.println("=== GESTORE PLAYLIST ===\n");

        while (running) {
            printMenu();
            System.out.print("Scegli un'opzione: ");
            
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    addBranoNext(scanner, playlist);
                    break;
                case 2:
                    addBranoPrev(scanner, playlist);
                    break;
                case 3:
                    showCurrent(playlist);
                    break;
                case 4:
                    forward(playlist);
                    break;
                case 5:
                    backward(playlist);
                    break;
                case 6:
                    removeCurrent(playlist);
                    break;
                case 7:
                    showPlaylist(playlist);
                    break;
                case 8:
                    showSize(playlist);
                    break;
                case 9:
                    exportPlaylist(scanner, playlist);
                    break;
                case 10:
                    importPlaylist(scanner, playlist);
                    break;
                case 0:
                    running = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida!\n");
            }
        }
        
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Aggiungi brano dopo il corrente");
        System.out.println("2. Aggiungi brano prima del corrente");
        System.out.println("3. Mostra brano corrente");
        System.out.println("4. Vai al brano successivo");
        System.out.println("5. Vai al brano precedente");
        System.out.println("6. Rimuovi brano corrente");
        System.out.println("7. Mostra tutta la playlist");
        System.out.println("8. Mostra dimensione playlist");
        System.out.println("9. Esporta playlist su file");
        System.out.println("10. Importa playlist da file");
        System.out.println("0. Esci");
        System.out.println("------------");
    }

    private static void addBranoNext(Scanner scanner, Playlist<Brano> playlist) {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        
        Brano brano = new Brano(titolo, artista);
        playlist.addAsNext(brano);
        System.out.println("✓ Brano aggiunto dopo il corrente\n");
    }
    private static void addBranoPrev(Scanner scanner, Playlist<Brano> playlist) {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        
        Brano brano = new Brano(titolo, artista);
        playlist.addAsPrev(brano);
        System.out.println("✓ Brano aggiunto prima del corrente\n");
    }
    private static void showCurrent(Playlist<Brano> playlist) {
        if (playlist.isEmpty()) {
            System.out.println("La playlist è vuota!\n");
        } else {
            System.out.println("Brano corrente: " + playlist.getCurrent() + "\n");
        }
    }
    private static void forward(Playlist<Brano> playlist) {
        if (playlist.isEmpty()) {
            System.out.println("La playlist è vuota!\n");
        } else {
            playlist.forward();
            System.out.println("✓ Passato al brano successivo\n");
        }
    }
    private static void backward(Playlist<Brano> playlist) {
        if (playlist.isEmpty()) {
            System.out.println("La playlist è vuota!\n");
        } else {
            playlist.backward();
            System.out.println("✓ Passato al brano precedente\n");
        }
    }
    private static void removeCurrent(Playlist<Brano> playlist) {
        if (playlist.isEmpty()) {
            System.out.println("La playlist è vuota!\n");
        } else {
            playlist.rmCurr();
            System.out.println("✓ Brano corrente rimosso\n");
        }
    }
    private static void showPlaylist(Playlist<Brano> playlist) {
        System.out.println("Playlist: " + playlist.toString() + "\n");
    }
    private static void showSize(Playlist<Brano> playlist) {
        System.out.println("Dimensione playlist: " + playlist.size() + " brani\n");
    }
    private static void exportPlaylist(Scanner scanner, Playlist<Brano> playlist) {
        System.out.print("Nome file (es. playlist.csv): ");
        String filename = scanner.nextLine();
        playlist.exportFile(filename);
        System.out.println("✓ Playlist esportata in " + filename + "\n");
    }
    private static void importPlaylist(Scanner scanner, Playlist<Brano> playlist) {
        System.out.print("Nome file (es. playlist.csv): ");
        String filename = scanner.nextLine();
        try {
            playlist.importFile(filename);
            System.out.println("✓ Playlist importata da " + filename + "\n");
        } catch (Exception e) {
            System.out.println("✗ Errore durante l'importazione: " + e.getMessage() + "\n");
        }
    }
}