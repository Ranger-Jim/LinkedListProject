
/**
 * This is the class that handles all of the customer objects associated with the 
 * VideoStore class.
 * 
 * @author James Donohue
 */

import java.util.*;

public class Customer implements Comparable<Customer> {
    private String name;
    private String id;
    private String dataStructure;
    private SLL<Video> rentVideoSLL = new SLL<>();
    private DLL<Video> rentVideoDLL = new DLL<>();
    private BST<Video> rentVideoBST = new BST<>();

    /**
     * Constructor.
     * 
     * @param name          The name of the customer.
     * @param id            The customer I.D.
     * @param dataStructure The data structure being used. SLL or DLL.
     */
    public Customer(String name, String id, String dataStructure) {
        this.name = name;
        this.id = id;
        this.dataStructure = dataStructure;
    }

    /**
     * @return String The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * @return String The I.D. of the customer.
     */
    public String getId() {
        return id;
    }

    /**
     * Adds a rented video to the appropriate data structure list.
     * 
     * @param video The video to be added.
     */
    public void addRentedVideo(Video video) {
        if (dataStructure.equals("SLL")) {
            rentVideoSLL.insert(video);
        } else if (dataStructure.equals("DLL")) {
            rentVideoDLL.insertAtTail(video);
        } else if (dataStructure.equals("BST")) {
            rentVideoBST.insert(video);
        }
    }

    /**
     * Deletes a rented video from the list.
     * 
     * @param video The video to be deleted.
     */
    public void deleteRentedVideo(Video video) {

        if (dataStructure.equals("SLL")) {
            rentVideoSLL.remove(video);
        } else if (dataStructure.equals("DLL")) {
            rentVideoDLL.remove(video);
        } else if (dataStructure.equals("BST")) {
            rentVideoBST.remove(video);
        }
    }

    /**
     * Prints the rented videos for a given customer.
     */
    public void printRentedVideos() {
        System.out.println("Rented videos for " + name + ":");
        if (dataStructure.equals("SLL")) {
            Node<Video> current = rentVideoSLL.getHead();
            while (current != null) {
                System.out.println(current.getElement().toString());
                current = current.getNext();
            }
        } else if (dataStructure.equals("DLL")) {
            Node<Video> current = rentVideoDLL.getHead();
            while (current != null) {
                System.out.println(current.getElement().toString());
                current = current.getNext();
            }
        } else if (dataStructure.equals("BST")) {
            rentVideoBST.inorderTraversal();
        }
    }

    public String toString() {
        String str = "\n====================================";
        str += "\nNAME: " + name;
        str += "\nI.D.: " + id;
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;
        return Objects.equals(name, other.name) && Objects.equals(id, other.id);
    }

    @Override
    public int compareTo(Customer other) {
        return this.name.compareTo(other.name);
    }
}
