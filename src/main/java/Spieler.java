import gegner.Bandit;
import gegner.Wolf;
import ressourcen.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Spieler extends MainFrame implements RessourcenInterface {
    private RessourcenManager ressourcenManager;
    private Status status;
    private int positionX;
    private int positionY;
    private static String logMessage;
    private Map<Class<? extends Ressource>, Ressource> ressourcenMap;

    // Ressourcen
    private Erz erz;
    private Holz holz;
    private Gold gold;
    private Kristall kristall;

    public Spieler(int startX, int startY, RessourcenManager RM) {
        positionX = startX;
        positionY = startY;
        status = new Status(200, 100, 10, 5, 20, 15); // Beispielwerte für den Status
        this.ressourcenManager = RM;
        this.ressourcenMap = new HashMap<>();
    }

    public Status getStatus() {
        return status;
    }

    // Methode zum Bewegen des Spielers auf dem Spielbrett
    public void bewege(int neueX, int neueY, Spieler spieler, Spielbrett spielbrett) {
        int bewegungskosten = spielbrett.getBewegungskosten(spieler.getPositionX(), spieler.getPositionY(), neueX, neueY); // Standard-Bewegungskosten

        FeldTypen feldtyp = spielbrett.getFeldtyp(neueX, neueY);

        if (feldtyp == FeldTypen.LAND) {
            bewegungskosten += 1; // Erhöhe die Bewegungskosten um 1 bei jedem Berg-Feld
        } else if (feldtyp == FeldTypen.WASSER) {
            System.out.println(logMessage);
            return;
        } else if (feldtyp == FeldTypen.GEBIRGE) {
            bewegungskosten += 3; // Erhöhe die Bewegungskosten um 1 bei jedem Berg-Feld
        } else if (feldtyp == FeldTypen.WALD) {
            bewegungskosten += 2; // Erhöhe die Bewegungskosten um 1 bei jedem Wald-Feld
        } else if (feldtyp == FeldTypen.STADT) {
            bewegungskosten += -1;
            logMessage = "Du hast eine Stadt erreicht. Kostenlose Einheiten stehen zur Verfügung.";
            // Füge hier den Code hinzu, um kostenlose Einheiten zur Verfügung zu stellen
        } else if (feldtyp == FeldTypen.WUESTE) {
            bewegungskosten += 1;
            logMessage = "Du bist in einer Wüste angelangt. Es besteht die Möglichkeit von Banditen angegriffen zu werden.";
            // Füge hier den Code hinzu, um einen Kampf mit Gegnern zu initiieren
        }
        System.out.println(logMessage);

        if (bewegungskosten > spieler.getStatus().getAusdauer()) {
            System.out.println("Du bist zu erschöpft, um dich so weit zu bewegen.");
            logMessage = "Du bist zu erschöpft, um dich so weit zu bewegen.";
            return;
        }

        positionX = neueX;
        positionY = neueY;
        spieler.getStatus().setAusdauer(spieler.getStatus().getAusdauer() - bewegungskosten);

        logMessage = ("Spieler bewegt sich nach:\nx:" + positionX + " y:" + positionY);
        System.out.println(logMessage);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    // Methode zur Interaktion mit einem bestimmten Feldtyp
    public void interagiere(FeldTypen feldtyp) {
        Scanner scanner = new Scanner(System.in);
        logMessage = feldtyp.toString();
        switch (feldtyp) {
            case LAND:
                System.out.println("Land");
                System.out.println("1. Warten");
                System.out.println("2. Erkunden");
                System.out.println("3. Bauen");
                int landAuswahl = scanner.nextInt();
                switch (landAuswahl) {
                    case 1:
                        // Logik für das Warten auf dem Landfeld
                        getStatus().setAusdauer(getStatus().getAusdauer() + 3);
                        break;
                    case 2:
                        // Logik für das Erkunden der umliegenden Felder
                        break;
                    case 3:
                        // Logik für das Bauen eines Gebäudes auf dem Landfeld
                        /*
                        Verteidigung, Tor wenn [G] [L] [G]

                                         [L] [L] [L]
                        Stadt bauen wenn [L] [L] [L]
                                         [L] [L] [L]
                         */
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case WASSER:
                System.out.println("Wasser");
                System.out.println("Kann nicht betreten werden.");
                // wenn Landfläche vor Wasser → Hafen bauen [L] [W]
                break;
            case GEBIRGE:
                System.out.println("Gebirge");
                System.out.println("1. Klettern");
                System.out.println("2. Ressourcen abbauen");
                System.out.println("3. Verstecken");
                int gebirgeAuswahl = scanner.nextInt();
                switch (gebirgeAuswahl) {
                    case 1:
                        // Logik für das Klettern auf dem Gebirgsfeld
                        break;
                    case 2:
                        // Logik für das Abbauen von Ressourcen auf dem Gebirgsfeld
                        // change Erz, Gold, Kristall abzubauen
                        Random random = new Random();
                        int randomErzMenge = random.nextInt(6); // Zufällige Zahl zwischen 0 und 5
                        int randomGoldMenge = random.nextInt(6); // Zufällige Zahl zwischen 0 und 5
                        int randomKristallMenge = random.nextInt(6); // Zufällige Zahl zwischen 0 und 5

                        erz.setMenge(holz.getMenge() + randomErzMenge);
                        gold.setMenge(holz.getMenge() + randomGoldMenge);
                        kristall.setMenge(holz.getMenge() + randomKristallMenge);
                        logMessage = randomErzMenge + " Erz gefunden\n" +
                                randomGoldMenge + " Gold gefunden\n" +
                                randomKristallMenge + " Kristall gefunden\n";
                        System.out.println(logMessage);
                        break;
                    case 3:
                        // Logik für das Verstecken auf dem Gebirgsfeld
                        getStatus().setAusdauer(getStatus().getAusdauer() + 2);
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case WALD:
                System.out.println("WALD");
                System.out.println("1. Warten");
                System.out.println("2. Abholzen");
                System.out.println("3. Bauen");
                int waldAuswahl = scanner.nextInt();
                switch (waldAuswahl) {
                    case 1:
                        // Logik für das Warten auf dem waldAuswahlfeld
                        getStatus().setAusdauer(getStatus().getAusdauer() + 3);
                        break;
                    case 2:
                        // Logik für das Abholzen des Feldes
                        // abholzen generiert 2 Holz
                        holz.setMenge(holz.getMenge() + 2);
                        break;
                    case 3:
                        // Logik für das Bauen eines Gebäudes auf dem Waldfeld
                        // Holzfäller hütte → 5 Holz pro runde
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case STADT:
                // öffnet neues Frame für Stadtverwaltung

                System.out.println("Stadt");
                System.out.println("1. Ausruhen");
                System.out.println("2. Verwalten");
                System.out.println("3. Einheiten rekrutieren");
                int stadtAuswahl = scanner.nextInt();
                switch (stadtAuswahl) {
                    case 1:
                        // Logik für das Ausruhen in der Stadt
                        getStatus().setAusdauer(getStatus().getAusdauer() + 10);
                        break;
                    case 2:
                        // Logik für das Verwalten der Stadt (Bauen, Upgraden)
                        break;
                    case 3:
                        // Logik für die Rekrutierung von Einheiten in der Stadt
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case WUESTE:
                System.out.println("Wüste");
                System.out.println("1. Warten");
                System.out.println("2. Verwalten");
                int ressourcenAuswahl = scanner.nextInt();
                switch (ressourcenAuswahl) {
                    case 1:
                        // Logik für die Auflistung der Ressourcen auf dem Ressourcenfeld
                        getStatus().setAusdauer(getStatus().getAusdauer() + 1);

                        changeAufGegner();
                        break;
                    case 2:
                        // Logik für die Verwaltung der Ressourcen auf dem Ressourcenfeld
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;

            // Weitere Feldtypen ...

            default:
                System.out.println("Warte");
                break;
        }
    }

    private void changeAufGegner() {
        Wolf wolf = null;
        Bandit bandit = null;
        Random random = new Random();
        int randomGegner = random.nextInt(6); // Zufällige Zahl zwischen 0 und 5

        switch (randomGegner) {
            case 0:
                wolf = new Wolf(50,20,5,2,2,10);
                System.out.println(wolf);
                break;
            case 1:
                break;
            case 2:
                bandit = new Bandit(100,30,8,6,7,9);
                System.out.println(bandit);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }

        if (wolf != null || bandit != null) {

        }
    }

    public String getRessourceInformation(Class<? extends Ressource> ressourceClass) {
        Ressource ressource = ressourcenManager.getRessource(ressourceClass);
        if (ressource != null) {
            return ressourceClass.getSimpleName() + ": " + ressource.getMenge();
        }
        return "";
    }

    public void setRessourceMenge(Class<? extends Ressource> ressourcenKlasse, int menge) {
        Ressource ressource = ressourcenMap.get(ressourcenKlasse);
        if (ressource != null) {
            ressource.setMenge(menge);
        } else {
            // Handle the case when the resource is not found
            System.out.println("Ressource not found.");
        }
    }

    public void setRessourcen(Erz e, Holz h, Gold g, Kristall k) {
        this.erz = e;
        this.holz = h;
        this.gold = g;
        this.kristall = k;
    }
    public Ressource getErz() {
        return erz;
    }
    public Ressource getHolz() {
        return holz;
    }
    public Ressource getGold() {
        return gold;
    }
    public Ressource getKristall() {
        return kristall;
    }

    public static String getLogMessage() {
        return logMessage;
    }

}