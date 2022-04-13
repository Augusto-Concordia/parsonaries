//COMP 249 - Assignment #4
//Due Date: April 15th
//Written by: Augusto Mota Pinheiro (40208080) & Michaël Gugliandolo (40213419)

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class that creates a dictionary from a txt file
 * Reads words separated by whitespaces, performs verifications on each word and checks if it's repeated in arraylist
 * If word is valid, add word to arraylist. When finished, sort arraylist alphabetically and print out dictionary in new txt file
 */
public class Main {
    /**
     * Driver method that runs the project
     *
     * @param args Optional arguments for driver method
     */
    public static void main(String[] args) {
        Scanner consoleIn = new Scanner(System.in); //Scanner for user input
        String fileName; //Name entered by the user for the name of the txt file to read words from
        String token; //Single word read from file
        Scanner fileIn = null; //Scanner to read the file
        PrintWriter fileOut = null; //PrintWriter to write the file SubDictionary.txt
        int nbTokens; //Number of words

        ArrayList<String> tokenList = new ArrayList<>(); //Arraylist filled with validated tokens

        //Welcome message and prompt user for file name
        System.out.println("""
                 ------------------------------------------------
                |  Hello and welcome to this wonderful program!  |
                 ------------------------------------------------
                 
                 What is the name of the txt file you want to read from? (You don't have to write .txt)""");
        fileName = consoleIn.nextLine();
        consoleIn.close();

        //Open specified file
        try {
            fileIn = new Scanner(new FileInputStream("input\\" + (fileName + ".txt")));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the input file; now exiting the program...");
            System.exit(-1);
        }

        //Read tokens from file
        while (fileIn.hasNext()) {
            token = formatToken(fileIn.next()); //Validate and format token
            if (token != null && !tokenList.contains(token)) { //If null, then invalid token; check if token is already present
                tokenList.add(token);
            }
        }
        fileIn.close(); //Close scanner

        nbTokens = tokenList.size(); //Save number of tokens
        sortAlphabetically(tokenList); //Sort alphabetically

        //Write to SubDictionary.txt
        try {
            fileOut = new PrintWriter(new FileOutputStream("output\\SubDictionary.txt"));

            fileOut.println("This sub-dictionary includes " + nbTokens + " entries."); //Number fo words

            for (String t : tokenList) { //Print out each word
                fileOut.println(t);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the output file; now exiting the program...");
            System.exit(-1);
        } finally {
            if (fileOut != null) {
                fileOut.close(); //Close PrintWriter
            }
        }

        System.out.println("Thank you for using our program! You have a nice day now, bye-bye!"); //Closing message
    }

    /**
     * Validate and format token
     *
     * @param token Token to validate
     * @return Formatted token if valid, null if invalid
     */
    public static String formatToken(String token) {
        final String[] ENDINGS = { //Apostrophe endings to remove
                "’T",
                "’LL",
                "’RE",
                "’D",
                "’NT",
                "’VE",
                "’M",
                "’N",
                "’S"
        };
        /*final String[] ENDINGS = { //Normal apostrophes endings to remove
                "'T",
                "'LL",
                "'RE",
                "'D",
                "'NT",
                "'VE",
                "'M",
                "'N",
                "'S"
        };*/

        final ArrayList<Character> END_CHARS = arrayToList(new Character[]{ //If token ends in one of these characters, it can be removed
                '?',
                ':',
                ',',
                ';',
                '!',
                '.',
        });

        final ArrayList<Character> NUMBERS = arrayToList(new Character[]{ //Arraylist containing all digits
                '0',
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
        });

        //Standardize checking
        token = token.toUpperCase();

        //Apostrophe endings
        for (String s : ENDINGS) {
            if (token.endsWith(s)) {
                token = token.replace(s, "");
                break;
            }
        }

        //Convert String token to char array for easier manipulation
        char[] tokenChars = token.toCharArray();

        //Takes care of single character tokens that aren't A & Is
        if (tokenChars.length == 1 && tokenChars[0] != 'A' && tokenChars[0] != 'I') return null;

        for (int i = 0; i < tokenChars.length; i++) { //Loop through each character
            //Numbers
            if (NUMBERS.contains(tokenChars[i])) return null;

            //Punctuation
            if (END_CHARS.contains(tokenChars[i])) {
                if (i != tokenChars.length - 1) //Punctuation in the middle of token
                    return null;

                tokenChars = copyRange(tokenChars, 0, tokenChars.length - 1); //Resize char array without punctuation
            }
        }
        return String.valueOf(tokenChars);
    }

    /**
     * Converts array to arraylist
     *
     * @param array Array to convert
     * @return Arraylist with elements from array
     */
    private static ArrayList<Character> arrayToList(Character[] array) {
        ArrayList<Character> listToReturn = new ArrayList<>();

        for (Character item : array) {
            listToReturn.add(item);
        }

        return listToReturn;
    }

    /**
     * Creates array with elements from a specified range from another array
     *
     * @param array Array to copy from
     * @param start Index of first element (inclusive)
     * @param end   Index of last element (exclusive, so if index 4 is the last element to copy, end = 5)
     * @return New array with copied elements
     */
    private static char[] copyRange(char[] array, int start, int end) {
        char[] arrayToReturn = new char[end - start];

        for (int i = 0; i < end - start; i++) {
            arrayToReturn[i] = array[start + i];
        }

        return arrayToReturn;
    }

    /**
     * Sort String arraylist alphabetically and adds line with first letter when we reach words with different first letters
     * Uses selection sort
     *
     * @param list String arraylist to sort
     */
    private static void sortAlphabetically(ArrayList<String> list) {
        String smallest; //Current smallest word
        String oldSmallest = " "; //Previous smallest word (to compare first letter)
        int pos; //Index of current smallest word

        for (int i = 0; i < list.size(); i++) { //Go through string arraylist
            smallest = list.get(i);
            pos = i;

            for (int j = i + 1; j < list.size(); j++) { //Go through all following words to find smaller word
                if (list.get(j).compareTo(smallest) < 0) { //Found smaller word
                    pos = j;
                    smallest = list.get(j);
                }
            }
            list.set(pos, list.get(i)); //Switch smallest with current word
            list.set(i, smallest);

            if (oldSmallest.charAt(0) != smallest.charAt(0)) { //New starting letter
                list.add(i, "\n" + smallest.charAt(0) + "\n==");
                i++; //We just added an element, so increment i to keep up
            }

            oldSmallest = smallest; //Update oldSmallest
        }
    }
}
