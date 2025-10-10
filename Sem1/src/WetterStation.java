import java.util.ArrayList;
import java.util.List;

public class WetterStation {
    public List<Integer> tage;
    public List<Double> temp;

    //constructor
    public WetterStation() {
        tage = new ArrayList<>();
        tage.add(1);
        tage.add(2);
        tage.add(3);

        temp = new ArrayList<>();
        temp.add(21.0);
        temp.add(19.5);
        temp.add(23.0);
    }

    public double medianTemp(List<Integer> tage, List<Double> temp) {
        int periode = tage.size();
        double median = 0.0;
        double sum = 0.0;

        for (int j = 0; j < periode; j++) {
                sum += temp.get(j);
            }

        median = sum / periode;

        return median;
    }

    public String MinMaxTemp(List<Double> temp){
        double min = 999999999.0;
        double max = -999999999.0;

        for (double t : temp) {
            if (t < min) {
                min = t;
            }
            if (t > max) {
                max = t;
            }
        }
        return ("Min: " + min + ", Max: " + max);
    }

    public String maxTempSchwung(List<Integer> tage ,List<Double> temp){
        double maxSchwung = -999999999.0;
        int tag1 = -1;
        int tag2 = -1;

        for (int i = 0; i < tage.size() - 1; i++) {
            double schwung = Math.abs(temp.get(i + 1) - temp.get(i));
            if (schwung > maxSchwung) {
                maxSchwung = schwung;
                tag1 = tage.get(i);
                tag2 = tage.get(i + 1);
            }
        }
        return ("Max Schwung: " + maxSchwung + " zwischen Tag " + tag1 + " und Tag " + tag2);
    }

    public String listtotable(List<Integer> tage, List<Double> temp){
        String tabelle = "";
        for (int i = 0; i < tage.size(); i++) {
            tabelle += "Tag " + tage.get(i) + ": " + temp.get(i) + "°C\n";
        }
        return tabelle;
    }
}
