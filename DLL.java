/**
 * This class handle functions involving a doubly linked list.
 * 
 * @author James Donohue
 */

public class DLL<T> {
    private Node<T> header;
    private Node<T> trailer;

    public DLL() {
        header = null;
        trailer = null;
    }

    public Node<T> getHeader() {
        return header;
    }

    public Node<T> getTrailer() {
        return trailer;
    }

    public void setHeader(Node<T> header) {
        this.header = header;
    }

    public void setTrailer(Node<T> trailer) {
        this.trailer = trailer;
    }

    public void print() {
        Node<T> current = header;
        while (current != null) {
            System.out.println(current.getElement());
            current = current.getNext();
        }
        System.out.println();
    }

    public void addFirst(T element) {
        Node<T> newNode = new Node<T>(element, null, null);
        if (header == null) {
            header = newNode;
            trailer = newNode;
        } else {
            newNode.setNext(header);
            header.setPrev(newNode);
            header = newNode;
        }
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<T>(element, null, null);
        if (header == null) {
            header = newNode;
            trailer = newNode;
        } else {
            trailer.setNext(newNode);
            newNode.setPrev(trailer);
            trailer = newNode;
        }
    }

    public void remove(Node<T> element) {
        Node<T> current = header;
        while (current != null) {
            if (current.getElement().equals(element)) {
                if (current.equals(header) && current.equals(trailer)) {
                    header = null;
                    trailer = null;
                } else if (current.equals(header)) {
                    header = current.getNext();
                    header.setPrev(null);
                } else if (current.equals(trailer)) {
                    trailer = current.getPrev();
                    trailer.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                return;
            }
            current = current.getNext();
        }
    }

    public boolean contains(T element) {
        Node<T> current = header;
        while (current != null) {
            if (current.getElement().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void reverse() {
        Node<T> current = header;
        Node<T> temp = null;
        while (!current.equals(null)) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev();
        }
        if (!temp.equals(null)) {
            header = temp.getPrev();
        }
    }

    public boolean isEmpty() {
        return header == null;
    }

    public int indexOf(T element) {
        int index = 0;
        Node<T> currNode = header;
        while (currNode != null) {
            if (currNode.getElement().equals(element)) {
                return index;
            }
            index++;
            currNode = currNode.getNext();
        }
        return -1;
    }

    public Node<T> getNode(T element) {
        Node<T> current = header;
        while (current != null) {
            if (current.getElement().equals(element)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}