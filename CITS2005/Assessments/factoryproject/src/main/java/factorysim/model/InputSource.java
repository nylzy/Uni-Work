package factorysim.model;

/**
 * Represents a source that can accept input items for a machine.
 *
 * This interface is used by components such as belt balancers to interact with
 * machine input ports without needing to know how the input is stored internally.
 */
public interface InputSource {

    /**
     * Returns the item type accepted by this input source.
     *
     * @return the item type accepted by this input source
     */
    String itemType();

    /**
     * Checks whether this input source can accept another item.
     *
     * @return true if another item can be added, false otherwise
     */
    boolean canAccept();

    /**
     * Adds one item to this input source.
     */
    void addItem();

    /**
     * Checks whether this input source has received enough items.
     *
     * @return true if the input requirement is satisfied, false otherwise
     */
    boolean isSatisfied();

    /**
     * Consumes the stored input items after the machine activates.
     */
    void consume();
}