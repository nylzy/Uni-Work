import java.util.Random;

public class secretNumber {

    public static void main(String[] Args) {
        int secretNumber = new Random().nextInt(100) + 1;
        System.out.println(secretNumber);
    }

}