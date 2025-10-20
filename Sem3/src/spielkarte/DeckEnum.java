package spielkarte;

import java.util.ArrayList;
import java.util.Iterator;

public class DeckEnum implements Iterable<SpielkarteEnum>{

    private ArrayList<SpielkarteEnum> spielkarteEnum;

    public DeckEnum() {
        spielkarteEnum = new ArrayList<SpielkarteEnum>();
        erstelleDeck();
    }

    public void erstelleDeck() {
        for (Farbe farbe : Farbe.values()) {
            for (Wert wert : Wert.values()) {
                spielkarteEnum.add(new SpielkarteEnum(farbe, wert));
            }
        }
    }

    public void addFarbe(Farbe farbe, Wert wert) {
        spielkarteEnum.add(new SpielkarteEnum(farbe, wert));
    }

    public void addWert(Wert wert) {
        for (Farbe farbe : Farbe.values()) {
            spielkarteEnum.add(new SpielkarteEnum(farbe, wert));
        }
    }

    public void addKarte(SpielkarteEnum karte) {
        spielkarteEnum.add(karte);
    }

    @Override
    public Iterator<SpielkarteEnum> iterator() {
        return spielkarteEnum.iterator();
    }
}
