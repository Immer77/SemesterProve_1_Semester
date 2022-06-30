package model;

public class Antal {
    private int antal;
    public Funktion funktion;

    Antal(int antal, Funktion funktion){
        this.antal = antal;
        this.funktion = funktion;
    }

    public Funktion getFunktion() {
        return funktion;
    }

    public int getAntal() {
        return antal;
    }

    @Override
    public String toString(){
        return antal +"";
    }
}
