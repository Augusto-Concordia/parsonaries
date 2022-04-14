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
