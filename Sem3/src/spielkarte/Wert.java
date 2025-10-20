package spielkarte;

public enum Wert {
    ZWEI,
    DREI,
    VIER,
    FUENF,
    SECHS,
    SIEBEN,
    ACHT,
    NEUN,
    ZEHN,
    BUBE,
    DAME,
    KOENIG,
    ASS;

    public String toString() {
        return switch (this) {
            case ZWEI -> "2";
            case DREI -> "3";
            case VIER -> "4";
            case FUENF -> "5";
            case SECHS -> "6";
            case SIEBEN -> "7";
            case ACHT -> "8";
            case NEUN -> "9";
            case ZEHN -> "10";
            case BUBE -> "Bube";
            case DAME -> "Dame";
            case KOENIG -> "König";
            case ASS -> "Ass";
        };
    }
}
