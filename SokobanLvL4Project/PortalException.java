/**
 * PortalException is a custom runtime exception class for handling portal-related errors in the game.
 * This exception can be used to indicate issues such as invalid portal states or interactions.
 */
public class PortalException extends RuntimeException {

    /**
     * Constructor for PortalException.
     *
     * @param message A detailed error message describing the portal issue.
     */
    public PortalException(String message) {
        // Call the superclass constructor (RuntimeException) to initialize the exception.
        super(message);
    }
}