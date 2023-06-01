import ressourcen.Erz;
import ressourcen.Gold;
import ressourcen.Holz;
import ressourcen.RessourcenManager;

import java.util.Scanner;

/**
 * Die Hauptklasse des Spiels.
 */
public class Game {
    private Spielbrett spielbrett;
    private Spieler spieler;
    private final RessourcenManager ressourcenManager = new RessourcenManager("ressourcen.dat");

    private final Erz erz = new Erz(10);
    private final Holz holz = new Holz(10);
    private final Gold gold = new Gold(10);

    /**
     * Erstellt ein neues Spielobjekt.
     */
    public Game() {
        // Initialisiere das Spielbrett
        spielbrett = new Spielbrett(20, 20); // Beispielgröße: 20x20
        spielbrett.generiereSpielbrett(); // Generiere die Felder des Spielbretts

        spielbrett.printSpielbrett(spielbrett); // Ausgabe der Felder mit Koordinaten und Feldtypen

        // Initialisiere den Spieler
        spieler = new Spieler(0, 0, ressourcenManager); // Startposition des Spielers: (0, 0)
    }

    /**
     * Startet das Spiel.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Auswahlmenü anzeigen
        System.out.println("Willkommen zum Spiel!");
        System.out.println("1. Neues Spiel starten");
        System.out.println("2. Spiel laden");

        int auswahl = scanner.nextInt();

        if (auswahl == 1) {
            // Neues Spiel starten
            spieler = new Spieler(0, 0, ressourcenManager);

            // Weise dem Spieler Startressourcen zu
            ressourcenManager.updateRessource(Erz.class, 10);
            ressourcenManager.updateRessource(Holz.class, 10);
            ressourcenManager.updateRessource(Gold.class, 10);

            // Speichere den Ressourcenstand
            ressourcenManager.speichern();

            // Starte den eigentlichen Spielablauf
            spieleSpiel();
        } else if (auswahl == 2) {
            // Spiel laden
            ressourcenManager.laden();

            // Beispiel: Lade die Position des Spielers
            spieler = new Spieler(0, 0, ressourcenManager); // Setze die richtigen Koordinaten basierend auf dem geladenen Spielstand

            // Starte den eigentlichen Spielablauf
            spieleSpiel();
        } else {
            System.out.println("Ungültige Auswahl. Das Spiel wird beendet.");
        }
    }

    /**
     * Führt den Spielablauf aus.
     */
    private void spieleSpiel() {
        Scanner scanner = new Scanner(System.in);

        ressourcenManager.addRessource(erz);
        ressourcenManager.addRessource(holz);
        ressourcenManager.addRessource(gold);

        // Spielablauf-Schleife
        while (true) {
            // Zeige das Spielbrett
            spielbrett.zeigeSpielbrett(spieler);

            System.out.println("Spielerposition: " + spieler.getPositionX() + "x" + spieler.getPositionY());
            System.out.println("Aktueller Ressourcenstand:");
            System.out.println("Erz: " + spieler.getRessourceMenge(Erz.class));
            System.out.println("Holz: " + spieler.getRessourceMenge(Holz.class));
            System.out.println("Gold: " + spieler.getRessourceMenge(Gold.class));

            // Beispiel: Speichern des Ressourcenstands
            ressourcenManager.speichern();

            System.out.println("1. Bewegen");
            System.out.println("2. Interagieren");
            System.out.println("3. Beenden");

            int auswahl = scanner.nextInt();

            if (auswahl == 1) {
                // Bewegung
                System.out.print("Gib die neue X-Position ein: ");
                int neueX = scanner.nextInt();
                System.out.print("Gib die neue Y-Position ein: ");
                int neueY = scanner.nextInt();

                spieler.bewege(neueX, neueY);
                System.out.println("Spieler bewegt sich nach: " + spieler.getPositionX() + "x" + spieler.getPositionY());

                // Überprüfe auf Kollisionen oder spezifische Ereignisse nach der Bewegung
                FeldTypen feldtyp = spielbrett.getFeldtyp(spieler.getPositionX(), spieler.getPositionY());
                spieler.interagiere(feldtyp);
            } else if (auswahl == 2) {
                // Interaktion
                FeldTypen feldtyp = spielbrett.getFeldtyp(spieler.getPositionX(), spieler.getPositionY());
                spieler.interagiere(feldtyp);
            } else if (auswahl == 3) {
                // Beenden
                break; // Verlasse die Spielablauf-Schleife
            } else {
                System.out.println("Ungültige Auswahl.");
            }
        }
    }

    /**
     * Der Einstiegspunkt des Spiels.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
