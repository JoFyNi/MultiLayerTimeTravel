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

    // Methode zur Interaktion des Spielers mit einem Feld
    public void interagiereMitFeld(Spieler spieler, int zeile, int spalte) {
        FeldTypen feldtyp = felder[zeile][spalte];

        switch (feldtyp) {
            case LAND:
                // Logik für die Interaktion mit einem Landfeld
                landInteraktion(spieler, zeile, spalte);
                break;
            case WASSER:
                // Logik für die Interaktion mit einem Wasserfeld
                wasserInteraktion(spieler, zeile, spalte);
                break;
            case GEBIRGE:
                // Logik für die Interaktion mit einem Gebirgsfeld
                gebirgeInteraktion(spieler, zeile, spalte);
                break;
            case STADT:
                // Logik für die Interaktion mit einem Stadtfeld
                stadtInteraktion(spieler, zeile, spalte);
                break;
            case RESSOURCE:
                // Logik für die Interaktion mit einem Ressourcenfeld
                ressourcenInteraktion(spieler, zeile, spalte);
                break;
            default:
                // Unerwarteter Feldtyp
                break;
        }
    }

    // Interaktionslogik für den Feldtyp LAND
    private void landInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Landfeld
        // Zum Beispiel: Ressourcen sammeln, Einheiten platzieren, etc.
    }

    // Interaktionslogik für den Feldtyp WASSER
    private void wasserInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Wasserfeld
        // Zum Beispiel: Über das Wasser segeln, spezielle Ressourcen sammeln, etc.
    }

    // Interaktionslogik für den Feldtyp GEBIRGE
    private void gebirgeInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Gebirgsfeld
        // Zum Beispiel: Gebirge erklimmen, spezielle Ressourcen finden, etc.
    }

    // Interaktionslogik für den Feldtyp STADT
    private void stadtInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Stadtfeld
        // Zum Beispiel: Einheiten rekrutieren, Handel betreiben, Quests erhalten, etc.
    }

    // Interaktionslogik für den Feldtyp RESSOURCE
    private void ressourcenInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Ressourcenfeld
        // Zum Beispiel: Ressourcen sammeln, abbauen, etc.
    }
}
