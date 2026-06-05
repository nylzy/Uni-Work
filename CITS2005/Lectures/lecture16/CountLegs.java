class Chair implements HasLegs {
    public int countLegs() {
        return 4;
    }
}

class Person implements HasLegs {
    public int countLegs() {
        return 2;
    }
}

class Spider implements HasLegs {
    public int countLegs() {
        return 8;
    }
}

public class CountLegs {

    public static void main(String[] args) {
        HasLegs[] things = new HasLegs[3];
        things[0] = new Chair();
        things[1] = new Person();
        things[2] = new Spider();
        // sum up the total number of legs
    }
}