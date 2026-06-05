public class ObjectStack {
    private Object[] data;
    private int top;

    public ObjectStack(int capacity) {
        data = new Object[capacity];
        top = 0;
    }

    public void push(Object s) {
        if (top == data.length) {
            throw new RuntimeException("Stack is Full. Capacity is " + data.length);       
        }
        data[top++] = s;
    }

    public Object pop() {
        if (top == 0) {
            throw new RuntimeException("Stack is Empty");
        }
        return data[--top];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public static void main(String[] args) {
        ObjectStack ss = new ObjectStack(5);
        ss.push(3);
        ss.push("two");
        ss.push(1.0);
        System.out.println(ss.pop());
        System.out.println(ss.pop());
        System.out.println(ss.pop());
    }

}