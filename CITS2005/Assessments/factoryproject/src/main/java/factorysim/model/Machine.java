package factorysim.model;
import factorysim.config.MachineConfig;
import factorysim.config.PortConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation model for a machine.
 */
public class Machine implements Tickable, StatResettable {

    /**
     * This is a suggested design for the Machine class if you are stuck
     * 
     * You do not need to follow this design, if you have a different
     * idea in mind, as long as what you do is compatible with the 
     * Sink and FactoryNetwork classes!
     * 
     * Design considerations:
     * - The spec states a machine should have a name and a cooldown period.
     * - It also needs to have some way of tracking its input and output ports (their capacity, what they currently hold, and what items they store), and its state (e.g. is it in cooldown? For how many moreticks?)
     * - A machine constructor could either take in a MachineConfig object, or 
     * these fields individually.
     * - A machine activates on a tick, so what provided interface could be useful here?
     * - On a tick, a machine will need to check things like if it can activate/if it is blocked/if it is currently in a cooldown state, and then implement activation (take items from input port, and produce a batch of output), or decrement cooldown remaining etc. 
     * - remember you can use private methods and other classes (e.g. to represent sub-components of a machine) to break down the machine component and the activation logic!
     * - You will also need to track machine statistics. You might need some more class fields to help! Read the section of the spec around machine statistics.
     * - The Sink class had a method for calculating avg items consumed. What is the equivalent here?
     * - If we're tracking statistics, what other interface should this class perhaps implement?
     * 
     */

    /**
     * Represents one input storage port on a machine.
     *
     * Each input port stores one required input item type and has enough capacity
     * for one activation batch.
     */
    private class InputPort implements InputSource {
        String itemType;
        int capacity;
        int contents;
        
        // Creates an input port for the given item type and batch capacity.
        private InputPort(String itemType, int capacity) {
            this.itemType = itemType;
            this.capacity = capacity;
            this.contents = 0;
        }

        /**
         * Checks whether this input port has space for another item.
         *
         * @return true if another item can be added, false if the port is full
         */
        public boolean canAccept() {
            return contents < capacity;
        }

        /**
         * Adds one item to this input port.
         */
        public void addItem() {
            contents++;
        }

        /**
         * Checks whether this input port contains a full required batch.
         *
         * @return true if the port is full, false otherwise
         */
        public boolean isSatisfied() {
            return contents == capacity;
        }

        /**
         * Consumes the stored input batch.
         *
         * This is called when the owning machine activates.
         */
        public void consume() {
            contents = 0;
        }

        /**
         * Returns the item type accepted by this input port.
         *
         * @return the input item type
         */
        public String itemType() {
            return itemType;
        }
    }

    /**
     * Represents one output storage port on a machine.
     *
     * Each output port stores one produced item type and can hold one activation
     * batch of that output item.
     */
    private class OutputPort implements InternalOutputPort {
        String itemType;
        int capacity;
        int contents;

        // Creates an output port for the given item type and batch capacity.
        private OutputPort(String itemType, int capacity) {
            this.itemType = itemType;
            this.capacity = capacity;
            this.contents = 0;
        }

        /**
         * Checks whether this output port has no stored items.
         *
         * @return true if the output port is empty, false otherwise
         */
        public boolean isEmpty() {
            return contents == 0;
        }

        /**
         * Fills this output port with one full output batch.
         */
        public void fill() {
            contents = capacity;
        }

        /**
         * Returns the item type produced by this output port.
         *
         * @return the output item type
         */
        public String itemType() { 
            return itemType;
        }

        /**
         * Checks whether an item can be pulled from this output port.
         *
         * @return true if the port contains at least one item, false otherwise
         */
        public boolean canPull() {
            return contents > 0;
        }

        /**
         * Removes one item from this output port.
         */
        public void pullItem() {
            contents--;
        }
    }

    // Machine fields
    private String name;
    private int cooldown;
    private List<InputSource> inputs;
    private List<InternalOutputPort> outputs;
    private boolean cooldownState;
    private int cooldownRemaining;

    // Stats fields
    private int ticksElapsed;
    private int ticksUtilised;

    /**
     * Creates a machine from the provided machine configuration.
     *
     * The configuration determines the machine's name, cooldown period, input
     * ports, and output ports.
     *
     * @param config the parsed machine configuration
     */
    public Machine(MachineConfig config) {
        this.name = config.getName();
        this.cooldown = config.getCooldown();
        this.inputs = new ArrayList<>();
        for (PortConfig port : config.getInputConfigs()) {
            inputs.add(new InputPort(port.getItemName(), port.getAmount()));
        }
        this.outputs = new ArrayList<>();
        for (PortConfig port : config.getOutputConfigs()) {
            outputs.add(new OutputPort(port.getItemName(), port.getAmount()));
        }
        this.cooldownState = false;
        this.cooldownRemaining = 0;
        this.ticksElapsed = 0;
        this.ticksUtilised = 0;
    }

    // Checks whether this machine is currently able to activate.
    // A machine can activate only when it is not cooling down,
    // all output ports are empty, and all required input ports contain a full batch.
    private boolean canActivate() {
        if (cooldownState) {
            return false;
        }
        for (InternalOutputPort port : outputs) {
            if (!port.isEmpty()) {
                return false;
            }
        }
        for (InputSource port : inputs) {
            if (!port.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Performs one tick of this machine.
     *
     * On a tick, the machine either continues cooling down, activates if all
     * activation requirements are met, or remains blocked. Activation and
     * cooldown ticks count as utilised time.
     */
    @Override
    public void tick() {
        if (cooldownState) {
            cooldownRemaining--;
            if (cooldownRemaining == 0) {
                cooldownState = false;
            }
            ticksUtilised++;
            ticksElapsed++;
        } else if (canActivate()) {
            for (InputSource port : inputs) {
                port.consume();
            }
            for (InternalOutputPort port : outputs) {
                port.fill();
            }
            if (cooldown > 0) {
                cooldownState = true;
                cooldownRemaining = cooldown;
            }
            ticksElapsed++;
            ticksUtilised++;
        } else {
            ticksElapsed++;
        }
    }

    /**
     * Returns this machine's name.
     *
     * @return the machine name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this machine's output ports.
     *
     * The returned list is a copy, so callers cannot modify the machine's
     * internal list of output ports.
     *
     * @return a list of output ports
     */
    public List<OutputSource> getOutputPorts() {
        return new ArrayList<>(outputs);
    }

    /**
     * Returns this machine's input ports.
     *
     * The returned list is a copy, so callers cannot modify the machine's
     * internal list of input ports.
     *
     * @return a list of input ports
     */
    public List<InputSource> getInputPorts() {
        return new ArrayList<>(inputs);
    }

    /**
     * Calculates this machine's utilisation.
     *
     * Utilisation is the fraction of elapsed statistic ticks where the machine
     * was either activating or cooling down.
     *
     * @return the machine utilisation as a value from 0.0 to 1.0
     */
    public double getUtilisation() {
        if (ticksElapsed == 0) return 0.0;
        return (double) ticksUtilised / ticksElapsed;
    }

    /**
     * Resets this machine's collected statistics.
     *
     * This clears elapsed and utilised tick counters, but does not reset stored
     * inputs, stored outputs, or cooldown state.
     */
    @Override
    public void resetStatistics() {
        ticksElapsed = 0;
        ticksUtilised = 0;
    }
}