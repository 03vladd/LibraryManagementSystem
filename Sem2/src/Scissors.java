public class Scissors extends Utensils implements CanCut{
    public Scissors(double weight) {
        super(weight);

    }

    @Override
    public boolean cut() {
        return true;
    }
}
