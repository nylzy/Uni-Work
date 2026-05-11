package factorysim.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import factorysim.config.MachineConfig;
import org.junit.jupiter.api.Test;

public class MachineTest {

    private static MachineConfig processorConfig(
        String name,
        String inputItem,
        int inputAmount,
        String outputItem,
        int outputAmount,
        int cooldown
    ) {
        return new MachineConfig(name, cooldown)
            .addInput(inputItem, inputAmount, "in_belt")
            .addOutput(outputItem, outputAmount, "out_belt");
    }

    private static MachineConfig mineConfig(
        String name,
        String outputItem,
        int outputAmount,
        int cooldown
    ) {
        return new MachineConfig(name, cooldown)
            .addOutput(outputItem, outputAmount, "out_belt");
    }

    @Test
    void machineWithNoInputsActivatesImmediately() {
        Machine machine = new Machine(mineConfig("Iron Mine", "Iron Ore", 4, 0));

        machine.tick();

        OutputSource output = machine.getOutputPorts().get(0);
        assertTrue(output.canPull());
        assertEquals(1.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void machineDoesNotActivateWhenInputBatchIncomplete() {
        Machine machine = new Machine(
            processorConfig("Assembler", "Iron Ore", 2, "Iron Plate", 3, 0)
        );

        InputSource input = machine.getInputPorts().get(0);
        input.addItem();

        machine.tick();

        OutputSource output = machine.getOutputPorts().get(0);
        assertFalse(output.canPull());
        assertEquals(0.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void machineActivatesWhenInputBatchIsFull() {
        Machine machine = new Machine(
            processorConfig("Assembler", "Iron Ore", 2, "Iron Plate", 3, 0)
        );

        InputSource input = machine.getInputPorts().get(0);
        input.addItem();
        input.addItem();

        machine.tick();

        OutputSource output = machine.getOutputPorts().get(0);
        assertTrue(output.canPull());
        assertEquals(1.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void activationConsumesInputsAndFillsOutputs() {
        Machine machine = new Machine(
            processorConfig("Assembler", "Iron Ore", 2, "Iron Plate", 3, 0)
        );

        InputSource input = machine.getInputPorts().get(0);
        OutputSource output = machine.getOutputPorts().get(0);

        input.addItem();
        input.addItem();

        machine.tick();

        assertTrue(input.canAccept());
        assertTrue(output.canPull());
    }

    @Test
    void machineDoesNotReactivateUntilOutputIsEmpty() {
        Machine machine = new Machine(mineConfig("Iron Mine", "Iron Ore", 4, 0));

        machine.tick();
        machine.tick();

        assertEquals(0.5, machine.getUtilisation(), 0.001);
    }

    @Test
    void cooldownPreventsImmediateReactivation() {
        Machine machine = new Machine(mineConfig("Iron Mine", "Iron Ore", 4, 2));
        OutputSource output = machine.getOutputPorts().get(0);

        machine.tick();

        while (output.canPull()) {
            output.pullItem();
        }

        machine.tick();

        assertFalse(output.canPull());
        assertEquals(1.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void cooldownTicksCountAsUtilisedTime() {
        Machine machine = new Machine(mineConfig("Iron Mine", "Iron Ore", 4, 2));
        OutputSource output = machine.getOutputPorts().get(0);

        machine.tick();

        while (output.canPull()) {
            output.pullItem();
        }

        machine.tick();
        machine.tick();

        assertEquals(1.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void blockedTicksDoNotCountAsUtilisedTime() {
        Machine machine = new Machine(
            processorConfig("Assembler", "Iron Ore", 2, "Iron Plate", 3, 0)
        );

        machine.tick();
        machine.tick();

        assertEquals(0.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void resetStatisticsClearsUtilisationButPreservesMachineState() {
        Machine machine = new Machine(mineConfig("Iron Mine", "Iron Ore", 4, 0));
        OutputSource output = machine.getOutputPorts().get(0);

        machine.tick();

        assertTrue(output.canPull());
        assertEquals(1.0, machine.getUtilisation(), 0.001);

        machine.resetStatistics();

        assertTrue(output.canPull());
        assertEquals(0.0, machine.getUtilisation(), 0.001);
    }

    @Test
    void machineWithMultipleInputsRequiresAllInputs() {
        MachineConfig config = new MachineConfig("Concrete Mixer", 0)
            .addInput("Cement", 2, "cement_belt")
            .addInput("Gravel", 3, "gravel_belt")
            .addOutput("Concrete", 4, "concrete_belt");

        Machine machine = new Machine(config);

        InputSource cementInput = machine.getInputPorts().get(0);
        InputSource gravelInput = machine.getInputPorts().get(1);
        OutputSource output = machine.getOutputPorts().get(0);

        cementInput.addItem();
        cementInput.addItem();

        gravelInput.addItem();
        gravelInput.addItem();

        machine.tick();

        assertFalse(output.canPull());
        assertEquals(0.0, machine.getUtilisation(), 0.001);

        gravelInput.addItem();

        machine.tick();

        assertTrue(output.canPull());
        assertEquals(0.5, machine.getUtilisation(), 0.001);
    }
}