public class Vehicle {
    private String brand;
    private int speed;

    public Vehicle(String brand) {
        this.brand = brand;
        this.speed = 0;
    }

    public void accelerate(int amount) { speed += amount; }
    public void brake(int amount) { speed -= amount; }
    public int getSpeed() { return speed; }
    public String getBrand() { return brand; }
}

public class Car extends Vehicle {
    private boolean engineOn;

    public Car(String brand) {
        super(brand);
    }

    public void startEngine() {
        engineOn = true;
    }

    public boolean isEngineOn() {
        return engineOn;
    }
}

public class ElectricCar extends Car {
    int batteryLevel = 100;

    @Override
    public void accelerate(int amount) {
        super.accelerate(amount);
        batteryLevel -= amount;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}