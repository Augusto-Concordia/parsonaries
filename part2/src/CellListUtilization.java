import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver class for the project.
 */
public class CellListUtilization {

    /**
     * Main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner consoleIn = new Scanner(System.in); // scanner for user input
        Scanner fileIn = null; // scanner for file input

        CellList list1 = new CellList();
        CellList list2 = new CellList();

        // welcome message
        System.out.println("""
                 ------------------------------------------------
                |  Hello and welcome to this wonderful program!  |
                 ------------------------------------------------
                 """);

        // gets file to read from
        try {
            fileIn = new Scanner(new FileInputStream("input\\Cell_Info.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the input file; now exiting the program...");
            System.exit(-1);
        }

        // reads the file and fills the first list
        while (fileIn.hasNext()) {
            list1.addToStart(new CellPhone(fileIn.nextLong(), fileIn.next(), fileIn.nextDouble(), fileIn.nextInt()));
        }

        // prints both lists
        System.out.println("List 1: " + list1.showContents());
        System.out.println("List 2: " + list2.showContents());

        System.out.println();

        // tests the find method
        System.out.println("Find existent cellphone (1): " + list1.find(6987612, true));
        System.out.println("Find nonexistent cellphone (1): " + list1.find(6987613, true));

        System.out.println();

        // tests the contains method
        System.out.println("Contains existent cellphone (1): " + list1.contains(6987612));
        System.out.println("Contains nonexistent cellphone (1): " + list1.contains(6987613));

        System.out.println();

        CellPhone basePhone = new CellPhone(31, "Custom", 932.32, 2022);

        // tests the insertAtIndex method
        try {
            list1.insertAtIndex(basePhone, 5);
        } catch (NoSuchElementException e) {
            System.out.println("Cellphone could not be added: no element at specified index.");
        }
        System.out.println("Added new cellphone (1): " + list1.showContents());

        System.out.println();

        // tests the deleteFromStart method
        list1.deleteFromStart();

        // tests the deleteFromIndex method
        try {
            list2.deleteFromIndex(10);
        } catch (NoSuchElementException e) {
            System.out.println("Cellphone could not be deleted: no element at specified index.");
        }

        System.out.println("Deleted starting cellphone (1): " + list1.showContents());
        System.out.println("Deleted cellphone at invalid index (2): " + list2.showContents());

        System.out.println();

        // tests the copy constructor
        list2 = new CellList(list1);

        System.out.println("Verifying list equality after copying (1,2): " + list1.equals(list2));

        System.out.println();

        // tests the deleteFromIndex method
        try {
            list1.deleteFromIndex(3);
        } catch (NoSuchElementException e) {
            System.out.println("Cellphone could not be deleted: no element at specified index.");
        }
        System.out.println("Deleted cellphone at valid index (1): " + list1.showContents());

        System.out.println();

        System.out.println("Verifying list equality (1,2): " + list1.equals(list2));

        System.out.println();

        System.out.print("Please input serial number for first new cellphone to be replaced at index: ");

        // tests the replaceAtIndex method and the clone method
        try {
            list1.replaceAtIndex(basePhone.clone(consoleIn.nextLong()), 5);
            list2.replaceAtIndex(new CellPhone(3, "Custom 2", 932.32, 2021), 1);
        } catch (NoSuchElementException e) {
            System.out.println("Cellphone could not be replaced: no element at specified index.");
        }

        System.out.println("Replaced cellphone at index (1): " + list1.showContents());
        System.out.println("Replaced cellphone at index (2): " + list2.showContents());

        System.out.println();

        System.out.println("Thank you for using our program! You have a nice day now, bye-bye!");

        // closes both scanners
        consoleIn.close();
        fileIn.close();
    }
}
