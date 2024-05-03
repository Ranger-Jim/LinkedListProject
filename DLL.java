/**
 * This class handles functions associated with doubly linked lists.
 * 
 * @author James Donohue
 */

public class DLL<T> {
    private Node<T> head;
    private Node<T> tail;

    public DLL() {
        this.head = null;
        this.tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void insertAtHead(T data) {
        Node<T> newNode = new Node<T>(data, null, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void insertAtTail(T data) {
        Node<T> newNode = new Node<T>(data, null, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
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
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.getElement().equals(data)) {
                if (currNode == head && currNode == tail) {
                    head = null;
                    tail = null;
                } else if (currNode == head) {
                    head = currNode.getNext();
                    head.setPrev(null);
                } else if (currNode == tail) {
                    tail = tail.getPrev();
                    tail.setNext(null);
                } else {
                    currNode.getPrev().setNext(currNode.getNext());
                    currNode.getNext().setPrev(currNode.getPrev());
                }
                return;
            }
            currNode = currNode.getNext();
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

    public Node<T> getNode(T element) {
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.getElement().equals(element)) {
                return currNode;
            }
            currNode = currNode.getNext();
        }
        return null;
    }
}
