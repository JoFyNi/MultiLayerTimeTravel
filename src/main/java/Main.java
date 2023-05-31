public class Main {
    public static void main(String[] args) {
        Spielbrett spielbrett = new Spielbrett(8, 8);       // größe
        spielbrett.setFeldtyp(0, 0, FeldTypen.LAND);
        FeldTypen feldtyp = spielbrett.getFeldtyp(0, 0);    // position
        System.out.println(feldtyp);  // Ausgabe: LAND

        System.out.println("Spielfeld Größe: " + spielbrett.getZeile() + "x" + spielbrett.getSpalte());
    }
}
