import List.Nodo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Rubrica {

    //Aggiunta Ordinata
    //Ricerca
    //Eliminazione
    //View (ordine alfabetico)

    private Nodo<Persona> head;
    private int len;

    public Rubrica() {
        this.head = null;
        this.len = 0;
    }

    public int size() {
        return this.len;
    }

    public boolean isEmpty() {
        return (this.len == 0);
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public String toString() {
        Nodo<Persona> n = this.head;
        StringBuilder outBuilder = new StringBuilder("{ ");
        for (int i = 0; n != null; i++) {
            outBuilder.append(n.toString());
            n = n.getNext();
            if ( n != null ) outBuilder.append(", ");
        }
        String out = outBuilder.toString();
        out += " }";
        return out;
    }

    public void add(Persona element) {
        Nodo<Persona> nToAdd = new Nodo<>(element);
        Persona pToAdd = nToAdd.get();

        if (this.head == null) {
            this.head = nToAdd;
            return;
        }

        if ( pToAdd.isLowerThan(this.head.get()) ) {
            nToAdd.setNext(this.head);
            this.head = nToAdd;
        }


        //TODO: Beautify
        boolean flag = false;
        Nodo<Persona> i = this.head;
//        System.out.println("pToAdd: " + pToAdd.getFullName());
        while ( i.getNext() != null && !flag ) {
            Persona pNext = i.getNext().get();

            if ( pToAdd.isLowerThan(pNext) ) {

                Nodo<Persona> tmp = i.getNext();
                i.setNext(nToAdd);
                nToAdd.setNext(tmp);
                flag = true;
            }

            i = i.getNext();
        }

        //Se non ho inserito il nodo prima della fine
        if ( !flag ) i.setNext(nToAdd);

    }

    public int search(String surname, String name) {

        Nodo<Persona> n = this.head;
        String fullName = surname + " " + name;
        for (int i = 0; n != null; i++) {
            if (n.get().getFullName().equals(fullName)) return i;
            n = n.getNext();
        }
        return -1;
    }

    public boolean delete(String surname, String name) {

        int i = search(surname, name);
        return delete(i);
    }

    private boolean delete(int index) {

        if (index == -1) return false;
        if (index == 0) {
            this.head = this.head.getNext();
            return true;
        }

        Nodo<Persona> n = this.head;
        for (int i = 0; n.getNext() != null; i++) {
            if (i+1 == index) {
                //Metto il puntatore saltando il nodo da eliminare
                n.setNext(n.getNext().getNext());

                return true;
            }
            n = n.getNext();
        }

        return false;
    }

    public boolean export(String filename) {

        //Se il filename non finisce per .csv aggiungo .csv
        if (!filename.toLowerCase().endsWith(".csv")) {
            filename += ".csv";
        }


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            Nodo<Persona> i = this.head;
            while ( i != null ) {
                String out = i.get().toCSV();
                bw.write(out);
                i = i.getNext();
            }

            bw.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
