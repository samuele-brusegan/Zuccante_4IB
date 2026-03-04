import java.io.*;

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

        if (head == null) {
            head = newNode;
            curr = newNode;
            this.len++;
            return;
        }

        if (curr.getNext() == null) {
            curr.setNext(newNode);
            newNode.setPrev(curr);
            this.len++;
            return;
        }

        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
        newNode.setPrev(curr);
        System.out.println(newNode);
        newNode.getNext().setPrev(newNode);
        this.len++;
    }
    public void addAsPrev(T brano) {
        NodoImpl<T> newNode = new NodoImpl<>(brano);
        if (head == null) {
            head = newNode;
            curr = newNode;
            this.len++;
            return;
        }

        if (curr == head) {
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
        if (curr.getNext() != null) {
            curr = curr.getNext();
        }
    }
    public void backward() {
        if (curr.getPrev() != null) {
            curr = curr.getPrev();
        }
    }
    public void rmCurr() {
        if ((curr.getPrev() == null && curr.getNext() == null) || curr == null) {
            curr = null;
            this.len = 0;
            return;
        }

        if (curr.getPrev() == null) {
            curr = curr.getNext();
            curr.setPrev(null);
        }
        if (curr.getNext() == null) {
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

        //Encode in CSV il brano
        NodoImpl<Brano> i = (NodoImpl<Brano>) this.head;
        while (i != null) {
            
            String branoCSV = i.get().getTitolo() + "," + i.get().getArtista();
            
            //Scrivo su file
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
                
                bw.write(branoCSV);
                bw.newLine();
                bw.close();
                
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            i = i.getNext();
        }

    }
    public void importFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Brano brano = new Brano(parts[0], parts[1]);
                    this.addAsNext((T) brano);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        NodoImpl<T> n = this.head;
        StringBuilder out = new StringBuilder("{ ");
        for (int i = 0; n != null; i++) {
            out.append(n.get().toString());
            n = n.getNext();
            if (n != null) out.append(", ");
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
        String out = "NodoImpl{" + "hashCode=" + hashCode();
        if (next != null) out += ", next=" + next.hashCode();
        if (prev != null) out += ", prev=" + prev.hashCode();
        out += ", val=" + val + '}';
        return out;
    }
}
