import aufgabe1.Note;
import aufgabe2.Mathe;
import aufgabe3.Arithmetik;
import aufgabe4.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTING AUFGABE 1: Note ===\n");
        testAufgabe1();

        System.out.println("\n=== TESTING AUFGABE 2: Mathe ===\n");
        testAufgabe2();

        System.out.println("\n=== TESTING AUFGABE 3: Arithmetik ===\n");
        testAufgabe3();

        System.out.println("\n=== TESTING AUFGABE 4: Shop ===\n");
        testAufgabe4();
    }

    // ============= AUFGABE 1 TESTS =============
    public static void testAufgabe1() {
        Note noteHandler = new Note();

        // Create test data: [29, 37, 38, 41, 84, 67]
        ArrayList<Note> notenListe = createNoteList(new int[]{29, 37, 38, 41, 84, 67});

        System.out.println("Test data: [29, 37, 38, 41, 84, 67]");

        // Test 1: Nicht ausreichende Noten (< 40)
        List<Integer> nichtAusreichend = noteHandler.nichtAusreichend(notenListe);
        System.out.println("1. Nicht ausreichende Noten: " + nichtAusreichend);
        System.out.println("   Expected: [29, 37, 38]");

        // Test 2: Durchschnitt
        double durchschnitt = noteHandler.durchschnitt(notenListe);
        System.out.println("2. Durchschnitt: " + String.format("%.2f", durchschnitt));
        System.out.println("   Expected: 49.33");

        // Test 3: Abgerundete Noten
        List<Integer> abgerundet = noteHandler.abgerundeteNoten(notenListe);
        System.out.println("3. Abgerundete Noten: " + abgerundet);
        System.out.println("   Expected: [29, 37, 40, 41, 85, 67]");

        // Test 4: Maximale abgerundete Note
        int maxAbgerundet = noteHandler.maximaleAbgerundeteNote(notenListe);
        System.out.println("4. Maximale abgerundete Note: " + maxAbgerundet);
        System.out.println("   Expected: 85");

        //System.out.println(noteHandler.nichtAusreichend(noteHandler.abgerundeteNoten(notenListe)));

        // Additional test cases
        System.out.println("\n--- Additional Rounding Tests ---");
        ArrayList<Note> roundingTests = createNoteList(new int[]{73, 67, 38, 33});
        List<Integer> roundedResults = noteHandler.abgerundeteNoten(roundingTests);
        System.out.println("Input: [73, 67, 38, 33]");
        System.out.println("Rounded: " + roundedResults);
        System.out.println("Expected: [75, 67, 40, 33]");
    }

    // ============= AUFGABE 2 TESTS =============
    public static void testAufgabe2() {
        Mathe mathe = new Mathe();

        List<Integer> zahlen = Arrays.asList(4, 8, 3, 10, 17);
        System.out.println("Test data: " + zahlen);

        // Test 1: Maximale Zahl
        int max = mathe.maximaleZahl(zahlen);
        System.out.println("1. Maximale Zahl: " + max);
        System.out.println("   Expected: 17");

        // Test 2: Minimale Zahl
        int min = mathe.minimaleZahl(zahlen);
        System.out.println("2. Minimale Zahl: " + min);
        System.out.println("   Expected: 3");

        // Test 3: Maximale Summe von n-1 Zahlen
        List<Integer> zahlenCopy1 = new ArrayList<>(zahlen);
        int maxSum = mathe.maxSumNmin1(zahlenCopy1);
        System.out.println("3. Maximale Summe von n-1 Zahlen: " + maxSum);
        System.out.println("   Expected: 39 (exclude smallest: 4+8+10+17)");

        // Test 4: Minimale Summe von n-1 Zahlen
        List<Integer> zahlenCopy2 = new ArrayList<>(zahlen);
        int minSum = mathe.minSumNmin1(zahlenCopy2);
        System.out.println("4. Minimale Summe von n-1 Zahlen: " + minSum);
        System.out.println("   Expected: 25 (exclude largest: 4+8+3+10)");
    }

    // ============= AUFGABE 3 TESTS =============
    public static void testAufgabe3() {
        Arithmetik arithmetik = new Arithmetik();

        // Test 1: Summe
        System.out.println("--- Test 1: Summe ---");
        List<Integer> zahl1 = Arrays.asList(1, 3, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> zahl2 = Arrays.asList(8, 7, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> summe = arithmetik.Summe(zahl1, zahl2);
        System.out.println("130000000 + 870000000 = " + listToString(summe));
        System.out.println("Expected: [1, 0, 0, 0, 0, 0, 0, 0, 0, 0]");

        // Test 2: Differenz
        System.out.println("\n--- Test 2: Differenz ---");
        List<Integer> zahl3 = Arrays.asList(8, 3, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> zahl4 = Arrays.asList(5, 4, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> differenz = arithmetik.Differenz(zahl3, zahl4);
        System.out.println("830000000 - 540000000 = " + listToString(differenz));
        System.out.println("Expected: [2, 9, 0, 0, 0, 0, 0, 0, 0]");

        // Test 3: Produkt mit Ziffer
        System.out.println("\n--- Test 3: Multiplikation ---");
        List<Integer> zahl5 = Arrays.asList(2, 3, 6, 0, 0, 0, 0, 0, 0);
        List<Integer> produkt = arithmetik.ProduktMitZiffer(zahl5, 2);
        System.out.println("236000000 * 2 = " + listToString(produkt));
        System.out.println("Expected: [4, 7, 2, 0, 0, 0, 0, 0, 0]");

        // Test 4: Division durch Ziffer
        System.out.println("\n--- Test 4: Division ---");
        List<Integer> zahl6 = Arrays.asList(2, 3, 6, 0, 0, 0, 0, 0, 0);
        List<Integer> division = arithmetik.DivisionDurchZiffer(zahl6, 2);
        System.out.println("236000000 / 2 = " + listToString(division));
        System.out.println("Expected: [1, 1, 8, 0, 0, 0, 0, 0, 0]");

        // Additional tests with smaller numbers
        System.out.println("\n--- Additional Tests ---");
        List<Integer> small1 = Arrays.asList(1, 2, 3);
        List<Integer> small2 = Arrays.asList(4, 5, 6);
        System.out.println("123 + 456 = " + listToString(arithmetik.Summe(small1, small2)));
        System.out.println("Expected: [5, 7, 9]");
    }

    // ============= AUFGABE 4 TESTS =============
    public static void testAufgabe4() {
        Shop shop = new Shop();

        // Test 1: Billigste Tastatur
        System.out.println("--- Test 1: Billigste Tastatur ---");
        List<Tastatur> tastaturen1 = createTastaturList(new int[]{40, 35, 70, 15, 45});
        int billigste = shop.billigsteTastatur(tastaturen1);
        System.out.println("Preise: [40, 35, 70, 15, 45]");
        System.out.println("Billigste: " + billigste);
        System.out.println("Expected: 15");

        // Test 2: Teuerster Gegenstand
        System.out.println("\n--- Test 2: Teuerster Gegenstand ---");
        List<Tastatur> tastaturen2 = createTastaturList(new int[]{15, 20, 10, 35});
        List<USB> usbs2 = createUSBList(new int[]{20, 15, 40, 15});
        int teuerster = shop.teuersterGegenstand(tastaturen2, usbs2);
        System.out.println("Tastaturen: [15, 20, 10, 35]");
        System.out.println("USBs: [20, 15, 40, 15]");
        System.out.println("Teuerster: " + teuerster);
        System.out.println("Expected: 40");

        // Test 3: Teuerster USB im Budget
        System.out.println("\n--- Test 3: Teuerster USB im Budget ---");
        List<USB> usbs3 = createUSBList(new int[]{15, 45, 20});
        int budget3 = 30;
        int teuersterUSB = shop.teuersterUsbImBudget(usbs3, budget3);
        System.out.println("USB Preise: [15, 45, 20]");
        System.out.println("Budget: " + budget3);
        System.out.println("Teuerster USB im Budget: " + teuersterUSB);
        System.out.println("Expected: 20");

        // Test 4: Maximaler Geldbetrag (various scenarios)
        System.out.println("\n--- Test 4: Maximaler Geldbetrag ---");

        // Scenario 1: b=60, tastaturen=[40,50,60], usb=[8,12]
        List<Tastatur> tastaturen4a = createTastaturList(new int[]{40, 50, 60});
        List<USB> usbs4a = createUSBList(new int[]{8, 12});
        int max4a = shop.maximalerGeldbetrag(tastaturen4a, usbs4a, 60);
        System.out.println("Budget: 60, Tastaturen: [40,50,60], USB: [8,12]");
        System.out.println("Result: " + max4a);
        System.out.println("Expected: 58 (50+8)");

        // Scenario 2: b=60, tastaturen=[60], usb=[8,12]
        List<Tastatur> tastaturen4b = createTastaturList(new int[]{60});
        List<USB> usbs4b = createUSBList(new int[]{8, 12});
        int max4b = shop.maximalerGeldbetrag(tastaturen4b, usbs4b, 60);
        System.out.println("\nBudget: 60, Tastaturen: [60], USB: [8,12]");
        System.out.println("Result: " + max4b);
        System.out.println("Expected: -1 (can't afford both)");

        // Scenario 3: b=60, tastaturen=[40,60], usb=[8,12]
        List<Tastatur> tastaturen4c = createTastaturList(new int[]{40, 60});
        List<USB> usbs4c = createUSBList(new int[]{8, 12});
        int max4c = shop.maximalerGeldbetrag(tastaturen4c, usbs4c, 60);
        System.out.println("\nBudget: 60, Tastaturen: [40,60], USB: [8,12]");
        System.out.println("Result: " + max4c);
        System.out.println("Expected: 52 (40+12)");
    }

    // ============= HELPER METHODS =============

    private static ArrayList<Note> createNoteList(int[] werte) {
        ArrayList<Note> liste = new ArrayList<>();
        for (int wert : werte) {
            // Using reflection to create Note objects with wert field
            try {
                Note note = Note.class.getDeclaredConstructor().newInstance();
                java.lang.reflect.Field field = Note.class.getDeclaredField("wert");
                field.setAccessible(true);
                field.set(note, wert);
                liste.add(note);
            } catch (Exception e) {
                System.err.println("Error creating Note: " + e.getMessage());
            }
        }
        return liste;
    }

    private static List<Tastatur> createTastaturList(int[] preise) {
        List<Tastatur> liste = new ArrayList<>();
        for (int preis : preise) {
            liste.add(new Tastatur(preis));
        }
        return liste;
    }

    private static List<USB> createUSBList(int[] preise) {
        List<USB> liste = new ArrayList<>();
        for (int preis : preise) {
            liste.add(new USB(preis));
        }
        return liste;
    }

    private static String listToString(List<Integer> list) {
        return list.toString();
    }
}