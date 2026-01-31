package cybot;

/**
 * Handle expected exception
 */
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
