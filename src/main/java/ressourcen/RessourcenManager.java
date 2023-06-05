package ressourcen;

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

    public Ressource getRessource(Class<? extends Ressource> ressourceClass) {
        for (Ressource ressource : ressourcenMap.values()) {
            if (ressourceClass.isInstance(ressource)) {
                return ressource;
            }
        }
        return null;
    }

    public Map<Class<? extends Ressource>, Ressource> getRessourceMap() {
        ressourcenMap.put(Holz.class, getRessource(Holz.class));
        ressourcenMap.put(Erz.class,  getRessource(Erz.class));
        ressourcenMap.put(Gold.class, getRessource(Gold.class));
        ressourcenMap.put(Kristall.class, getRessource(Kristall.class));
        // Füge hier weitere Ressourcen zur Map hinzu
        return ressourcenMap;
    }
    public Map<Class<? extends Ressource>, Ressource> getRessourcenMap() {
        return this.ressourcenMap;
    }
}
