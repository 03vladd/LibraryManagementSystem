package spielkarte;

public class SpielkarteEnum {
    private Farbe farbe; // Pik, Kreuz, Herz, Karo
    private Wert wert;

    public SpielkarteEnum(Farbe farbe, Wert wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public Wert getWert() {
        return wert;
    }

    public String toString() {
        return wert.toString() + " von " + farbe.toString();
    }
}
