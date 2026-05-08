public class StringStack {

    private String[] data;
    private int top;

    public StringStack(int capacity) {
        data = new String[capacity];
        top = 0;
    }

    public void push(String s) {
        if (top < data.length()) {
            data[top] = s;
            top += 1;
        } else {
            System.out.println("The stack is full.");
        }    

    }

    public String pop() {
        if (top > 0) {
            top -= 1;
            return data[top + 1];
        } else {
            return "The stack is empty";
        }
    }

    public static void main(String[] args) {
        StringStack ss = new StringStack(5);
        ss.push("Hello");
        ss.push("World");
        ss.pop();
        ss.pop();
    }
}
