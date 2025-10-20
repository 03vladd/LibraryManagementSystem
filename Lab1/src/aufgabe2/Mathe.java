package aufgabe2;

import java.util.*;
import java.util.ArrayList;

public class Mathe {

    //1. maximale zahl in der liste

    public int maximaleZahl(List<Integer> zahlenListe) {
        int max = Integer.MIN_VALUE;
        for (int zahl : zahlenListe) {
            if (zahl > max) {
                max = zahl;
            }
        }
        return max;
    }

    //2. minimale zahl in der liste
    public int minimaleZahl(List<Integer> zahlenListe) {
        int min = Integer.MAX_VALUE;
        for (int zahl : zahlenListe) {
            if (zahl < min) {
                min = zahl;
            }
        }
        return min;
    }

    //3. maximale summe von n-1 Zahlen
    public int maxSumNmin1(List<Integer> zahlenListe) {
        zahlenListe.sort(Comparator.naturalOrder());
        int sum = 0;
        for (int i =1; i< zahlenListe.size(); i++) {
            sum += zahlenListe.get(i);
        }
        return sum;
    }

    //4. minimale summe von n-1 Zahlen
    public int minSumNmin1(List<Integer> zahlenListe) {
        zahlenListe.sort(Comparator.naturalOrder());
        int sum = 0;
        for (int i =0; i< zahlenListe.size() -1; i++) {
            sum += zahlenListe.get(i);
        }
        return sum;
    }
}
