package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Medarbejder {
    private String navn;
    private int antalTimerPrDag;
    private LocalTime typiskMødetid;
    private ArrayList<Vagt> vagter = new ArrayList<>();
    public ArrayList<Funktion> funktioner = new ArrayList<>();

    public Medarbejder(String navn, int antalTimerPrDag, LocalTime typiskMødetid){
        this.navn = navn;
        this.antalTimerPrDag = antalTimerPrDag;
        this.typiskMødetid = typiskMødetid;
    }

    public ArrayList<Funktion> getFunktioner() {
        return new ArrayList<>(funktioner);
    }

    /**
     * Tilføjer funktion til medarbejderen
     * @param funktion
     */
    public void addFunktion(Funktion funktion){
        if(!funktioner.contains(funktion)){
            funktioner.add(funktion);
        }
    }

    /**
     * Fjerne funktionen fra medarbejder
     * @param funktion
     */
    public void removeFunktion(Funktion funktion){
        if(funktioner.contains(funktion)){
            funktioner.remove(funktion);
        }
    }

    public String getNavn() {
        return navn;
    }

    public int getAntalTimerPrDag() {
        return antalTimerPrDag;
    }

    public LocalTime getTypiskMødetid() {
        return typiskMødetid;
    }

    public ArrayList<Vagt> getVagter() {
        return new ArrayList<>(vagter);
    }

    /**
     * Tilføjer vagten til en medarbejder og medarbejdere til vagten
     * hvis den ikke allerede er blevet tilføjet
     * @param vagt
     */

//    public void addVagt(Vagt vagt) {
//        if(!vagter.contains(vagt)){
//            vagter.add(vagt);
//            vagt.addMedarbejder(this);
//        }
//    }

    public void addVagt(Vagt vagt){
        if(!vagter.contains(vagt)){
            for(Vagt v : vagter){
                if(overlap(v,vagt)){
                    throw new RuntimeException(navn + " " +v.getNavn());
                }
            }
            vagter.add(vagt);
            vagt.addMedarbejder(this);
        }
    }

    public boolean overlap(Vagt v1, Vagt v2){
        return v1.getTidFra().isBefore(v2.getTidTil()) && v1.getTidTil().isAfter(v2.getTidFra());
    }

    /**
     * fjerner vagten fra en medarbejder og fjerner medarbejderen fra en vagt
     * hvis den ikke allerede er blevet fjernet.
     * @param vagt
     */
    public void removeVagt(Vagt vagt) {
        if(vagter.contains(vagt)){
            vagter.remove(vagt);
            vagt.removeMedarbejder(this);
        }
    }

    @Override
    public String toString(){
        return navn + " ";
    }
}
