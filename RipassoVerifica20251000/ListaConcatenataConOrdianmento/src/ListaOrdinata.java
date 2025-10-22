public class ListaOrdinata<T extends Comparable<T> > {
    NodeImpl<T> head;
    int size;

    public ListaOrdinata(){}

    public void add(T valueToAdd){
        NodeImpl<T> nodeToAdd = new NodeImpl<>(valueToAdd);
        if(head == null){
            head = nodeToAdd;
            size++;
            return;
        }

        //Va messo prima di head
        if (head.get().compareTo(valueToAdd) >= 0){
            nodeToAdd.setNext(head);
            head = nodeToAdd;
            size++;
            return;
        }

        //Creo un nodo fantasma prima di head
        NodeImpl<T> i = new NodeImpl<>(null, head);
        for(; i.getNext() != null; i = i.getNext()){
            T nextVal = i.getNext().get();

            //se (nextVal > valueToAdd)
            if (nextVal.compareTo(valueToAdd) > 0){
                nodeToAdd.setNext(i.getNext());
                i.setNext(nodeToAdd);
                size++;
                return;
            }
        }

        //Va messo come ultimo elemento
        if (i.getNext() == null){
            i.setNext(nodeToAdd);
            size++;
        }
    }
    public boolean remove(T value){

        if(head == null) return false;

        if(head.get().equals(value)){
            head = head.getNext();
            size--;
            return true;
        }

        NodeImpl<T> i = new NodeImpl<>(null);
        i.setNext(head);
        for(; i.getNext() != null; i = i.getNext()){
            if(i.getNext().get().equals(value)){
                i.setNext(i.getNext().getNext());
                size--;
                return true;
            }
        }
        return false;
    }
    public int size(){
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodeImpl<T> current = head;

        while (current != null) {
            sb.append(current.get());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}

class NodeImpl<T> {
    private final T value;
    private NodeImpl<T> next;

    public NodeImpl(T value) {
        this.value = value;
    }
    public NodeImpl(T value, NodeImpl<T> next) {
        this.value = value;
        this.next = next;
    }


    public T get() {
        return value;
    }
    public void setNext(NodeImpl<T> next){
        this.next = next;
    }
    public NodeImpl<T> getNext(){
        return next;
    }

    public String rec() {
        if(next == null) return "Node{" + "(" + value + ")}";
        if(next == this) return "Node{" + "(" + value + "), next=this}";
        return "Node{" + "(" + value + "), next=" + next.rec() + '}';
    }
    
    
}
