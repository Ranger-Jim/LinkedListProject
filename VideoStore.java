
/**
 * This is the VideoStore class which also contains 'main'.  This handles all functions of a video
 *  rental store.  Can access the menu from command line prompt java VideoStore <data structure>.  This class
 *  utilizes either singly linked list (SLL) or doubly linked list (DLL).
 * 
 * @author James Donohue
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class VideoStore {
    private SLL<Video> videoInStoreSLL = new SLL<Video>();
    private DLL<Video> videoInStoreDLL = new DLL<Video>();
    private BST<Video> videoInStoreBST = new BST<Video>();
    private SLL<Customer> customerSLL = new SLL<Customer>();
    private DLL<Customer> customerDLL = new DLL<Customer>();
    private BST<Customer> customerBST = new BST<Customer>();
    private SLL<Video> allVideosSLL = new SLL<Video>();
    private DLL<Video> allVideosDLL = new DLL<Video>();
    private BST<Video> allVideosBST = new BST<Video>();
    private SLL<Video> rentedVideosSLL = new SLL<Video>();
    private DLL<Video> rentedVideosDLL = new DLL<Video>();
    private BST<Video> rentedVideosBST = new BST<Video>();
    private String dataStructure;
    private int numVideos;
    private int numCustomers;
    private int numRequests;
    private Queue<Integer> requestQueue = new LinkedList<>();

    /*
     * Contructor
     * 
     * @param dataStruc The data structure being used. SLL or DLL.
     */
    public VideoStore(String dataStruc) {
        dataStructure = dataStruc;
    }

    public void setNumVideos(int numVideos) {
        this.numVideos = numVideos;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    public void setNumRequests(int numRequests) {
        this.numRequests = numRequests;
    }

    /**
     * Generates random videos to be added to the store.
     */
    private void generateVideos() {
        for (int i = 1; i <= numVideos; i++) {
            String title = "Video " + i;
            String id = "V" + i;
            Video video = new Video(title, id);
            addVideo(video);
        }
    }

    /**
     * Generates random customers to be added to the store.
     */
    private void generateCustomers() {
        for (int i = 1; i <= numCustomers; i++) {
            String name = "Customer " + i;
            String id = "C" + i;
            Customer customer = new Customer(name, id, dataStructure);
            setCustomer(customer);
        }
    }

    /**
     * Generates random request types - 5, 6, or 7.
     */
    private void generateRequests() {
        Random rand = new Random();
        for (int i = 0; i < numRequests; i++) {
            int requestType = rand.nextInt(3) + 5; // Generate random request type (5, 6, or 7)
            requestQueue.offer(requestType);
        }
    }

    /**
     * Processes the requests generated in generateRequests method. Processes those
     * requests from a queue
     * and records the time it takes to complete all of them. Writes the results to
     * "Performance.docx".
     * 
     * @throws IOException
     */
    private void processRequests() throws IOException {
        FileWriter fWriter = new FileWriter("Performance.docx", true);
        PrintWriter outputFile = new PrintWriter(fWriter);
        double startTime = System.nanoTime();
        while (!requestQueue.isEmpty()) {
            int requestType = requestQueue.poll();
            switch (requestType) {
                case 5: // Checks if video is available in store
                    String title = "Video " + (new Random().nextInt(numVideos) + 1);
                    String id = "V" + (new Random().nextInt(numVideos) + 1);
                    Video video = new Video(title, id);
                    check(video);
                    break;
                case 6: // Requests to check out a video from the store
                    String custName = "Customer " + (new Random().nextInt(numCustomers));
                    String custID = "C" + (new Random().nextInt(numCustomers));
                    title = "Video " + (new Random().nextInt(numVideos) + 1);
                    id = "V" + (new Random().nextInt(numVideos) + 1);

                    Customer customer = new Customer(custName, custID, dataStructure);
                    video = new Video(title, id);
                    // checkOut(video, customer);
                    break;
                case 7: // Checks a video into the store
                    title = "Video " + (new Random().nextInt(numVideos) + 1);
                    id = "V" + (new Random().nextInt(numVideos) + 1);
                    custName = "Customer " + (new Random().nextInt(numCustomers));
                    custID = "C" + (new Random().nextInt(numCustomers));

                    video = new Video(title, id);
                    customer = new Customer(custName, custID, dataStructure);

                    checkInHelp(video, customer);
                    break;

                default:
                    break;
            }
        }
        double endTime = System.nanoTime();
        double nanoTime = endTime - startTime;
        double totalTime = nanoTime / 1_000_000.0; // converts nanoseconds to milli
        System.out.println("Videos: " + numVideos + "\nCustomers: " + numCustomers + "\nRequests: " + numRequests);
        System.out.println("Total service time in ms: " + totalTime);
        outputFile.println(dataStructure);
        outputFile.println("Videos: " + numVideos + "\nCustomers: " + numCustomers + "\nRequests: " + numRequests);
        outputFile.println("Total service time: " + totalTime + " milliseconds");
        outputFile.println();
        outputFile.close();
    }

    /**
     * Adds video to the store.
     * 
     * @param video
     */
    private void addVideo(Video video) {
        if (dataStructure.equals("SLL")) {
            setVideoInStore(video);
        } else if (dataStructure.equals("DLL")) {
            setVideoInStore(video);
        } else if (dataStructure.equals("BST")) {
            setVideoInStore(video);
        }
    }

    /**
     * Deletes a video from the store.
     * 
     * @param video The video to be deleted.
     * @return If the video was able to be deleted.
     */
    private boolean deleteVideo(Video video) {
        if (dataStructure.equals("SLL")) {
            allVideosSLL.remove(video);
            videoInStoreSLL.remove(video);
            rentedVideosSLL.remove(video);
            return true;
        } else if (dataStructure.equals("DLL")) {
            allVideosDLL.remove(video);
            videoInStoreDLL.remove(video);
            rentedVideosDLL.remove(video);
            return true;
        } else if (dataStructure.equals("BST")) {
            allVideosBST.remove(video);
            videoInStoreBST.remove(video);
            rentedVideosBST.remove(video);
        }
        return false;
    }

    /**
     * Set a particular video to be available in store.
     * 
     * @param video The video in store.
     */
    private void setVideoInStore(Video video) {
        if (dataStructure.equals("SLL")) {
            videoInStoreSLL.insert(video);
        } else if (dataStructure.equals("DLL")) {
            videoInStoreDLL.insertAtTail(video);
        } else if (dataStructure.equals("BST")) {
            videoInStoreBST.insert(video);
        }
    }

    /**
     * Adds a customer to the store.
     * 
     * @param cust The customer to be added.
     */
    private void setCustomer(Customer cust) {
        if (dataStructure.equals("SLL")) {
            customerSLL.insert(cust);
        } else if (dataStructure.equals("DLL")) {
            customerDLL.insertAtTail(cust);
        } else if (dataStructure.equals("BST")) {
            customerBST.insert(cust);
        }
    }

    /**
     * Deletes a customer from the store.
     * 
     * @param cust The customer to be deleted.
     * @return If the customer was able to be deleted.
     */
    private boolean deleteCustomer(Customer cust) {
        if (dataStructure.equals("SLL")) {
            customerSLL.remove(cust);
        } else if (dataStructure.equals("DLL")) {
            customerDLL.remove(cust);
        } else if (dataStructure.equals("BST")) {
            customerBST.remove(cust);
        }
        return false;
    }

    /**
     * Checks if a video is available in store.
     * 
     * @param video The video to search for.
     * @return If the video is available in store.
     */
    private boolean check(Video video) {
        if (dataStructure.equals("SLL")) {
            if (videoInStoreSLL.contains(video)) {
                return true;
            } else {
                return false;
            }
        } else if (dataStructure.equals("DLL")) {
            if (videoInStoreDLL.contains(video)) {
                return true;
            } else {
                return false;
            }
        } else if (dataStructure.equals("BST")) {
            Node<Video> videoToSearch = videoInStoreBST.search(video);
            if (videoToSearch != null) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOutHelp(Video video, Customer customer) {
        Node<Video> videoNodeSLL = videoInStoreSLL.getNode(video);
        Node<Customer> custNodeSLL = customerSLL.getNode(customer);
        System.out.println(videoNodeSLL.getElement());
        System.out.println(custNodeSLL.getElement());
        if (videoNodeSLL != null && custNodeSLL != null) {
            if (checkOut(videoNodeSLL, custNodeSLL)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks a video out of the store and into the posession of a customer.
     * 
     * @param video    The video to check-out.
     * @param customer The customer checking the video out.
     * @return If the video was able to be checked-out.
     */
    private boolean checkOut(Node<Video> videoNode, Node<Customer> customerNode) {
        Video video = videoNode.getElement();
        Customer customer = customerNode.getElement();
        if (dataStructure.equals("SLL")) {
            videoInStoreSLL.remove(video);
            rentedVideosSLL.remove(video);
            customer.addRentedVideo(video);
            return true;
        } else if (dataStructure.equals("DLL")) {
            videoInStoreDLL.remove(video);
            rentedVideosSLL.remove(video);
            customer.addRentedVideo(video);
            return true;
        } else if (dataStructure.equals("BST")) {
            videoInStoreBST.remove(video);
            rentedVideosBST.remove(video);
            customer.addRentedVideo(video);
            return true;
        }
        return false;
    }

    private boolean checkInHelp(Video video, Customer customer) {
        Node<Video> videoNodeSLL = rentedVideosSLL.getNode(video);
        Node<Customer> custNodeSLL = customerSLL.getNode(customer);

        if (videoNodeSLL != null && custNodeSLL != null) {
            System.out.println(videoNodeSLL.getElement());
            System.out.println(custNodeSLL.getElement());

            if (checkIn(videoNodeSLL, custNodeSLL)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check a video back into the store, making it available to rent again.
     * 
     * @param video The video to check-in to the store.
     * @return If the video was able to be checked-in.
     */
    private boolean checkIn(Node<Video> videoNode, Node<Customer> customerNode) {
        Video video = videoNode.getElement();
        Customer customer = customerNode.getElement();

        if (dataStructure.equals("SLL")) {
            rentedVideosSLL.remove(video);
            customer.deleteRentedVideo(video);
            videoInStoreSLL.insert(video);
            return true;
        } else if (dataStructure.equals("DLL")) {
            rentedVideosDLL.remove(video);
            customer.deleteRentedVideo(video);
            videoInStoreDLL.insertAtTail(video);
        } else if (dataStructure.equals("BST")) {
            rentedVideosBST.remove(video);
            customer.deleteRentedVideo(video);
            videoInStoreBST.insert(video);
            return true;
        }
        return false;
    }

    /**
     * Prints all of the customers that have been added to the store.
     */
    private void printAllCustomers() {
        if (dataStructure.equals("SLL")) {
            customerSLL.print();
        } else if (dataStructure.equals("DLL")) {
            customerDLL.print();
        } else if (dataStructure.equals("BST")) {
            customerBST.inorderTraversal();
        }
    }

    /**
     * Prints all of the videos, whether in store or rented.
     */
    private void printAllVideos() {
        if (dataStructure.equals("SLL")) {
            allVideosSLL.print();
        } else if (dataStructure.equals("DLL")) {
            allVideosDLL.print();
        } else if (dataStructure.equals("BST")) {
            allVideosBST.inorderTraversal();
        }
    }

    /**
     * Prints all of the videos available in store.
     */
    private void printInStoreVideos() {
        if (dataStructure.equals("SLL")) {
            videoInStoreSLL.print();
        } else if (dataStructure.equals("DLL")) {
            videoInStoreDLL.print();
        } else if (dataStructure.equals("BST")) {
            videoInStoreBST.inorderTraversal();
        }
    }

    /**
     * Prints all of the videos currently rented.
     */
    private void printAllRentVideos() {
        if (dataStructure.equals("SLL")) {
            rentedVideosSLL.print();
        } else if (dataStructure.equals("DLL")) {
            rentedVideosDLL.print();
        } else if (dataStructure.equals("BST")) {
            rentedVideosBST.inorderTraversal();
        }
    }

    /**
     * Prints all of the videos that have been rented by a particular customer.
     * 
     * @param customer The customer to check.
     */
    private void printVideosRentByACustomer(Customer customer) {
        if (dataStructure.equals("SLL")) {
            Node<Customer> custNode = customerSLL.getNode(customer);
            if (custNode != null) {
                Customer cust = custNode.getElement();
                cust.printRentedVideos();
            }
        } else if (dataStructure.equals("DLL")) {

        }
    }

    public static void main(String[] args) throws Exception {
        String dataType = args[0];
        VideoStore store = new VideoStore(dataType);
        if (args.length > 1) { // This is if the user wants to automate the process
            store.setNumVideos(Integer.parseInt(args[1]));
            store.setNumCustomers(Integer.parseInt(args[2]));
            store.setNumRequests(Integer.parseInt(args[3]));

            store.generateVideos();
            store.generateCustomers();
            store.generateRequests();
            store.processRequests();
            System.exit(0);
        }

        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            String str = "=================================\n";
            str += "Select one of the following...\n1: Add a video\n";
            str += "2: Delete a video\n3: Add a customer\n4: Delete a customer\n";
            str += "5: Check if a particular video is in store\n6: Check out a video\n";
            str += "7: Check in a video\n8: Print all customers\n9: Print all videos\n";
            str += "10: Print in store videos\n11: Print all rented videos\n";
            str += "12: Print all the videos rented by a customer\n13: Exit\n";
            str += "=================================\n";
            System.out.println(str);
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    String title = input.nextLine();
                    String id;
                    System.out.print("Input video title: ");
                    title = input.nextLine();
                    System.out.print("Input video I.D.: ");
                    id = input.nextLine();
                    Video vid = new Video(title, id);
                    store.setVideoInStore(vid);
                    store.addVideo(vid);
                    System.out.println("\nVideo added...");
                    break;

                case 2:
                    title = input.nextLine();
                    System.out.print("Input video title: ");
                    title = input.nextLine();
                    System.out.print("Input video I.D.: ");
                    id = input.nextLine();
                    vid = new Video(title, id);
                    if (store.deleteVideo(vid)) {
                        System.out.println("Video was deleted...");
                    } else {
                        System.out.println("Video could not be deleted...");
                    }
                    break;

                case 3:
                    String custName = input.nextLine();
                    String custId;

                    System.out.print("Enter customer name: ");
                    custName = input.nextLine();
                    System.out.print("Enter customer I.D.: ");
                    custId = input.nextLine();

                    Customer cust = new Customer(custName, custId, dataType);
                    store.setCustomer(cust);
                    System.out.println("\nCustomer added...");
                    break;

                case 4:
                    custName = input.nextLine();

                    System.out.print("Enter customer name: ");
                    custName = input.nextLine();
                    System.out.print("Enter customer I.D.: ");
                    custId = input.nextLine();

                    cust = new Customer(custName, custId, dataType);
                    if (store.deleteCustomer(cust)) {
                        System.out.println("Customer deleted...");
                    } else {
                        System.out.println("Customer could not be deleted...");
                    }
                    break;

                case 5:
                    title = input.nextLine();

                    System.out.println("\nChecking availabilty: ");
                    System.out.print("Title: ");
                    title = input.nextLine();
                    System.out.print("I.D.: ");
                    id = input.nextLine();
                    Video checkIfAvailable = new Video(title, id);
                    if (store.check(checkIfAvailable)) {
                        System.out.println("Video is available in store");
                    } else {
                        System.out.println("Video is not available in store");
                    }
                    break;

                case 6:
                    custName = input.nextLine();

                    System.out.println("Renting video...");
                    System.out.print("Enter customer name: ");
                    custName = input.nextLine();
                    System.out.print("Enter customer I.D.: ");
                    custId = input.nextLine();

                    cust = new Customer(custName, custId, dataType);

                    System.out.print("Title: ");
                    title = input.nextLine();
                    System.out.print("I.D.: ");
                    id = input.nextLine();

                    vid = new Video(title, id);

                    if (store.checkOutHelp(vid, cust)) {
                        // System.out.println("Video checked out...");
                    } else {
                        System.out.println("Could not rent video...");
                    }
                    break;

                case 7:
                    title = input.nextLine();
                    System.out.println("Checking in video...");
                    System.out.print("Customer name: ");
                    custName = input.nextLine();
                    System.out.print("Customer I.D.: ");
                    custId = input.nextLine();
                    System.out.print("Title: ");
                    title = input.nextLine();
                    System.out.print("I.D.: ");
                    id = input.nextLine();

                    cust = new Customer(custName, custId, dataType);
                    vid = new Video(title, id);

                    if (store.checkInHelp(vid, cust)) {
                        System.out.println("\nVideo checked in...");
                    }

                    break;

                case 8:
                    store.printAllCustomers();
                    break;

                case 9:
                    System.out.println("\nPrinting all videos...");
                    store.printAllVideos();
                    break;

                case 10:
                    System.out.println("\nPrinting all videos in store...");
                    store.printInStoreVideos();
                    break;

                case 11:
                    System.out.println("\nPrinting all currently rented videos...");
                    store.printAllRentVideos();
                    break;

                case 12:
                    custName = input.nextLine();
                    System.out.print("\nCustomer name: ");
                    custName = input.nextLine();
                    System.out.print("Customer I.D.: ");
                    custId = input.nextLine();

                    cust = new Customer(custName, custId, dataType);
                    store.printVideosRentByACustomer(cust);
                    break;

                case 13:
                    System.out.println("EXITING...");
                    break;

                default:
                    System.out.println("INVALID SELECTION...");
                    break;
            }

        } while (choice != 13);
        input.close();

    }
}