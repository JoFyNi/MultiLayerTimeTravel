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

    public void speichern(Map<Class<? extends Ressource>, Ressource> ressourcenMap, String statusMessage, String ressourcenMessage) {
        Properties properties = new Properties();

        // Ressourcen speichern
        for (Map.Entry<Class<? extends Ressource>, Ressource> entry : ressourcenMap.entrySet()) {
            Class<? extends Ressource> ressourcenKlasse = entry.getKey();
            Ressource ressource = entry.getValue();
            properties.setProperty(ressourcenKlasse.getName(), String.valueOf(ressource.getMenge()));
        }

        // Status speichern
        properties.setProperty("status", statusMessage);

        // Ressourcen-Message speichern
        properties.setProperty("ressourcen", ressourcenMessage);

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
        spieler.getStatus().setLeben(getIntegerProperty(props, "leben"));
        spieler.getStatus().setAusdauer(getIntegerProperty(props, "ausdauer"));
        spieler.getStatus().setKraft(getIntegerProperty(props, "kraft"));
        spieler.getStatus().setRuestung(getIntegerProperty(props, "ruestung"));
        spieler.getStatus().setWissen(getIntegerProperty(props, "wissen"));
        spieler.getStatus().setFuehrung(getIntegerProperty(props, "fuehrung"));

        // Setze die Ressourcenmengen
        spieler.setRessourceMenge(Erz.class, getIntegerProperty(props, "erz"));
        spieler.setRessourceMenge(Holz.class, getIntegerProperty(props, "holz"));
        spieler.setRessourceMenge(Gold.class, getIntegerProperty(props, "gold"));
        spieler.setRessourceMenge(Kristall.class, getIntegerProperty(props, "kristall"));
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

    public void anzeigenGespeicherteDaten() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(speicherdatei)) {
            props.load(input);
            System.out.println("Gespeicherte Daten:");
            props.forEach((key, value) -> System.out.println(key + "=" + value));
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Daten: " + e.getMessage());
        }
    }
}
