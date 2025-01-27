/**
 * The AboutCreator class implements the InformationDisplay interface to provide information about the creator of the game.
 * It defines the details of the creator, including their name, interests, and fun facts.
 */
public class AboutCreator implements InformationDisplay {

    /**
     * Retrieves information about the creator in HTML format for display purposes.
     *
     * @return A string containing the creator's details formatted in HTML.
     */
    @Override
    public String getInformation() {
        // Returns an HTML-formatted string containing information about the creator.
        return """
                <html>
                <div style='font-size:14px;'>
                About the Creator:<br>
                - Name: Andrin, Jones<br>
                - Favorite Programming Language: Java<br>
                - Hobbies: Coding, Gaming, Reading<br>
                - Fun Fact: All this was created for a school project!<br>
                </div>
                </html>
                """;
    }
}