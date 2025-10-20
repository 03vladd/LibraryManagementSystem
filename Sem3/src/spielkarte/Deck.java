package spielkarte;

import java.util.ArrayList;
import java.util.Iterator;

public class Deck implements Iterable<Spielkarte> {
    private ArrayList<Spielkarte> karten;

    public Deck() {
        karten = new ArrayList<Spielkarte>();
        erstelleDeck();
    }

    private void erstelleDeck() {
        String[] farben = {"Pik", "Kreuz", "Herz", "Karo"};
        String[] werte = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass"};

        for (String s : farben) {
            for (String w : werte) {
                karten.add(new Spielkarte(s, w));
            }
        }
    }

    public void addKarte(Spielkarte karte) {
        karten.add(karte);
    }

    @Override
    public Iterator<Spielkarte> iterator() {
        return karten.iterator();
    }
}
