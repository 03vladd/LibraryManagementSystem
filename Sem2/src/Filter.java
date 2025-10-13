import java.util.List;

public class Filter {

    public void filterbyweigth(List<Utensils> list)
    {
        for (Utensils utensil : list) {
            if (utensil.getWeight() > 1 ){
                System.out.println(utensil.getWeight());
            }
        }
    }

    public void cancut(List<CanCut> list) {
        for (CanCut utensil : list) {
            utensil.cut();  // Just call it
        }
    }
}
