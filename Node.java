public class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> prev; // for DLL
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    // For SLL
    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    // For DLL
    public Node(T element, Node<T> next, Node<T> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    // for BST
    public Node(T element, Node<T> parent, Node<T> left, Node<T> right) {
        this.element = element;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getLeftChild() {
        return left;
    }

    public Node<T> getRightChild() {
        return right;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node<T> leftChild) {
        left = leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        right = rightChild;
    }

    // Additional methods for BST
    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

}
