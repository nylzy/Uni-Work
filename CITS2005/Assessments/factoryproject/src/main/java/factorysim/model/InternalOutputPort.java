package factorysim.model;

/**
 * Represents an internal machine output port.
 *
 * This extends OutputSource so that belts and sinks can pull items from the
 * port, while also exposing extra methods needed by the Machine class to check
 * and fill its own output storage.
 */
public interface InternalOutputPort extends OutputSource {

    /**
     * Checks whether this output port currently has no stored items.
     *
     * @return true if the output port is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Fills this output port with one full batch of output items.
     *
     * The amount added is determined by the output port's configured capacity.
     */
    void fill();
}