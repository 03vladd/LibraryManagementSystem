package spielkarte;

public enum Farbe {
    PIK,
    KREUZ,
    HERZ,
    KARO;

    @Override
    public String toString() {
        // Makes it display nicely
        return switch(this) {
            case PIK -> "Pik";
            case KREUZ -> "Kreuz";
            case HERZ -> "Herz";
            case KARO -> "Karo";
        };
    }
}

