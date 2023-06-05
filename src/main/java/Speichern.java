import ressourcen.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class Speichern {
    private String speicherdatei;

    public Speichern(String speicherdatei) {
        this.speicherdatei = speicherdatei;
    }

    /**
     * speichert alle Ressourcen und Status Werte nach Schlüssel und Werte prinzip
     * @param ressourcenMap alle Ressourcen Namen + Werte
     * @param spieler um getter und setter zu verwenden
     */
    public void speichern(Map<Class<? extends Ressource>, Ressource> ressourcenMap, Spieler spieler) {
        // Vor dem Aufruf von speichern und laden
        System.out.println("Ressourcen in der Map:");
        for (Map.Entry<Class<? extends Ressource>, Ressource> entry : ressourcenMap.entrySet()) {
            Class<? extends Ressource> ressourcenKlasse = entry.getKey();
            Ressource ressource = entry.getValue();
            System.out.println(ressourcenKlasse.getName() + ": " + ressource.getMenge());
        }

        Properties properties = new Properties();

        // Ressourcen speichern
        for (Map.Entry<Class<? extends Ressource>, Ressource> entry : ressourcenMap.entrySet()) {
            Class<? extends Ressource> ressourcenKlasse = entry.getKey();
            Ressource ressource = entry.getValue();
            properties.setProperty(ressourcenKlasse.getName(), String.valueOf(ressource.getMenge()));
        }

        Status status = spieler.getStatus();
        properties.setProperty("leben", String.valueOf(status.getLeben()));
        properties.setProperty("ausdauer", String.valueOf(status.getAusdauer()));
        properties.setProperty("kraft", String.valueOf(status.getKraft()));
        properties.setProperty("ruestung", String.valueOf(status.getRuestung()));
        properties.setProperty("wissen", String.valueOf(status.getWissen()));
        properties.setProperty("fuehrung", String.valueOf(status.getFuehrung()));

        /*
         * Speichert der Karte?
         */

        // Speichern der Properties in eine Datei
        try (FileOutputStream outputStream = new FileOutputStream(speicherdatei)) {
            properties.store(outputStream, "Spielstand");
            System.out.println("Daten erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Daten: " + e.getMessage());
        }
    }

    public void laden(Properties props, Spieler spieler) {
        // Lade die Properties aus der Datei
        try (InputStream input = new FileInputStream(speicherdatei)) {
            props.load(input);
            System.out.println("Daten erfolgreich geladen.");
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Daten: " + e.getMessage());
        }

        // Setze die geladenen Werte auf die entsprechenden Parameter
        Status status = spieler.getStatus();
        status.setLeben(getIntegerProperty(props, "leben"));
        status.setAusdauer(getIntegerProperty(props, "ausdauer"));
        status.setKraft(getIntegerProperty(props, "kraft"));
        status.setRuestung(getIntegerProperty(props, "ruestung"));
        status.setWissen(getIntegerProperty(props, "wissen"));
        status.setFuehrung(getIntegerProperty(props, "fuehrung"));

        // Drucken Sie die geladenen Werte
        System.out.println("Leben: " + status.getLeben());
        System.out.println("Ausdauer: " + status.getAusdauer());
        System.out.println("Kraft: " + status.getKraft());
        System.out.println("Rüstung: " + status.getRuestung());
        System.out.println("Wissen: " + status.getWissen());
        System.out.println("Führung: " + status.getFuehrung());

        int erzMenge = getIntegerProperty(props, "ressourcen.Erz");
        int holzMenge = getIntegerProperty(props, "ressourcen.Holz");
        int goldMenge = getIntegerProperty(props, "ressourcen.Gold");
        int kristallMenge = getIntegerProperty(props, "ressourcen.Kristall");

        // Erstellen Sie die Ressourcen-Objekte
        Erz erz = new Erz(erzMenge);
        Holz holz = new Holz(holzMenge);
        Gold gold = new Gold(goldMenge);
        Kristall kristall = new Kristall(kristallMenge);

        spieler.setRessourcen(erz, holz, gold, kristall);


        // Drucken Sie die geladenen Schlüssel und Werte
        System.out.println("Geladene Schlüssel und Werte:");
        props.forEach((key, value) -> System.out.println(key + "=" + value));
    }

    private int getIntegerProperty(Properties props, String key) {
        String value = props.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Fehler beim Parsen der Zahl: " + value);
            }
        }
        return 0; // or return a default value depending on your requirements
    }

    public void setRessourcenMenge() {

    }
}
