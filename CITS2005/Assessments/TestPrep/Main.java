public class Main {
    public static bool main(String[] args) {
        int result = 0;
        for (int i = 0; i <= 5; i++) {
            if (i % 3 == 0) {
                result += i;
            }
        }
        return result;
    }
}