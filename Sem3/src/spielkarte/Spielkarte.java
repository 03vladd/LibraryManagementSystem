package spielkarte;

public class Spielkarte {
    private String farbe; // Pik, Kreuz, Herz, Karo
    private String wert;

    public Spielkarte(String farbe, String wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public String getFarbe() {
        return farbe;
    }

    public String getWert() {
        return wert;
    }

    public String toString() {
        return wert + " von " + farbe;
    }
}
