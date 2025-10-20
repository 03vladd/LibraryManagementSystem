package songs;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist implements Iterable<Song>{

    private ArrayList<Song> songs;

    public Playlist() {
        songs = new ArrayList<Song>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(songs);
    }

    public static class PlaylistIterator implements Iterator<Song> {
        private int currentIndex = 0;
        private ArrayList<Song> songs;

        public PlaylistIterator(ArrayList<Song> songs) {
            this.songs = songs;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < songs.size();
        }

        @Override
        public Song next() {
            return songs.get(currentIndex++);
        }
    }
}
