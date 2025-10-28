package aufgabe4;

import java.util.*;

public class Shop {
    private Tastatur tastatur;
    private USB usb;

    //1. billigste tastatur in der liste
    public int billigsteTastatur(List<Tastatur> tastaturen) {
        int min = Integer.MAX_VALUE;
        for(Tastatur t : tastaturen){
            if(t.preis < min){
                min = t.preis;
            }
        }
        return min;
    }

    //2. teuersten gegenstand in der liste
    public int teuersterGegenstand(List<Tastatur> tastaturen, List<USB> usbs) {
        int max = Integer.MIN_VALUE;
        for(Tastatur t : tastaturen){
            if(t.preis > max){
                max = t.preis;
            }
        }
        for(USB u : usbs){
            if(u.preis > max){
                max = u.preis;
            }
        }
        return max;
    }

    //3. teursten Usb der in einem Budget passt
    public int teuersterUsbImBudget(List<USB> usbs, int budget) {
        int max = Integer.MIN_VALUE;
        for (USB u : usbs){
            if(u.preis > max && u.preis < budget){
                max = u.preis;
            }
        }
        return max;
    }

    //4. maximalen Geldbetrag ausgeben der für eine Tastatur und einen USB Stick ausgegeben werden kann
    public int maximalerGeldbetrag(List<Tastatur> tastaturen, List<USB> usbs, int budget) {
        int max = -1;
        for (Tastatur t : tastaturen) {
            for (USB u : usbs) {
                int sum = t.preis + u.preis;
                if (sum > max && sum <= budget) {
                    max = sum;
                }
            }
        }
        return max;
    }

}
