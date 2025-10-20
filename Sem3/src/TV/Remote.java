package TV;

public class Remote {

    private TV tv;
    public Remote(TV tv) {
        this.tv = tv;
    }

    public Kanal channelUp() {
        return tv.next();
    }

    public Kanal channelDown() {
        return tv.previous();
    }
}
