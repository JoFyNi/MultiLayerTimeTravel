public class Spielbrett {
    int zeilen;
    int spalten;
    private FeldTypen[][] felder;

    public Spielbrett(int zeilen, int spalten) {
        felder = new FeldTypen[zeilen][spalten];
        this.zeilen = zeilen;
        this.spalten = spalten;
    }

    // Methode zum Setzen des Feldtyps an einer bestimmten Position
    public void setFeldtyp(int zeile, int spalte, FeldTypen feldtyp) {
        felder[zeile][spalte] = feldtyp;
    }

    // Methode zum Abrufen des Feldtyps an einer bestimmten Position
    public FeldTypen getFeldtyp(int zeile, int spalte) {
        return felder[zeile][spalte];
    }


    public int getZeile() {
        return zeilen;
    }
    public int getSpalte() {
        return spalten;
    }

    public void Kollision() {

    }

    public void Interaktion() {

    }
}
