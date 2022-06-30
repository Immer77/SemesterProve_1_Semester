package storage;

import model.Funktion;
import model.Medarbejder;
import model.Vagt;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Funktion> funktioner = new ArrayList<>();
    public static ArrayList<Vagt> vagter = new ArrayList<>();
    public static ArrayList<Medarbejder> medarbejdere = new ArrayList<>();

    public static ArrayList<Funktion> getFunktioner() {
        return new ArrayList<>(funktioner);
    }

    public static ArrayList<Vagt> getVagter() {
        return new ArrayList<>(vagter);
    }

    public static ArrayList<Medarbejder> getMedarbejdere() {
        return new ArrayList<>(medarbejdere);
    }

    public static void addFunktion(Funktion funktion){
        funktioner.add(funktion);
    }

    public static void addVagt(Vagt vagt){
        vagter.add(vagt);
    }

    public static void addMedarbejder(Medarbejder medarbejder){
        medarbejdere.add(medarbejder);
    }
}
