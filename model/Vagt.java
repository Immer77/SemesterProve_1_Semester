package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Vagt {
    private String navn;
    private LocalDateTime tidFra;
    private LocalDateTime tidTil;
    public ArrayList<Antal> antaller = new ArrayList<>();
    public ArrayList<Medarbejder> medarbejdere = new ArrayList<>();

    public Vagt(String navn, LocalDateTime tidFra, LocalDateTime tidTil) {
        this.navn = navn;
        this.tidFra = tidFra;
        this.tidTil = tidTil;
    }

    public ArrayList<Antal> getAntal() {
        return new ArrayList<>(antaller);
    }

    public ArrayList<Medarbejder> getMedarbejdere() {
        return new ArrayList<>(medarbejdere);
    }

    /**
     * Tilføjer medarbejderen til en vagt og vagten til en medarbejdere
     * hvis de ikke allerede er tilføjet
     * @param medarbejder
     */

    public void addMedarbejder(Medarbejder medarbejder){
        if(!medarbejdere.contains(medarbejder)){
            medarbejdere.add(medarbejder);
            medarbejder.addVagt(this);
        }
    }

    /**
     * Fjerne medarbejderen fra en vagt og vagten fra en medarbejere
     * hvis der ikke allerede er blevet fjernet
     * @param medarbejder
     */

    public void removeMedarbejder(Medarbejder medarbejder){
        if(medarbejdere.contains(medarbejder)){
            medarbejdere.remove(medarbejder);
            medarbejder.removeVagt(this);
        }
    }

    public Antal createAntal(int antal, Funktion funktion){
        Antal a = new Antal(antal, funktion);
        antaller.add(a);
        return a;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDateTime getTidFra() {
        return tidFra;
    }

    public LocalDateTime getTidTil() {
        return tidTil;
    }

    public Medarbejder findMedarbejder(LocalTime tidspunkt, int antalTimer){
        for(Medarbejder m : medarbejdere){
            if(tidspunkt.equals(m.getTypiskMødetid()) && antalTimer <= m.getAntalTimerPrDag()){
                return m;
            }
        }
        return null;
    }

    public int beregnTimeForbrug(){
        double samletTimeForbrug = 0;
        for(Medarbejder m : medarbejdere){
            if(m.getAntalTimerPrDag() > 0){
                samletTimeForbrug += m.getAntalTimerPrDag();
            }
        }
        return (int) Math.ceil(samletTimeForbrug);
    }

    public int antalMedarbejdereMedFunktion(Funktion funktion){
        int antalSpecifikFunktion = 0;
        for(Medarbejder m : medarbejdere){
            for(Funktion f : m.getFunktioner()){
                if(f == funktion){
                    antalSpecifikFunktion++;
                }
            }
        }
        return antalSpecifikFunktion;
    }

    public Medarbejder[] skalAdviseresOmMødeTid(){
        Medarbejder[] medArbejdereMedSenereMødetid = new Medarbejder[medarbejdere.size()];
        for (int i = 0; i < medArbejdereMedSenereMødetid.length; i++) {
            if(medarbejdere.get(i).getTypiskMødetid().isAfter(getTidFra().toLocalTime())){
                medArbejdereMedSenereMødetid[i] = medarbejdere.get(i);
            }
        }
        return medArbejdereMedSenereMødetid;
    }

    public String status(){
        int i = 0;
        while (i < antaller.size()){
            Funktion f = antaller.get(i).getFunktion();
            if(antalMedarbejdereMedFunktion(f) < 0){
                return "Manglende ressourser";
            }
            i++;
        }
        return "Ressourcer tildelt";
    }



    @Override
    public String toString(){
        return tidFra + " " + "(" + navn + ")";
    }
}
