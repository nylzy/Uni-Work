package factorysim.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a belt balancer that transfers items from machine output ports
 * to machine input ports.
 *
 * A belt balancer can only carry one item type. During each tock, it transfers
 * items in a round-robin order from available output ports to available input
 * ports.
 */
public class BeltBalancer implements Tockable, StatResettable {
    private String name;
    private String itemType;
    private List<OutputSource> outputPorts;
    private List<InputSource> inputPorts;
    private int outputTurn;
    private int inputTurn;

    // Statistics collected during the statistics window.
    private long itemsTransferred;
    private long tocksElapsed;

    /**
     * Creates a belt balancer with the given name.
     *
     * @param name the unique name of this belt balancer
     */
    public BeltBalancer(String name) {
        this.name = name;
        this.itemType = null;
        this.outputPorts = new ArrayList<>();
        this.inputPorts = new ArrayList<>();
        this.outputTurn = 0;
        this.inputTurn = 0;
        this.itemsTransferred = 0;
        this.tocksElapsed = 0;
    }

    /**
     * Connects a machine output port to this belt balancer.
     *
     * The first connected port determines the item type of the belt. Any later
     * connected port must have the same item type.
     *
     * @param port the output port to connect
     * @throws BeltValidationException if the port item type does not match this belt's item type
     */
    public void addOutputPort(OutputSource port) throws BeltValidationException {
        if (itemType == null) {
            itemType = port.itemType();
        } else if (!itemType.equals(port.itemType())) {
            throw new BeltValidationException(
                    "Belt " + name + " has mixed item types: "
                            + itemType + " and " + port.itemType()
            );
        }
        outputPorts.add(port);
    }

    /**
     * Connects a machine input port to this belt balancer.
     *
     * The first connected port determines the item type of the belt. Any later
     * connected port must have the same item type.
     *
     * @param port the input port to connect
     * @throws BeltValidationException if the port item type does not match this belt's item type
     */
    public void addInputPort(InputSource port) throws BeltValidationException {
        if (itemType == null) {
            itemType = port.itemType();
        } else if (!itemType.equals(port.itemType())) {
            throw new BeltValidationException(
                    "Belt " + name + " has mixed item types: "
                            + itemType + " and " + port.itemType()
            );
        }
        inputPorts.add(port);
    }

    /**
     * Performs one tock of belt movement.
     *
     * The belt repeatedly transfers one item at a time while there is at least
     * one output port with available items and at least one input port with
     * remaining capacity. Output and input ports are selected using persistent
     * round-robin turns.
     */
    @Override
    public void tock() {
        tocksElapsed++;

        while (true) {
            // Find the next output port with an item, starting from the saved round-robin turn.
            int found = -1;
            for (int i = 0; i < outputPorts.size(); i++) {
                int idx = (outputTurn + i) % outputPorts.size();
                if (outputPorts.get(idx).canPull()) {
                    found = idx;
                    break;
                }
            }

            // No output ports have items available, so this belt cannot transfer more.
            if (found == -1) {
                break;
            }

            // Find the next input port with free capacity, starting from the saved round-robin turn.
            int foundInput = -1;
            for (int i = 0; i < inputPorts.size(); i++) {
                int idx = (inputTurn + i) % inputPorts.size();
                if (inputPorts.get(idx).canAccept()) {
                    foundInput = idx;
                    break;
                }
            }

            // No input ports can accept items, so this belt cannot transfer more.
            if (foundInput == -1) {
                break;
            }

            // Transfer exactly one item from the chosen output port to the chosen input port.
            outputPorts.get(found).pullItem();
            inputPorts.get(foundInput).addItem();
            itemsTransferred++;

            // Advance both round-robin turns so the next transfer starts at the following port.
            outputTurn = (found + 1) % outputPorts.size();
            inputTurn = (foundInput + 1) % inputPorts.size();
        }
    }

    /**
     * Returns the name of this belt balancer.
     *
     * @return this belt's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the item type carried by this belt balancer.
     *
     * If no ports have been connected yet, this may be null.
     *
     * @return the item type carried by this belt
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Calculates the average number of items transferred per minute.
     *
     * @return the average item throughput per minute
     */
    public double getAvgItemsPerMinute() {
        if (tocksElapsed == 0) {
            return 0.0;
        }
        return ((double) itemsTransferred / tocksElapsed) * 60;
    }

    /**
     * Resets this belt's collected statistics.
     *
     * This clears the number of transferred items and elapsed tocks, but does
     * not reset connected ports or round-robin turn positions.
     */
    @Override
    public void resetStatistics() {
        itemsTransferred = 0;
        tocksElapsed = 0;
    }
}