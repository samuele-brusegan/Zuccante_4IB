import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);
        System.out.println("==== Menu' ====");
        System.out.println("1. Aggiungi contatto");
        System.out.println("2. Rimuovi contatto");
        System.out.println("3. Cerca contatto");
        System.out.println("4. Visualizza lista");





    }
}