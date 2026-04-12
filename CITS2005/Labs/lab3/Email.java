import java.util.ArrayList;

public class Email {
    String subject;
    String sender;
    String message;
    boolean isImportant;
 
 
    public Email(String subject, String sender, String message) {
        this.subject = subject;
        this.sender = sender;
        this.message = message;
        this.isImportant = false;
    }

    public Email(String subject, String sender, String message, boolean isImportant) {
        this.subject = subject;
        this.sender = sender;
        this.message = message;
        this.isImportant = isImportant;
    }

    public Email(String subject, String sender) {
        this.subject = subject;
        this.sender = sender;
        this.message = "No Message.";
        this.isImportant = false;
    }

    public void printEmails() {



    }

}




/* task
    -
    -
    -
*/

/* notes while building
    -
    -
    -
*/