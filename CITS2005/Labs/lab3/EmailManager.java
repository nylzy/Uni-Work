import java.util.ArrayList;

public class EmailManager {
    ArrayList<Email> emails = new ArrayList<>();

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void printEmails() {
        for (Email email: emails) {
            System.out.println("Subject: " + email.subject);
            System.out.println("Sender: " + email.sender);
            if (!email.message.equals("No Message.")) {
                System.out.println("Message: " + email.message);
            }
            if (email.isImportant) {
                System.out.println("Important!");
            } else {
                System.out.println("Not Important.");
            }
            }
            
    }

    public void printImportant() {
        ArrayList<Email> importantEmails = new ArrayList<>();

        for (Email email: emails) {
            if (email.isImportant) {
                importantEmails.add(email);
            }
        }
        System.out.println("Important Emails:");
        for (Email email : importantEmails) {
            System.out.println("Subject: " + email.subject);
            System.out.println("Sender: " + email.sender);
            if (!email.message.equals("No Message.")) {
                System.out.println("Message: " + email.message);
            }
            if (email.isImportant) {
                System.out.println("Important!");
            } else {
                System.out.println("Not Important.");
            }
        }
    }


    public static void main(String[] args) {
        EmailManager em = new EmailManager();
        em.addEmail(new Email("Hello", "Donald"));
        em.addEmail(new Email ("Promotion", "KFC", "Cheap Wings"));
        em.addEmail(new Email("Unit Results", "UWA", "Your results are attached.", true));
        em.printEmails();
        em.printImportant();
    }

}