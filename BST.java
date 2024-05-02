public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        root = null;
    }

    public void insert(T key) {
        root = insertRec(root, null, key);
    }

    private Node<T> insertRec(Node<T> root, Node<T> parent, T key) {
        if (root == null) {
            root = new Node<T>(key, parent, null, null);
        } else if (key.compareTo(root.getElement()) < 0) {
            root.setLeftChild(insertRec(root.getLeftChild(), root, key));
        } else if (key.compareTo(root.getElement()) > 0) {
            root.setRightChild(insertRec(root.getRightChild(), root, key));
        }
        return root;
    }

    public Node<T> search(T key) {
        return searchRec(root, key);
    }

    private Node<T> searchRec(Node<T> root, T key) {
        if (root == null || root.getElement().compareTo(key) == 0) {
            return root;
        }

        if (key.compareTo(root.getElement()) < 0) {
            return searchRec(root.getLeftChild(), key);
        } else {
            return searchRec(root.getRightChild(), key);
        }
    }

    public void inorderTraversal() {
        inorderTraversalRec(root);
        System.out.println();
    }

    private void inorderTraversalRec(Node<T> root) {
        if (root != null) {
            inorderTraversalRec(root.getLeftChild());
            System.out.print(root.getElement() + " ");
            inorderTraversalRec(root.getRightChild());
        }
    }

    public void remove(T key) {
        root = removeRec(root, key);
    }

    private Node<T> removeRec(Node<T> root, T key) {
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.getElement());
        if (cmp < 0) {
            root.setLeftChild(removeRec(root.getLeftChild(), key));
        } else if (cmp > 0) {
            root.setRightChild(removeRec(root.getRightChild(), key));
        } else {
            // Node to be deleted found

            // Node with only one child or no child
            if (root.getLeftChild() == null) {
                return root.getRightChild();
            } else if (root.getRightChild() == null) {
                return root.getLeftChild();
            }

            // Node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.setElement(minValue(root.getRightChild()));

            // Delete the inorder successor
            root.setRightChild(removeRec(root.getRightChild(), root.getElement()));
        }
        return root;
    }

    private T minValue(Node<T> node) {
        T minValue = node.getElement();
        while (node.getLeftChild() != null) {
            minValue = node.getLeftChild().getElement();
            node = node.getLeftChild();
        }
        return minValue;
    }

    public boolean contains(T key) {
        return containsRec(root, key);
    }

    private boolean containsRec(Node<T> root, T key) {
        if (root == null) {
            return false;
        }

        int cmp = key.compareTo(root.getElement());
        if (cmp == 0) {
            return true; // Key found in the current node
        } else if (cmp < 0) {
            return containsRec(root.getLeftChild(), key); // Search left subtree
        } else {
            return containsRec(root.getRightChild(), key); // Search right subtree
        }
    }

}