import java.sql.SQLOutput;
import java.util.Scanner;

import static utilities.General.input;

public class Main {
    public static void main(String[] args) {

        String[][] ps = {
                {"paolo", "Rossi"},
                {"luca", "Bianchi"},
                {"francesco", "Ariosto"},
                {"mario", "Gialli"}
        };
        Rubrica r = new Rubrica();

        //init default
        for (String[] p : ps) {
            String std_mail = "name.sname@mail.dom";
            String std_tel  = "+39 123 456 7890";
            r.add(new Persona(p[0], p[1], std_mail, std_tel));
        }

        //Todo:
        menu(r);
    }

    public static void menu(Rubrica r) {
        System.out.println("\n\n==== Menu' ====");
        System.out.println("1. Aggiungi contatto");
        System.out.println("2. Rimuovi contatto");
        System.out.println("3. Cerca contatto");
        System.out.println("4. Visualizza lista");
        System.out.println("5. Esci");
        System.out.println();

        String in = input("Seleziona la funzione: ", "");
        System.out.println();

        if (in.equals("5")) {
            System.out.println("Arrivederci e a presto!");
            return;
        }

        switch (in) {
            case "1" -> {
                System.out.println("== Aggiungi contatto ==");
                String name    = input("Inserisci il nome: ", "");
                String surname = input("Inserisci il cognome: ", "");
                String email   = input("Inserisci l'email: ", "");
                String phone   = input("Inserisci il tele-phone: ", "");

                Persona p = new Persona(name, surname, email, phone);

                r.add(p);

                System.out.println("\nContatto aggiunto correttamente!");
            }
            case "2" -> {
                System.out.println("== Rimuovi contatto ==");

                String name    = input("Inserisci il nome: ", "");
                String surname = input("Inserisci il cognome: ", "");

                boolean b = r.delete(surname, name);
                if  (b) System.out.println("Contatto eliminato correttamente!");
                else    System.out.println("Contatto non eliminato correttamente!");

            }
            case "3" -> {
                System.out.println("== Cerca contatto ==");
                String name    = input("Inserisci il nome: ", "");
                String surname = input("Inserisci il cognome: ", "");

                int pos = r.search(surname, name);

                if (pos >= 0) {
                    System.out.println("Il contatto \""+ surname + " " + name + "\" è il " + (pos+1)+ "° nella rubrica");
                } else {
                    System.out.println("Il contatto non è presente in rubrica");
                }
            }
            case "4" -> {
                System.out.println("== Visualizza lista ==");

                System.out.println(r);
            }
            default -> System.err.println("Opid non valido");
        }
        menu(r);
    }
}