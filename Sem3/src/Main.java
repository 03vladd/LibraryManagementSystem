import TV.TV;
import TV.Kanal;
import TV.Remote;
import songs.Playlist;
import songs.Song;
import spielkarte.*;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Song 1", "Artist 1", 210));
        playlist.addSong(new Song("Song 2", "Artist 2", 180));

        for(Song song : playlist) {
            System.out.println(song);
        }

        Spielkarte karte = new Spielkarte("Herz", "Zwei");

        Deck deck = new Deck();

        deck.addKarte(karte);

        for (Spielkarte c : deck) {
            System.out.println(c);
        }

        SpielkarteEnum karteEnum = new SpielkarteEnum(Farbe.KARO, Wert.ZWEI);
        DeckEnum deckEnum = new DeckEnum();

        for (SpielkarteEnum c : deckEnum) {
            System.out.println(c);
        }

        TV tv = new TV();
        tv.add(new Kanal("Kanal 1"));
        tv.add(new Kanal("Kanal 2"));
        tv.add(new Kanal("Kanal 3"));
        tv.add(new Kanal("Kanal 4"));

        Remote remote = new Remote(tv);

        remote.channelDown();
        remote.channelUp();
        remote.channelDown();
        remote.channelDown();


    }


}