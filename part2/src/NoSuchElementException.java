public class NoSuchElementException extends Exception{

    public NoSuchElementException() {
        super("Element doesn't exist in list.");
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
