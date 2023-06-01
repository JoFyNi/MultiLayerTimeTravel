import ressourcen.*;

import java.util.Scanner;

public class Spieler {
    private RessourcenManager ressourcenManager;
    private Status status;
    private int positionX;
    private int positionY;

    public Spieler(int startX, int startY, RessourcenManager RM) {
        positionX = startX;
        positionY = startY;
        status = new Status(200, 100, 10, 5, 20, 15); // Beispielwerte für den Status
        this.ressourcenManager = RM;
    }

    public Status getStatus() {
        return status;
    }

    // Methode zum Bewegen des Spielers auf dem Spielbrett
    public void bewege(int neueX, int neueY, Spieler spieler, Spielbrett spielbrett) {
        int bewegungskosten = spielbrett.getBewegungskosten(spieler.getPositionX(), spieler.getPositionY(), neueX, neueY); // Standard-Bewegungskosten

        FeldTypen feldtyp = spielbrett.getFeldtyp(neueX, neueY);

        if (feldtyp == FeldTypen.WASSER) {
            System.out.println("Das Wasser kann nicht betreten werden.");
            return;
        } else if (feldtyp == FeldTypen.GEBIRGE) {
            bewegungskosten += 1; // Erhöhe die Bewegungskosten um 1 bei jedem Berg-Feld
        } else if (feldtyp == FeldTypen.STADT) {
            System.out.println("Du hast eine Stadt erreicht. Kostenlose Einheiten stehen zur Verfügung.");
            // Füge hier den Code hinzu, um kostenlose Einheiten zur Verfügung zu stellen
        } else if (feldtyp == FeldTypen.RESSOURCE) {
            System.out.println("Du hast eine Ressource gefunden. Es besteht die Möglichkeit eines Kampfes mit Gegnern.");
            // Füge hier den Code hinzu, um einen Kampf mit Gegnern zu initiieren
        }

        if (bewegungskosten > spieler.getStatus().getAusdauer()) {
            System.out.println("Du bist zu erschöpft, um dich so weit zu bewegen.");
            return;
        }

        positionX = neueX;
        positionY = neueY;
        spieler.getStatus().setAusdauer(spieler.getStatus().getAusdauer() - bewegungskosten);


        System.out.println("Spieler bewegt sich nach: " + positionX + "x" + positionY);
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
                        break;
                    case 2:
                        // Logik für das Erkunden der umliegenden Felder
                        break;
                    case 3:
                        // Logik für das Bauen eines Gebäudes auf dem Landfeld
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case WASSER:
                System.out.println("Wasser");
                System.out.println("Kann nicht betreten werden.");
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
                        break;
                    case 3:
                        // Logik für das Verstecken auf dem Gebirgsfeld
                        break;
                    default:
                        System.out.println("Ungültige Auswahl.");
                        break;
                }
                break;
            case STADT:
                System.out.println("Stadt");
                System.out.println("1. Betreten");
                System.out.println("2. Verwalten");
                System.out.println("3. Einheiten rekrutieren");
                int stadtAuswahl = scanner.nextInt();
                switch (stadtAuswahl) {
                    case 1:
                        // Logik für das Betreten der Stadt
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
            case RESSOURCE:
                System.out.println("Ressource");
                System.out.println("1. Auflisten");
                System.out.println("2. Verwalten");
                int ressourcenAuswahl = scanner.nextInt();
                switch (ressourcenAuswahl) {
                    case 1:
                        // Logik für die Auflistung der Ressourcen auf dem Ressourcenfeld
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

    public String getRessourceMenge(Class<? extends Ressource> ressourceClass) {
        Ressource ressource = ressourcenManager.getRessource(ressourceClass);
        if (ressource != null) {
            return ressourceClass.getSimpleName() + ": " + ressource.getMenge();
        }
        return "";
    }
}
