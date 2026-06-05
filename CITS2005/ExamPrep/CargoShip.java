interface Cargo {
    double getMass();    // mass in kg
    double getVolume();  // volume in litres
    boolean isContainer; // is it a standard shipping container
}

public class CargoShip implements Cargo {
    private double maxMass;
    private double maxVolume;
    private double currentMass;
    private double currentVolume;

    public CargoShip(double mass, double volume) {
        this.maxMass = mass;
        this.maxVolume = volume;
        this.currentMass = 0;
        this.currentVolume = 0;
    }

    public boolean addCargo(int cargo) {
        if ((cargo + currentMass) < maxMass) {
            currentMass = currentMass + cargo;
            return true;
        } 
        else {
            return false;
        }
    }
}

public class ContainerShip extends CargoShip {

    public ContainerShip(double mass, double volume, boolean container) {
        super(mass, volume);
        this.isContainer = container;
    }

    @Override
    public boolean addCargo(int cargo, boolean isContainer) {
        if (isContainer) {
            if ((cargo + currentMass) < maxMass) {
            currentMass = currentMass + cargo;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }