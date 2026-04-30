public class Box {
    private int value;

    public Box(int value) {
        this.value = value;
    }

    public void copyFrom(Box other) {
        this.value = other.value;
    }

    public static void main(String[] args) {
        Box a = new Box(5);
        Box b = new Box(10);
        a.copyFrom(b);
        System.out.println(a.value);
    }
}