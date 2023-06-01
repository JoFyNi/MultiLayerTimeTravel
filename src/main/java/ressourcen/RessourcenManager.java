package ressourcen;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RessourcenManager {
    private Map<Class<? extends Ressource>, Ressource> ressourcenMap;
    private String speicherdatei = "ressourcen.dat";

    public RessourcenManager(String speicherdatei) {
        ressourcenMap = new HashMap<>();
        this.speicherdatei = speicherdatei;
    }

    public void addRessource(Ressource ressource) {
        ressourcenMap.put(ressource.getClass(), ressource);
    }

    public void updateRessource(Class<? extends Ressource> ressourceClass, int menge) {
        Ressource ressource = ressourcenMap.get(ressourceClass);
        if (ressource != null) {
            ressource.setMenge(menge);
        }
    }

    public int getRessourceMenge(Class<? extends Ressource> ressourceClass) {
        Ressource ressource = ressourcenMap.get(ressourceClass);
        if (ressource != null) {
            return ressource.getMenge();
        }
        return 0;
    }

    public void speichern() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(speicherdatei))) {
            oos.writeObject(ressourcenMap);
            oos.close();
            System.out.println("Ressourcen erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Ressourcen: " + e.getMessage());
        }
    }

    public void laden() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(speicherdatei))) {
            ressourcenMap = (Map<Class<? extends Ressource>, Ressource>) ois.readObject();
            ois.close();
            System.out.println("Ressourcen erfolgreich geladen.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fehler beim Laden der Ressourcen: " + e.getMessage());
        }
    }

    public Ressource getRessource(Class<? extends Ressource> ressourceClass) {
        for (Ressource ressource : ressourcenMap.values()) {
            if (ressourceClass.isInstance(ressource)) {
                return ressource;
            }
        }
        return null;
    }

}
