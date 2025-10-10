public static void main() {

    MatheAufgabe matheAufgabe = new MatheAufgabe();
    StringManipulation stringManipulation = new StringManipulation();
    WetterStation station = new WetterStation();
    Numbers numbers = new Numbers();

    System.out.println("Summe: " + matheAufgabe.sum(5, 3));
    System.out.println("Subtraktion: " + matheAufgabe.sub(5, 3));
    System.out.println("Multiplikation: " + matheAufgabe.mul(5, 3));

    System.out.println("Umkehrter String: " + stringManipulation.umkehren("Hallo Welt"));
    System.out.println("Wortanzahl: " + stringManipulation.wordCount("Hallo Welt"));

    System.out.println("Perfekte Zahl (6): " + matheAufgabe.perfectNumber(6));
    System.out.println("Perfekte Zahl (10): " + matheAufgabe.perfectNumber(10));

    System.out.println(station.listtotable(station.tage, station.temp));
    System.out.println(station.maxTempSchwung(station.tage, station.temp));
    System.out.println(station.MinMaxTemp(station.temp));
    System.out.println("Median Temperatur: " + station.medianTemp(station.tage, station.temp));

    System.out.println(numbers.romanToArabic("XVI"));
}
