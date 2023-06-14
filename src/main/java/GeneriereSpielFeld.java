import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GeneriereSpielFeld extends JPanel {

    private static FeldTypen[][] felder; // Felder, die das Spielbrett repräsentieren
    static int SPIELBRETT_BREITE = 10; // Breite des Spielbretts
    static int SPIELBRETT_HOEHE = 10; // Höhe des Spielbretts

    private static final int HEX_SIZE = 50; // Größe eines Hexagons
    private static final int HEX_WIDTH = (int) (HEX_SIZE * Math.sqrt(1)); // Horizontaler Abstand zwischen den Hexagons
    private static final int HEX_HEIGHT = 2 * HEX_SIZE; // Vertikaler Abstand zwischen den Hexagons
    private static final Color[] HEX_COLORS = {
            Color.GREEN, Color.BLUE, Color.GRAY, Color.YELLOW, Color.RED, Color.ORANGE, Color.MAGENTA
    }; // Farben der Hexagons

    // Konstruktor
    public GeneriereSpielFeld(FeldTypen[][] felder) {
        this.felder = felder;
        setPreferredSize(new Dimension(HEX_WIDTH * felder[0].length, HEX_HEIGHT * felder.length));
    }

    // Methode zum Setzen der Spielbrettgröße
    public static void setSpielbrett(int spielbrettBreite, int spielbrettHoehe) {
        SPIELBRETT_BREITE = spielbrettBreite;
        SPIELBRETT_HOEHE = spielbrettHoehe;
    }

    // Methode zum Zeichnen eines Hexagons
    // Methode zum Zeichnen eines Hexagons
    private void drawHexagon(Graphics2D g2d, int x, int y, FeldTypen feldTyp, boolean isPlayerPosition) {
        int[] xPoints = {
                x + HEX_SIZE/2, x + HEX_SIZE + HEX_SIZE/2, x + 2*HEX_SIZE, x + HEX_SIZE + HEX_SIZE/2, x + HEX_SIZE/2, x
        };
        int[] yPoints = {
                y, y, y + HEX_SIZE, y + 2*HEX_SIZE, y + 2*HEX_SIZE, y + HEX_SIZE
        };

        if (isPlayerPosition) {
            g2d.setColor(Color.MAGENTA); // Lila Farbe für Spielerposition
        } else {
            g2d.setColor(HEX_COLORS[feldTyp.ordinal()]); // Farbe des Hexagons basierend auf dem Feldtyp
        }

        g2d.fillPolygon(xPoints, yPoints, 6); // Hexagon zeichnen und füllen
        g2d.setColor(Color.BLACK); // Farbe für den Rand des Hexagons
        g2d.drawPolygon(xPoints, yPoints, 6); // Rand des Hexagons zeichnen

        String labelText = feldTyp.toString();
        int labelX = x + HEX_SIZE - g2d.getFontMetrics().stringWidth(labelText) / 2;
        int labelY = y + HEX_HEIGHT/2 + g2d.getFontMetrics().getHeight() / 2;

        g2d.drawString(labelText, labelX, labelY); // Beschriftung des Hexagons
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int startX = (getWidth() - (3 * HEX_WIDTH) / 2 * felder[0].length) / 2; // Startposition für das Zeichnen der Hexagons
        int startY = (getHeight() - HEX_HEIGHT * felder.length) / 2; // Startposition für das Zeichnen der Hexagons

        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                int x = startX + j * (3 * HEX_WIDTH / 2); // x-Position des Hexagons
                int y = startY + i * HEX_HEIGHT + ((j % 2) * HEX_HEIGHT / 2); // y-Position des Hexagons

                boolean isPlayerPosition = (i == Game.getPlayerPositionX && j == Game.getPlayerPositionY);

                drawHexagon(g2d, x, y, felder[i][j], isPlayerPosition); // Hexagon zeichnen
            }
        }
    }

    private void setPlayer(int positionX, int positionY) {
        // Überprüfen, ob die Spielerposition gültig ist
        if (positionX >= 0 && positionX < felder.length && positionY >= 0 && positionY < felder[0].length) {
            FeldTypen feldTyp = felder[positionX][positionY]; // Feldtyp an der Spielerposition

            // Spielerposition markieren (z. B. durch Ändern des Feldtyps)
            felder[positionX][positionY] = FeldTypen.STADT; // Beispiel: Verwenden Sie hier den passenden Feldtyp, der den Spieler hervorhebt

            // Blinkenden Rand erzeugen
            new Thread(() -> {
                for (int i = 0; i < 6; i++) {
                    try {
                        Thread.sleep(500); // Pause von 500 Millisekunden
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Zustand des blinkenden Rands umschalten
                    if (feldTyp != FeldTypen.WASSER && feldTyp != FeldTypen.GEBIRGE) {
                        felder[positionX][positionY] = (i % 2 == 0) ? feldTyp : FeldTypen.STADT;
                    }

                    repaint(); // Neuzeichnen des Spielbretts
                }

                // Spielerposition zurücksetzen
                felder[positionX][positionY] = feldTyp;
                repaint(); // Neuzeichnen des Spielbretts
            }).start();
        }
    }


    // Hauptmethode zum Erzeugen des Spielbretts und Anzeigen der GUI
    public static void main(String[] args) {
        FeldTypen[][] felder = generiereSpielbrett(); // Spielbrett generieren
        GeneriereSpielFeld hexagonGenerator = new GeneriereSpielFeld(felder); // GeneriereSpielFeld-Objekt erstellen

        JFrame frame = new JFrame("Hexagon Generator"); // JFrame erstellen
        frame.setSize(new Dimension(1080, 720)); // Größe des JFrame festlegen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beenden der Anwendung bei Schließen des JFrames
        frame.getContentPane().add(hexagonGenerator); // GeneriereSpielFeld dem JFrame hinzufügen
        frame.pack(); // JFrame packen
        frame.setLocationRelativeTo(null); // JFrame zentrieren
        frame.setVisible(true); // JFrame sichtbar machen
    }

    // Methode zum Generieren des Spielbretts
    public static FeldTypen[][] generiereSpielbrett() {
        FeldTypen[][] felder = new FeldTypen[SPIELBRETT_BREITE][SPIELBRETT_HOEHE]; // Spielbrett erstellen
        Random random = new Random();

        for (int i = 0; i < SPIELBRETT_BREITE; i++) {
            for (int j = 0; j < SPIELBRETT_HOEHE; j++) {
                FeldTypen feldtyp = FeldTypen.values()[random.nextInt(FeldTypen.values().length)]; // Zufälligen Feldtyp wählen
                felder[i][j] = feldtyp; // Feldtyp dem Spielbrett zuweisen
            }
        }
        return felder; // Spielbrett zurückgeben
    }
}
