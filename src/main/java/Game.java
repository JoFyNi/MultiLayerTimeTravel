import ressourcen.*;

import javax.swing.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Die Hauptklasse des Spiels.
 */
public class Game extends MainFrame implements RessourcenInterface{
    private Spielbrett spielbrett;
    static Spieler spieler;
    private Status status;
    static String statusMessage;
    static String ressourcenMessage;
    private final RessourcenManager ressourcenManager = new RessourcenManager("ressourcen.dat");
    Speichern speicher = new Speichern("ressourcen.dat");
    Properties props = new Properties();

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
        status = spieler.getStatus();
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
            ressourcenManager.updateRessource(Kristall.class, 10);

            // Starte den eigentlichen Spielablauf
            spieleSpiel();
        } else if (auswahl == 2) {
            // Spiel laden
            speicher.laden(props, spieler);

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
        // erstelle GUI
        createGUI();
        // übergebe spieler und status
        setSpielerStatus(spieler, status);

        // rufe gespeicherte werte ab
        checkForExistingGame();

        Scanner scanner = new Scanner(System.in);

        // Spielablauf-Schleife
        while (true) {
            // Zeige das Spielbrett
            spielbrett.zeigeSpielbrett(spieler);

            System.out.println("Spielerposition: " + spieler.getPositionX() + "x" + spieler.getPositionY());

            setRessourceMessage();
            setStatusMessage();

            System.out.println(getStatusMessage());
            System.out.println(getRessourceMessage());

            // Beispiel: Speichern des Ressourcenstands

            System.out.println("1. Bewegen");
            System.out.println("2. Interagieren");
            System.out.println("3. Speichern");
            System.out.println("4. Beenden");

            int auswahl = scanner.nextInt();
            FeldTypen feldtyp = spielbrett.getFeldtyp(spieler.getPositionX(), spieler.getPositionY());

            switch (auswahl) {
                case 1:
                    // Bewegung
                    System.out.print("Gib die neue X-Position ein: ");
                    int neueX = scanner.nextInt();
                    System.out.print("Gib die neue Y-Position ein: ");
                    int neueY = scanner.nextInt();

                    spieler.bewege(neueX, neueY, spieler, spielbrett);

                    // Überprüfe auf Kollisionen oder spezifische Ereignisse nach der Bewegung
                    spieler.interagiere(feldtyp);
                    break;
                case 2:
                    // Interaktion
                    spieler.interagiere(feldtyp);
                    break;
                case 3:
                    speicher.speichern(ressourcenManager.getRessourcenMap(), spieler);    // ressourcenManager.getRessourcenMap()
                    break;
                case 4:
                    System.exit(0);
                    break; // Verlasse die Spielablauf-Schleife
                default:
                    System.out.println("Ungültige Auswahl.");
                    break;
            }
        }
    }

    private void checkForExistingGame() {
        speicher.laden(props, spieler);
        if (props.isEmpty()) {
            ressourcenManager.addRessource(erz);
            ressourcenManager.addRessource(holz);
            ressourcenManager.addRessource(gold);
            ressourcenManager.addRessource(kristall);
        } else {
            ressourcenManager.addRessource(spieler.getErz());
            ressourcenManager.addRessource(spieler.getHolz());
            ressourcenManager.addRessource(spieler.getGold());
            ressourcenManager.addRessource(spieler.getKristall());
        }
    }

    public void setStatusMessage() {
        statusMessage = "\n----Status----\n" +
                "Leben: " + spieler.getStatus().getLeben() + "\n" +
                "Ausdauer: " + spieler.getStatus().getAusdauer() + "\n" +
                "Kraft: " + spieler.getStatus().getKraft() + "\n" +
                "Rüstung: " + spieler.getStatus().getRuestung() + "\n" +
                "Wissen: " + spieler.getStatus().getWissen() + "\n" +
                "Führung: " + spieler.getStatus().getFuehrung() + "\n";
    }
    public void setRessourceMessage() {
        ressourcenMessage = "Ressourcenstand: \n" +
                spieler.getRessourceInformation(Erz.class) + "\n" +
                spieler.getRessourceInformation(Holz.class) + "\n" +
                spieler.getRessourceInformation(Gold.class) + "\n" +
                spieler.getRessourceInformation(Kristall.class) + "\n";
    }
    public static String getStatusMessage() {
        return statusMessage;
    }
    public static String getRessourceMessage() {
        return ressourcenMessage;
    }

    private void createGUI() {
        MainFrame ui = new MainFrame();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(1080,720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
