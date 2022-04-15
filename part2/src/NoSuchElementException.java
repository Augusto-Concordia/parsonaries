//COMP 249 - Assignment #4
//Due Date: April 15th
//Written by: Augusto Mota Pinheiro (40208080) & Michaël Gugliandolo (40213419)

/**
 * Exception thrown when no element is found in a collection.
 */
public class NoSuchElementException extends Exception{

    /**
     * Default constructor.
     */
    public NoSuchElementException() {
        super("Element doesn't exist in list.");
    }

    /**
     * Constructor with message.
     * @param message message to be displayed.
     */
    public NoSuchElementException(String message) {
        super(message);
    }
}
