import java.util.*;
import java.util.concurrent.TimeUnit;

public class SlotMachine {
    final Map<String, String> symbols;
    private final Random random;
    private final Scanner scanner;

    public SlotMachine() {
        symbols = new HashMap<>();
        initializeSymbols();
        random = new Random();
        scanner = new Scanner(System.in);
    }

    void initializeSymbols() {
        symbols.put("Cherry", "🍒");
        symbols.put("Single Bar", "─");
        symbols.put("Double Bar", "║");
        symbols.put("Triple Bar", "╬");
        symbols.put("Seven", "7️⃣");
        symbols.put("Bell", "🔔");
        symbols.put("Diamond", "💎");
        symbols.put("Orange", "🍊");
        symbols.put("Lemon", "🍋");
        symbols.put("Grape", "🍇");
        symbols.put("Star", "⭐");
        symbols.put("Coin", "💰");
        symbols.put("Horseshoe", "🐴");
        symbols.put("Heart", "❤️");
    }

    // Getter method to access symbols
    public String getSymbol(String name) {
        return symbols.get(name);
    }

    // Method to spin the slot machine
    public boolean spin() {
        String[] symbolsArray = symbols.values().toArray(new String[0]);
        int numSymbols = symbolsArray.length;
        String[] result = new String[3];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Spinning...");
                for (int k = 0; k < 3; k++) {
                    int index = random.nextInt(numSymbols);
                    result[j] = symbolsArray[index];
                    System.out.print(" " + symbolsArray[index] + " ");
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println();
            }
        }
        return displayJackpot(result);
    }

    // Method to display the result and check for jackpot
    boolean displayJackpot(String[] result) {
        if (result[0].equals(result[1]) && result[1].equals(result[2])) {
            System.out.println("Jackpot!");
            return true;
        }
        System.out.println("No jackpot, better luck next time!");
        return false;
    }

    public static void main(String[] args) {
        int numSpins = 5;
        SlotMachine slotMachine = new SlotMachine();
        while (numSpins > 0) {
            System.out.println("Spins remaining: " + numSpins);
            System.out.print("Press any key to spin... ");
            slotMachine.scanner.next();
            if (!slotMachine.spin()) {
                numSpins--;
            }
        }
        System.out.println("Thanks for playing!");
    }
}
