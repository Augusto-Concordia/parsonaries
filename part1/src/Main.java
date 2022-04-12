import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleIn = new Scanner(System.in);
        String fileName;
        String token;
        Scanner fileIn = null;
        PrintWriter fileOut = null;
        int nbLines;

        ArrayList<String> tokenList = new ArrayList<>();

        System.out.println("""
                 ------------------------------------------------
                |  Hello and welcome to this wonderful program!  |
                 ------------------------------------------------
                 
                 What is the name of the txt file you want to read from? (You don't have to write .txt)""");
        fileName = consoleIn.nextLine();

        try {
            fileIn = new Scanner(new FileInputStream("input\\" + (fileName + ".txt")));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the input file; now exiting the program...");
            System.exit(-1);
        }

        while (fileIn.hasNext()) {
            token = formatToken(fileIn.next());
            if (token != null && !tokenList.contains(token)) {
                tokenList.add(token);
            }
        }

        nbLines = tokenList.size();
        tokenList = sortAlphabetically(tokenList);

        try {
            fileOut = new PrintWriter(new FileOutputStream("output\\SubDictionary.txt"));

            fileOut.println("This sub-dictionary includes " + nbLines + " entries.");

            for (String t : tokenList) {
                fileOut.println(t);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the output file; now exiting the program...");
            System.exit(-1);
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
        }

        System.out.println("Thank you for using our program! You have a nice day now, bye-bye!");
    }

    public static String formatToken(String token) {
        final String[] ENDINGS = { //weird apostrophes
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
        /*final String[] ENDINGS = { //normal apostrophes
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

        final ArrayList<Character> END_CHARS = arrayToList(new Character[]{
                '?',
                ':',
                ',',
                ';',
                '!',
                '.',
        });

        final ArrayList<Character> NUMBERS = arrayToList(new Character[]{
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

        //standardize checking
        token = token.toUpperCase();

        //apostrophe endings
        for (String s : ENDINGS) {
            if (token.endsWith(s)) {
                token = token.replace(s, "");
                break;
            }
        }

        //for easier manipulation
        char[] tokenChars = token.toCharArray();

        //takes care of A & Is
        if (tokenChars.length == 1 && tokenChars[0] != 'A' && tokenChars[0] != 'I') return null;

        for (int i = 0; i < tokenChars.length; i++) {
            //numbers
            if (NUMBERS.contains(tokenChars[i])) return null;

            //punctuation
            if (END_CHARS.contains(tokenChars[i])) {
                if (i != tokenChars.length - 1)
                    return null;

                tokenChars = copyRange(tokenChars, 0, tokenChars.length - 1); //Arrays.copyOfRange(tokenChars, 0, tokenChars.length - 1);
                ;
            }
        }
        return String.valueOf(tokenChars);
    }

    private static ArrayList<Character> arrayToList(Character[] array) {
        ArrayList<Character> listToReturn = new ArrayList();

        for (Character item : array) {
            listToReturn.add(item);
        }

        return listToReturn;
    }

    private static char[] copyRange(char[] array, int start, int end) {
        char[] arrayToReturn = new char[end - start];

        for (int i = 0; i < end - start; i++) {
            arrayToReturn[i] = array[start + i];
        }

        return arrayToReturn;
    }

    private static ArrayList<String> sortAlphabetically(ArrayList<String> list) {
        String smallest;
        String oldSmallest = " ";

        ArrayList<String> sortedList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            smallest = list.get(i);

            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(smallest) < 0) {
                    String temp = smallest;
                    smallest = list.get(j);
                    list.set(j, temp);
                }
            }

            if (!oldSmallest.substring(0, 1).equals(smallest.substring(0, 1))) {
                sortedList.add("\n" + smallest.substring(0, 1));
                sortedList.add("==");
            }

            sortedList.add(smallest);
            oldSmallest = smallest;
        }
        return sortedList;
    }
}
