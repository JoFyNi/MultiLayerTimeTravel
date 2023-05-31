public class Spieler {
    private int positionX;
    private int positionY;

    public Spieler(int startX, int startY) {
        positionX = startX;
        positionY = startY;
    }

    // Methode zum Bewegen des Spielers auf dem Spielbrett
    public void bewege(int deltaX, int deltaY) {
        positionX += deltaX;
        positionY += deltaY;
    }

    // Methode zur Interaktion mit einem bestimmten Feldtyp
    public void interagiere(FeldTypen feldtyp) {
        switch (feldtyp) {
            case LAND:
                // Logik für die Interaktion mit einem Landfeld
                // gehen, ausruhen, An-/ Bauen
                break;
            case WASSER:
                // Logik für die Interaktion mit einem Wasserfeld
                // Sammeln, Schwimmen, Fischen, Blockiert?
                break;
            case GEBIRGE:
                // Logik für die Interaktion mit einem Gebirgsfeld
                // Klettern / blockiert
                break;
            case STADT:
                // Logik für die Interaktion mit einem Stadtfeld
                // Verwalten, Hineingehen,
                break;
            case RESSOURCE:
                // Logik für die Interaktion mit einem Ressourcenfeld
                // abbauen -> aufsammeln
                break;
            default:
                // Unerwarteter Feldtyp
                break;
        }
    }
}
