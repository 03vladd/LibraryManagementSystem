public class Saw extends Utensils implements CanCut {
    private double weight;

    Saw(double weight) {
        super(weight);
    }

    @Override
    public boolean  cut() {
        return true;
    }
}
