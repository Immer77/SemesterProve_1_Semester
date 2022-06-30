package controller;

import model.Antal;
import model.Funktion;
import model.Medarbejder;
import model.Vagt;
import storage.Storage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


public class Controller {

    public static Funktion createFunktion(String navn){
        Funktion funktion = new Funktion(navn);
        Storage.addFunktion(funktion);
        return funktion;
    }

    public static Medarbejder createMedarbejder(String navn, int antalTimerPrDag, LocalTime typiskMødetid){
        Medarbejder medarbejder = new Medarbejder(navn, antalTimerPrDag, typiskMødetid);
        Storage.addMedarbejder(medarbejder);
        return medarbejder;
    }

    public static Vagt createVagt(String navn, LocalDateTime tidFra, LocalDateTime tidTil){
        Vagt vagt = new Vagt(navn, tidFra, tidTil);
        Storage.addVagt(vagt);
        return vagt;
    }

    public static ArrayList<Vagt> getVagter(){
        return Storage.getVagter();
    }
    public static ArrayList<Funktion> getFunktioner(){
        return Storage.getFunktioner();
    }

    public static ArrayList<Medarbejder> getMedarbejdere(){
        return Storage.getMedarbejdere();
    }

    public static void addMedarbejderToVagt(Vagt vagt, Medarbejder medarbejder){
        medarbejder.addVagt(vagt);
    }

    public static void addFunktionToMedarbejder(Medarbejder medarbejder, Funktion funktion){
        if(!medarbejder.getFunktioner().contains(funktion)){
            medarbejder.addFunktion(funktion);
        }
    }


    public static void udskrivVagtplan(Vagt vagt, String filnavn){
        try (PrintWriter pw = new PrintWriter(filnavn)){
            pw.println("--------------------------------------");
            pw.println(vagt);
            pw.println("--------------------------------------\n");
            pw.println("Funktioner:");
            for (Antal a : vagt.getAntal()){
                pw.println(a.getFunktion() + " (" + a.getAntal() + ", " + vagt.antalMedarbejdereMedFunktion(a.getFunktion()) + " medarbejdere)");
            }
            pw.print("Medarbejdere: ");
            StringBuilder sb = new StringBuilder();
            for(Medarbejder m : vagt.getMedarbejdere()){
                sb.append(m.getNavn() + " ");
            }
            pw.println(sb+ "\n");
            pw.println("Status:" + vagt.status());
        }catch (FileNotFoundException fe){
            System.out.println("File doesnt exist");
        }
    }

    private static void initStorage(){
        Funktion f1 = createFunktion("Filetering");
        Funktion f2 = createFunktion("Grøntsager");
        Funktion f3 = createFunktion("Sovs og tilbehør");
        Funktion f4 = createFunktion("Buffetopfyldning");

        Medarbejder m1 = createMedarbejder("Peter", 6,LocalTime.of(8,0));
        Medarbejder m2 = createMedarbejder("Anne", 8,LocalTime.of(8,0));
        Medarbejder m3 = createMedarbejder("Marie", 6,LocalTime.of(10,0));
        Medarbejder m4 = createMedarbejder("Torben", 8,LocalTime.of(7,0));

        Vagt vagt = createVagt("Røgede ål til medarbejderne", LocalDateTime.of(2022,6,24,8,0), LocalDateTime.of(2022,6,24,12,30));
        Vagt vagt1 = createVagt("Sommerfest",LocalDateTime.of(2022,6,24,8,0), LocalDateTime.of(2022,6,24,12,30));
        vagt.createAntal(2, f1);
        vagt.createAntal(1,f2);
        vagt.createAntal(1,f3);
        vagt.createAntal(1,f4);

        m1.addVagt(vagt);
        m2.addVagt(vagt);
        m3.addVagt(vagt);

        m1.addFunktion(f2);
        m1.addFunktion(f3);
        m1.addFunktion(f4);

        m2.addFunktion(f2);
        m2.addFunktion(f3);
        m2.addFunktion(f4);

        m3.addFunktion(f1);
        m3.addFunktion(f2);
        m3.addFunktion(f4);

        m4.addFunktion(f1);
        m4.addFunktion(f3);
    }


    public static void init(){
        initStorage();
    }
}
