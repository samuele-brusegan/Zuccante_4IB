// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Coda<String> c = new Coda<>();

        c.enqueue("Paolo di Tarso");
        c.enqueue("Baden Power");
        c.enqueue("Caterina da Siena");
        c.enqueue("Francesco d'Assisi");
        c.enqueue("Giorgio (quello del drago)");
        c.enqueue("Giovanna (la francese)");

        System.out.println(c);

        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println(c.dequeue());
        }

        System.out.println();

        System.out.println(c);

    }
}