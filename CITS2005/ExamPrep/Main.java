public class Main {
    public static void change(Integer x, String[] arr) {
        x++;
        arr[0] = "changed";
    }

    public static void main(String[] args) {
        Integer n = 5;
        String[] words = {"hello"};
        change(n, words);
        System.out.println(n);
        System.out.println(words[0]);
    }
}