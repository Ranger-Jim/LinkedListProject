/**
 * This class handles functions associated with singly linked lists.
 * 
 * @author James Donohue
 */

public class SLL<T> {
    private Node<T> head = null;

    public SLL() {
        head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void insert(T data) {
        if (head == null) {
            head = new Node<T>(data, null);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<T>(data, null));
        }
    }

    public boolean contains(T element) {
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.getElement().equals(element)) {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.getElement().equals(data)) {
            head = head.getNext();
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getElement().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }

    public void print() {
        Node<T> currNode = head;

        while (currNode != null) {
            System.out.print(currNode.getElement() + " ");
            currNode = currNode.getNext();
        }
        System.out.println();
    }

    public void reverse() {
        if (head == null || head.getNext() == null) {
            System.out.println("The list is either empty or has only one element...");
        } else {
            Node<T> prevNode = null;
            Node<T> currNode = head;

            while (currNode.getNext() != null) {
                Node<T> temp = currNode.getNext();
                currNode.setNext(prevNode);
                prevNode = currNode;
                currNode = temp;
            }
            currNode.setNext(prevNode);
            head = currNode;
        }
    }

    public Node<T> getNode(T element) {
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.getElement().equals(element)) {
                return currNode; // Element found, return the node
            }
            currNode = currNode.getNext();
        }
        return null; // Element not found in the list
    }
}
