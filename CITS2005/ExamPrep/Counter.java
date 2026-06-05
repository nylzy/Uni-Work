public interface Counter {
    int getCount();
    void step();
}

public class BasicCounter implements Counter{

    private int count;

    public BasicCounter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void step() {
        count += 1;
    }
}

/*
- an abstract class is a class which can have methods, but does not actually explain how the method works, or specify how it should be implemented. an abstract class can be extended by another class which will actually implement those methods
- an interface is not a class, but rather just a contract which specifies what any class that 'implements' that interface should do (what methods it should have) it also does not specify how they should be implemented specifically
*/
