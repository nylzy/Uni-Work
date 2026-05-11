import java.util.InputMismatchException;
import java.util.Scanner;


public class ArrayException {

    public static void main(String[] args) {
        int[] a = {882, 2, 11};
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Index: ");
        try {
            int index = sc.nextInt();
            System.out.println(a[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter an int lower than 3.");
        } catch (InputMismatchException e) {
            System.out.println("Enter an int.");
        }
    }
}

/* Notes:
- try blocks can have multiple catch statements within them
- we can create our own exception cases by extending 'Exception'
- Exceptions inherit from 'throwables'
*/