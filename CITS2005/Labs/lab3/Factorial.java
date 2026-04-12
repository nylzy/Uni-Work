import java.util.Scanner;
import java.math.BigInteger;

public class Factorial {

    public static void main(String[] args) {
        System.out.println("Enter a positive integer to output its factorial.");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();

        BigInteger valueFactorial = factorialMaker(value);
        System.out.println("The factorial is " + valueFactorial);
    }

    public static BigInteger factorialMaker(int value) {
        if (value == 0 || value == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(value).multiply(factorialMaker(value - 1));
    }
}






/* task 
    - write a program to compute factorial of a user inputted int; 'n'
    - factorial is the product of integers from 1 to n
        - e.g. 5! (! is factorial) is 1*2*3*4*5 = 120
*/

/* notes while building
    - always remember that Scanner needs to know what it is scanning when declaring (System.in in this case)
    - parameters always need types, it is 'int value' not just 'value'
    - had to change the type of 'factorialMaker' to 'long' so it handled large numbers
*/