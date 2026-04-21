import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Random rand = new Random();
        int secret = rand.nextInt(100) + 1; 
        int userGuess = 0;

        System.out.println("Hello, guess a number between 1 and 100.");

        while (userGuess != secret) {
            Scanner sc = new Scanner(System.in);
            userGuess = sc.nextInt();

            if (userGuess < secret) {
                if (Math.abs(userGuess - secret) < 5) {
                    System.out.println("Too low! Try again. (But you're warm)");
                } else {
                    System.out.println("Too low! Try again.");
                }
            } else if (userGuess > secret) {
                 if (Math.abs(userGuess - secret) < 5) {
                    System.out.println("Too high! Try again. (But you're warm)");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
        }
        System.out.println("Correct!");
    }

} 




/* task 
    1. Create a class called GuessTheNumber
    2. Class should generate a random number
        - next-int method
    3. Class for user to repeatedly guess numbers, the class will return if the actual num is higher or lower
*/

/* notes while building
    - nextInt is an instance method of Random (built in)
    - Scanner needs to know what it is scanning, thus Scanner(System.in);
    - nextInt is also a method of Scanner


*/