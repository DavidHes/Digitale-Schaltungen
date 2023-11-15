import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TruthTableGenerator {

    private static final Random random = new Random();


    public TruthTableGenerator(int numInputs, int numOutputs) {
        generateRandomCircuit();
    }

    private static void generateRandomCircuit() {
        System.out.println("Random Circuit:");

        // Anzahl der Eingänge und maximale Anzahl von Gattern
        int numInputs = 4; // Beispiel: 4 Eingänge
        int maxGates = 6;   // Beispiel: Maximal 6 Gatter

        // Liste von möglichen Gattern
        String[] gates = {"AND", "OR", "NOT", "XOR"};

        // Header
        System.out.print("|");
        for (int i = 0; i < numInputs; i++) {
            System.out.printf("  A%d  |", i + 1);
        }
        System.out.println("   Schaltung");

        // Separator
        System.out.print("+");
        for (int i = 0; i < numInputs + 1; i++) {
            System.out.print("------+");
        }
        System.out.println("-------------");

        // Zufällige Auswahl von Gattern und Erstellung der Schaltung
        List<String> circuit = new ArrayList<>();
        for (int i = 0; i < numInputs; i++) {
            int input = random.nextInt(2); // Zufällige Auswahl von 0 oder 1
            System.out.printf("   %d  |", input);
            circuit.add("   " + input + "  |");
        }

        for (int i = 0; i < maxGates; i++) {
            String gate = getRandomGate(gates);
            circuit.add("   " + gate + "  |");

            if (gate.equals("NOT")) {
                circuit.add("   " + "NOT A" + "  |");
            } else {
                // Zufällige Auswahl von A oder B
                String inputA = "A" + (random.nextInt(numInputs) + 1);
                String inputB = "A" + (random.nextInt(numInputs) + 1);
                while (inputB.equals(inputA)) {
                    inputB = "A" + (random.nextInt(numInputs) + 1);
                }
                circuit.add("   " + inputA + " " + gate + " " + inputB + "  |");
            }
        }

        // Separator
        System.out.print("+");
        for (int i = 0; i < numInputs + 1; i++) {
            System.out.print("------+");
        }
        System.out.println();

        // Zeige die generierte Schaltung
        circuit.forEach(System.out::println);
    }

    private static String getRandomGate(String[] gates) {
        return gates[random.nextInt(gates.length)];
    }
}