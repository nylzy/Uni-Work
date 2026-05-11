package factorysim.model;
import factorysim.config.MachineConfig;
import factorysim.config.PortConfig;

import factorysim.stats.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * FactoryNetwork models the overall factory, including connections 
 * between machines, belts, and sinks.
 * Implements the FactoryNetwork interface.
 */
public final class FactoryNetworkImpl implements FactoryNetwork {

    private Sink sink;
    
    // YOUR FIELDS HERE. You probably want to track the other main factory components
    private List<Machine> machines;
    private Map<String, BeltBalancer> belts;

    /**
     * Establish a factory network.
     * Most of the logic for this class is in the constructor
     * (though you might find private methods useful to break it up)
     * 
     * @param machineConfigs a list of machine configs from the 
     * configuration file (in the same order they were originally in
     * i.e. machineConfigs.get(0) would have been the first listed machine in the config file)
     * @throws BeltValidationException If the list of machineConfigs tries to wire a sink to a
     * machine input port, or tries to connect a belt to two ports with different types, 
     * the constructor should throw a BeltValidationException with an informative error message.
     */
    public FactoryNetworkImpl(List<MachineConfig> machineConfigs) throws BeltValidationException {

        // The constructor should initialise your factory components (Sink has been initialised for you below). 
        // It should "wire" the components together (i.e. connect machine output ports to belts/sinks etc), 
        // based on the layout specified in the machine configs.
        // It should also check that the provided config doesn't suggest wiring a belt in 
        // an incorrect way (i.e. you can't wire a sink to a machine input port)
        // a BeltValidationException. You might even find it useful to do this first,
        // It is up to you how to approach this/ what data structures you use.

        this.sink = new Sink();
        this.machines = new ArrayList<>();
        this.belts = new LinkedHashMap<>();

        for (MachineConfig config : machineConfigs) {
            machines.add(new Machine(config));
        }

        for (int i = 0; i < machineConfigs.size(); i++) {
            MachineConfig config = machineConfigs.get(i);
            Machine machine = machines.get(i);
            List<PortConfig> outputConfigs = config.getOutputConfigs();
            List<OutputSource> outputPorts = machine.getOutputPorts();
            for (int j = 0; j < outputConfigs.size(); j++) {
                String beltName = outputConfigs.get(j).getBeltName();
                OutputSource port = outputPorts.get(j);
                if (beltName.equals(Sink.BELT_NAME)) {
                    sink.addSource(port);
                } else {
                    if (!belts.containsKey(beltName)) {
                        belts.put(beltName, new BeltBalancer(beltName));
                    }
                    belts.get(beltName).addOutputPort(port);
                }
            }
        }

        for (int i = 0; i < machineConfigs.size(); i++) {
            MachineConfig config = machineConfigs.get(i);
            Machine machine = machines.get(i);
            List<PortConfig> inputConfigs = config.getInputConfigs();
            List<InputSource> inputPorts = machine.getInputPorts();
            for (int j = 0; j < inputConfigs.size(); j++) {
                String beltName = inputConfigs.get(j).getBeltName();
                InputSource port = inputPorts.get(j);
                if (beltName.equals(Sink.BELT_NAME)) {
                    throw new BeltValidationException("Sink cannot be connected to a machine input port: " + beltName);
                } else {
                    if (!belts.containsKey(beltName)) {
                        belts.put(beltName, new BeltBalancer(beltName));
                    }
                    belts.get(beltName).addInputPort(port);
                }
            }
        }
    }

    // This class should implement the FactoryNetwork specification.
    // You should think about what methods this involves.
    // If you've used a good object oriented design on factory components, these methods might be quite simple.

    /**
     * Performs one tick of the simulation.
     *
     * During the tick phase, each machine updates its own state. Machines may
     * activate, continue cooling down, or remain blocked depending on their inputs,
     * outputs, and cooldown state.
     */
    @Override
    public void tick() {
        for (Machine machine : machines) {
            machine.tick();
        }
    }

    /**
     * Performs one tock of the simulation.
     *
     * During the tock phase, belt balancers transfer items between machine ports,
     * then the sink consumes all available items from machine outputs connected to
     * it.
     */
    @Override
    public void tock() {
        for (BeltBalancer belt : belts.values()) {
            belt.tock();
        }
        sink.tock();
    }

    /**
     * Resets all statistics collected by the factory network.
     *
     * This is used after the warmup period so that only the statistics collection
     * window contributes to the final report. It does not reset the physical state
     * of machines, belts, or the sink.
     */
    @Override
    public void resetStatistics() {
        for (Machine machine : machines) {
            machine.resetStatistics();
        }
        for (BeltBalancer belt : belts.values()) {
            belt.resetStatistics();
        }
        sink.resetStatistics();
    }

    /**
     * Gets the current sink throughput statistics.
     *
     * Each entry contains an item type consumed by the sink and its average
     * consumption rate in items per minute.
     *
     * @return a list of sink statistic entries
     */
    @Override
    public List<SinkEntry> getSinkStats() {
        List<SinkEntry> stats = new ArrayList<>();
        for (String itemType : sink.getItemTypes()) {
            stats.add(new SinkEntry(itemType, sink.getAvgItemsPerMinute(itemType)));
        }
        return stats;
    }

    /**
     * Gets the current machine utilisation statistics.
     *
     * Each entry contains a machine name and its utilisation as a fraction between
     * 0.0 and 1.0.
     *
     * @return a list of machine statistic entries
     */
    @Override
    public List<MachineStats> getMachineStats() {
        List<MachineStats> stats = new ArrayList<>();
        for (Machine machine : machines) {
            stats.add(new MachineStats(machine.getName(), machine.getUtilisation()));
        }
        return stats;
    }

    /**
     * Gets the current belt throughput statistics.
     *
     * Each entry contains a belt name, the item type carried by that belt, and the
     * average number of items transferred per minute.
     *
     * @return a list of belt statistic entries
     */
    @Override
    public List<BeltStats> getBeltStats() {
        List<BeltStats> stats = new ArrayList<>();
        for (BeltBalancer belt : belts.values()) {
            stats.add(new BeltStats(belt.getName(), belt.getItemType(), belt.getAvgItemsPerMinute()));
        }
        return stats;
    }
}