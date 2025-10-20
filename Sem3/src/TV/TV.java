package TV;


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class TV implements ListIterator<Kanal> {

    private List<Kanal> kanalList;
    private int index = 0;

    public TV() {
        kanalList = new ArrayList<Kanal>();
    }

    @Override
    public boolean hasNext() {
        return index < kanalList.size();
    }

    @Override
    public Kanal next() {
        return kanalList.get(index++);
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public Kanal previous() {
        return kanalList.get(--index);
    }

    @Override
    public int nextIndex() {
        return index++;
    }

    @Override
    public int previousIndex() {
        return index--;
    }

    @Override
    public void remove() {
        kanalList.remove(index);
    }

    @Override
    public void add(Kanal kanal) {
        kanalList.add(index, kanal);
    }

    @Override
    public void set(Kanal kanal) {
        kanalList.set(index, kanal);
    }
}
