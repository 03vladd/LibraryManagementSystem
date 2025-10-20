package songs;

public class Song {

    //Getters and Setters
    private String title;
    private String artist;
    private int duration; // duration in seconds

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Duration: " + duration + " seconds";
    }
}
