import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SlotMachineTest {
    private SlotMachine slotMachine;

    @BeforeEach
    public void setUp() {
        slotMachine = new SlotMachine();
    }

    @Test
    public void testGetSymbol_ValidSymbol_ReturnsCorrectUTF8Representation() {
        assertEquals("🍒", slotMachine.getSymbol("Cherry"));
        assertEquals("❤️", slotMachine.getSymbol("Heart"));
    }

    @Test
    public void testGetSymbol_InvalidSymbol_ReturnsNull() {
        assertNull(slotMachine.getSymbol("InvalidSymbol"));
    }

    @Test
    public void testSpin() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create SlotMachine object
        SlotMachine slotMachine = new SlotMachine();

        // Call spin method
        slotMachine.spin();

        // Restore original System.out
        System.setOut(originalOut);

        // Get the output
        String output = outputStream.toString().trim();

        // Verify output contains "Spinning..."
        assertTrue(output.contains("Spinning..."));
    }

    @Test
    public void testMultipleSpins() {
        for (int i = 0; i < 5; i++) { // Perform 5 spins
            // Redirect System.out to capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Create SlotMachine object
            SlotMachine slotMachine = new SlotMachine();

            // Call spin method
            slotMachine.spin();

            // Restore original System.out
            System.setOut(originalOut);

            // Get the output
            String output = outputStream.toString().trim();

            // Verify output contains "Spinning..."
            assertTrue(output.contains("Spinning..."));
        }
    }

    @Test
    public void testJackpotDetection() {
        String[] jackpotResult = {"🍒", "🍒", "🍒"}; // All reels show the same symbol
        assertTrue(slotMachine.displayJackpot(jackpotResult));

        String[] nonJackpotResult = {"🍒", "❤️", "🍊"}; // Reels show different symbols
        assertFalse(slotMachine.displayJackpot(nonJackpotResult));
    }

    @Test
    public void testEmptySymbolSet() {
        SlotMachine emptySlotMachine = new SlotMachine();
        emptySlotMachine.initializeSymbols(); // Clear symbols

        // Ensure spin method does not throw exceptions
        assertDoesNotThrow(() -> emptySlotMachine.spin());
    }

    @Test
    public void testSingleSymbol() {
        SlotMachine singleSymbolSlotMachine = new SlotMachine();
        singleSymbolSlotMachine.initializeSymbols();
        singleSymbolSlotMachine.symbols.clear();
        singleSymbolSlotMachine.symbols.put("Cherry", "🍒"); // Only one symbol available

        // Ensure spin method does not throw exceptions
        assertDoesNotThrow(singleSymbolSlotMachine::spin);
    }
}

