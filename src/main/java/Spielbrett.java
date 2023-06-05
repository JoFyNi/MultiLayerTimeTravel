import java.util.Random;

public class Spielbrett extends MainFrame {
    static int zeilen;
    static int spalten;
    private static FeldTypen[][] felder;

    /**
     * Erstellt ein Spielbrett mit den angegebenen Zeilen und Spalten.
     * @param zeilen Anzahl der Zeilen des Spielbretts
     * @param spalten Anzahl der Spalten des Spielbretts
     */
    public Spielbrett(int zeilen, int spalten) {
        this.zeilen = zeilen;
        this.spalten = spalten;
        felder = new FeldTypen[zeilen][spalten];
    }

    public int getBewegungskosten(int aktuelleX, int aktuelleY, int neueX, int neueY) {
        FeldTypen aktuellesFeldtyp = getFeldtyp(aktuelleX, aktuelleY);
        FeldTypen neuesFeldtyp = getFeldtyp(neueX, neueY);

        int bewegungskosten = Math.abs(neueX - aktuelleX) + Math.abs(neueY - aktuelleY);

        if (aktuellesFeldtyp == FeldTypen.GEBIRGE || neuesFeldtyp == FeldTypen.GEBIRGE) {
            bewegungskosten += 1; // Erhöhe die Bewegungskosten um 1 für jedes Gebirgsfeld
        }

        return bewegungskosten;
    }

    /**
     * Setzt den Feldtyp an der angegebenen Position im Spielbrett.
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     * @param feldtyp Der zu setzende Feldtyp
     */
    public void setFeldtyp(int zeile, int spalte, FeldTypen feldtyp) {
        felder[zeile][spalte] = feldtyp;
    }

    /**
     * Ruft den Feldtyp an der angegebenen Position im Spielbrett ab.
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     * @return Der Feldtyp an der angegebenen Position
     */
    public FeldTypen getFeldtyp(int zeile, int spalte) {
        try {
            return felder[zeile][spalte];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Außerhalb des Spielfeldes");
            return null; // Oder einen geeigneten Standardwert für den Feldtyp zurückgeben
        }
    }


    /**
     * Generiert das Spielbrett mit zufälligen Feldtypen.
     * @return Das generierte Spielbrett als zweidimensionales Feld
     */
    public FeldTypen[][] generiereSpielbrett() {
        Random random = new Random();

        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                FeldTypen feldtyp = FeldTypen.values()[random.nextInt(FeldTypen.values().length)];
                felder[i][j] = feldtyp;
            }
        }
        return new FeldTypen[getZeile()][getSpalte()];
    }
    /**
     * Gibt die Anzahl der Zeilen des Spielbretts zurück.
     * @return Die Anzahl der Zeilen des Spielbretts
     */
    public static int getZeile() {
        return zeilen;
    }

    /**
     * Gibt die Anzahl der Spalten des Spielbretts zurück.
     * @return Die Anzahl der Spalten des Spielbretts
     */
    public static int getSpalte() {
        return spalten;
    }

    /**
     * Führt die Interaktionslogik für den Feldtyp LAND aus.
     * @param spieler Der Spieler, der mit dem Landfeld interagiert
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     */
    private void landInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Landfeld
        // zum Beispiel: Ressourcen sammeln, Einheiten platzieren, etc.
    }

    /**
     * Führt die Interaktionslogik für den Feldtyp WASSER aus.
     * @param spieler Der Spieler, der mit dem Wasserfeld interagiert
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     */
    private void wasserInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Wasserfeld
        // Zum Beispiel: Über das Wasser segeln, spezielle Ressourcen sammeln, etc.
    }

    /**
     * Führt die Interaktionslogik für den Feldtyp GEBIRGE aus.
     * @param spieler Der Spieler, der mit dem Gebirgsfeld interagiert
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     */
    private void gebirgeInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Gebirgsfeld
        // Zum Beispiel: Gebirge erklimmen, spezielle Ressourcen finden, etc.
    }

    /**
     * Führt die Interaktionslogik für den Feldtyp STADT aus.
     * @param spieler Der Spieler, der mit dem Stadtfeld interagiert
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     */
    private void stadtInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Stadtfeld
        // Zum Beispiel: Einheiten rekrutieren, Handel betreiben, Quests erhalten, etc.
    }

    /**
     * Führt die Interaktionslogik für den Feldtyp RESSOURCE aus.
     * @param spieler Der Spieler, der mit dem Ressourcenfeld interagiert
     * @param zeile Zeilenindex
     * @param spalte Spaltenindex
     */
    private void ressourcenInteraktion(Spieler spieler, int zeile, int spalte) {
        // Implementiere die spezifische Logik für die Interaktion mit einem Ressourcenfeld
        // zum Beispiel: Ressourcen sammeln, abbauen, etc.
    }

    /**
     * Zeigt das Spielbrett mit Spielernposition an.
     * spielbrett Das Spielbrett als zweidimensionales Feld
     * @param spieler Der Spieler, dessen Position markiert werden soll
     */
    public void zeigeSpielbrett(Spieler spieler) {
        int spielerPosX = spieler.getPositionX();
        int spielerPosY = spieler.getPositionY();

        for (int y = 0; y < zeilen; y++) {
            for (int x = 0; x < spalten; x++) {
                FeldTypen feldTyp = felder[y][x];

                if (x == spielerPosX && y == spielerPosY) {
                    System.out.print(ConsoleColors.BLUE_BOLD + " [" + feldTyp.name().charAt(0) + "] " + ConsoleColors.RESET);
                } else {
                    System.out.print(" [" + feldTyp.name().charAt(0) + "] ");
                }
            }
            System.out.println();
        }
    }

    public static String updateSpielbrettArea() {
        if (Game.spieler != null) {
            int spielerPosX = Game.spieler.getPositionX();
            int spielerPosY = Game.spieler.getPositionY();

            StringBuilder spielbrettText = new StringBuilder();
            for (int y = 0; y < zeilen; y++) {
                for (int x = 0; x < spalten; x++) {
                    FeldTypen feldTyp = felder[y][x];

                    if (x == spielerPosX && y == spielerPosY) {
                        spielbrettText.append(ConsoleColors.BLUE_BOLD).append(" [").append(feldTyp.name().charAt(0)).append("] ").append(ConsoleColors.RESET);
                    } else {
                        spielbrettText.append(" [").append(feldTyp.name().charAt(0)).append("] ");
                    }
                }
                spielbrettText.append("\n");
            }
            return spielbrettText.toString();
        }
        return null;
    }

    /**
     * Gibt das Spielbrett mit Feldtypen aus.
     * @param spielbrett Das Spielbrett
     */
    public void printSpielbrett(Spielbrett spielbrett) {
        int zeilen = spielbrett.getZeile();
        int spalten = spielbrett.getSpalte();

        for (int x = 0; x < zeilen; x++) {
            for (int y = 0; y < spalten; y++) {
                FeldTypen feldTyp = spielbrett.getFeldtyp(x, y);
                System.out.println("Feld: " + x + "x" + y + " Feldtyp: " + feldTyp);
            }
        }
    }

}
