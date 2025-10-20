package aufgabe1;

import java.util.List;
import java.util.ArrayList;

public class Note {
    private int wert;

    // <40 ist nicht ausreichend

    //1. returns nicht ausreichende Noten aus der Liste

    public List<Integer> nichtAusreichend(ArrayList<Note> notenListe) {

        List<Integer> result = new ArrayList<>();

        for (Note note : notenListe) {
            if (note.wert < 40) {
                result.add(note.wert);
            }
        }
        return result;
    }

    //2. returns durchschnitt der noten aus der liste

    public double durchschnitt(ArrayList<Note> notenListe) {
        int sum = 0;
        for (Note note : notenListe) {
            sum += note.wert;
        }
        return (double) sum / notenListe.size();
    }

    //3. abgerundete noten (differenz zu 5 ist kleiner als 3)

    public List<Integer> abgerundeteNoten(List<Note> notenListe) {
        List<Integer> result = new ArrayList<>();

        for (Note note : notenListe) {
            if(note.wert < 38) {
                result.add(note.wert);
            }
            else {
                int differenz = 5 - (note.wert % 5);
                if (differenz < 3) {
                    result.add(note.wert + differenz);
                } else {
                    result.add(note.wert);
                }
            }
        }
        return result;
    }

    //4. maximale abgerundete note

    public int maximaleAbgerundeteNote(List<Note> notenListe) {
        List<Integer> abgerundeteNoten = abgerundeteNoten(notenListe);
        int max = Integer.MIN_VALUE;

        for (int note : abgerundeteNoten) {
            if (note > max) {
                max = note;
            }
        }
        return max;
    }


}
