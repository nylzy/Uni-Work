public class NumberStack {

    private int[] stack;
    private int top;

    public NumberStack(int capacity) {
        stack = new int[capacity];
    }

    public void push(int value) {
        if (top >= stack.length) {
            System.out.println("Stack is full.");
        } else {
            stack[top] = value;
            top ++;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return 0;
        }
        top--;
        return stack[top];       
    }
    
    public int peek() {
        if (isEmpty()) {
            return 0;
        }
        return stack[top -1];
    }

    public boolean isEmpty() {
        if (top == 0) {
            return true;
        } else {
            return false;
        }
    }

}