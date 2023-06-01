import ressourcen.*;

import java.util.Scanner;

public class Spieler {
    private RessourcenManager ressourcenManager;
    private int positionX;
    private int positionY;

    public Spieler(int startX, int startY, RessourcenManager RM) {
        positionX = startX;
        positionY = startY;
        ressourcenManager = RM;
    }

    // Methode zum Bewegen des Spielers auf dem Spielbrett
    public void bewege(int deltaX, int deltaY) {
        positionX += deltaX;
        positionY += deltaY;
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
