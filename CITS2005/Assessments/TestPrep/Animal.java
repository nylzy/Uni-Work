public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public String speak() { return "..."; }
}

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Woof";
    }
}

public class GuideDog extends Dog {

    @Override
    public String speak() {
        return super.speak() + " - I am " + getName();
    }
}