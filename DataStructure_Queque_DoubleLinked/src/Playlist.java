import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Playlist<T> {
    private NodoImpl<T> head;
    private NodoImpl<T> curr;
    private int len;

    public Playlist() {
        this.head = null;
        this.len = 0;
    }

    public void addAsNext(T brano) {
        NodoImpl<T> newNode = new NodoImpl<>(brano);

        if(head == null){
            head = newNode;
            return;
        }

        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
        newNode.setPrev(curr);
        newNode.getNext().setPrev(newNode);
        this.len++;
    }
    public void addAsPrev(T brano) {
        NodoImpl<T> newNode = new NodoImpl<>(brano);
        if(head == null){
            head = newNode;
            this.len++;
            return;
        }

        if(curr == head) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            this.len++;
            return;
        }

        curr.getPrev().setNext(newNode);
        newNode.setNext(curr);
        newNode.setPrev(curr.getPrev());
        curr.setPrev(newNode);

        this.len++;
    }
    public String getCurrent() {
        return curr.get().toString();

    }

    public void forward() {
        if(curr.getNext() != null){
            curr = curr.getNext();
        }
    }
    public void backward() {
        if(curr.getPrev() != null){
            curr = curr.getPrev();
        }
    }

    public void rmCurr() {
        if( (curr.getPrev() == null && curr.getNext() == null) || curr == null) {
            curr = null;
            this.len = 0;
            return;
        }

        if(curr.getPrev() == null) {
            curr = curr.getNext();
            curr.setPrev(null);
        }
        if(curr.getNext() == null) {
            curr = curr.getPrev();
            curr.setNext(null);
        }
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
        curr = curr.getNext();
        this.len--;
    }

    public int size() {
        return this.len;

    }

    public boolean isEmpty() {
        return (this.len == 0);

    }

    public void exportFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            NodoImpl<T> current = head;
            while (current != null) {
                writer.println(current.get().toString());
                current = current.getNext();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    //TODO: finish this method
    public void importFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    addAsNext((T) line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        NodoImpl<T> n = this.head;
        StringBuilder out = new StringBuilder("{ ");
        for (int i = 0; n != null; i++) {
            out.append(n.get().toString());
            n = n.getNext();
            if ( n != null ) out.append(", ");
        }
        out.append(" }");
        return out.toString();
    }
}

class NodoImpl<T> {
    private NodoImpl<T> next;
    private NodoImpl<T> prev;
    private T val;

    public NodoImpl(T val) {
        this.next = null;
        this.val = val;
    }

    public void setNext(NodoImpl<T> n) {
        this.next = n;
    }
    public NodoImpl<T> getNext() {
        return next;
    }

    public void setPrev(NodoImpl<T> n) {
        this.prev = n;
    }
    public NodoImpl<T> getPrev() {
        return prev;
    }

    public T get() {
        return val;
    }

    public void set(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val+"";
    }

}
