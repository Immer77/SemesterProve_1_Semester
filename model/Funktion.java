package model;

public class Funktion {
    private String navn;

    public Funktion(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString(){
        return navn;
    }
}
