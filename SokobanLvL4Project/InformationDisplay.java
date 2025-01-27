/**
 * The InformationDisplay interface provides a contract for classes that need to display informational content.
 * Classes implementing this interface must define the method for retrieving the information.
 */
public interface InformationDisplay {

    /**
     * Retrieves the information to be displayed.
     *
     * @return A string containing the information to display.
     */
    String getInformation();
}
