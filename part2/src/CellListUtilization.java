import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CellListUtilization {

    public static void main(String[] args) {

        Scanner fileIn = null;
        CellList list1 = new CellList();
        CellList list2 = new CellList();

        System.out.println("""
                 ------------------------------------------------
                |  Hello and welcome to this wonderful program!  |
                 ------------------------------------------------
                 """);

        try {
            fileIn = new Scanner(new FileInputStream("input\\Cell_Info.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the input file; now exiting the program...");
            System.exit(-1);
        }

        while (fileIn.hasNext()) {
            //Do something with the lists
        }

        System.out.println(list1.showContents());
        System.out.println(list2.showContents());

        //Have some fun with the lists
        //Output content again


        System.out.println("Thank you for using our program! You have a nice day now, bye-bye!");
    }
}
