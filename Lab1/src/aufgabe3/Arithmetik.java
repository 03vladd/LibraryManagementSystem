package aufgabe3;

import java.util.*;

public class Arithmetik {

    //1. berechnet die summe von zwei zahlen die in der form eines arrays dargestellt sind

    public List<Integer> Summe(List<Integer> zahl1, List<Integer> zahl2) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;

        int maxLen = Math.max(zahl1.size(), zahl2.size());

        for(int i = 0; i<maxLen; i++){
            result.add(0);
        }

        for (int i = maxLen - 1; i>= 0; i--){
            int sum = zahl1.get(i) + zahl2.get(i) + carry;
            result.set(i, sum % 10);
            carry = sum / 10;
        }

        if(carry > 0){
            result.add(0, carry);
        }
        if(result.get(0)== 0){
            result.remove(0);
        }
        return result;
    }

    //2. berechnet die differenz von zwei zahlen die in der form eines arrays dargestellt sind
    public List<Integer> Differenz(List<Integer> zahl1, List<Integer> zahl2) {
        List<Integer> result = new ArrayList<>();
        int borrow = 0;

        int maxLen = Math.max(zahl1.size(), zahl2.size());

        for(int i = 0; i<maxLen; i++){
            result.add(0);
        }

        for (int i = maxLen - 1; i>= 0; i--){
            int diff = zahl1.get(i) - zahl2.get(i) - borrow;
            if(diff < 0){
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.set(i, diff);
        }

        while(result.size() > 1 && result.get(0) == 0){
            result.remove(0);
        }

        return result;
    }

    //3. berechnet das produkt von eine Zahl die als array dargestellt ist mit einer einzelnen Ziffer
    public List<Integer> ProduktMitZiffer(List<Integer> zahl, int ziffer) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;

        for (int i = zahl.size() - 1; i >= 0; i--) {
            int prod = zahl.get(i) * ziffer + carry;
            result.add(0, prod % 10);
            carry = prod / 10;
        }

        while (carry > 0) {
            result.add(0, carry % 10);
            carry /= 10;
        }

        return result;
    }

    //4. berechnet die division von einer Zahl die als array dargestellt ist durch eine einzelne Ziffer
    public List<Integer> DivisionDurchZiffer(List<Integer> zahl, int ziffer) {
        List<Integer> result = new ArrayList<>();
        int remainder = 0;
        for (int i = 0; i < zahl.size(); i++) {
            int current = remainder * 10 + zahl.get(i);
            result.add(current / ziffer);
            remainder = current % ziffer;
        }
        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }
        return result;
    }



}
