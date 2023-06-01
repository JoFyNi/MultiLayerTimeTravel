package ressourcen;

import java.io.Serializable;

public class Holz extends Ressource implements Serializable {
    public Holz(int menge) {
        super("Holz", menge);
    }

    @Override
    public String toString() {
        return "Holz: " + getMenge();
    }

    // Spezifische Methoden für die Holz-Ressource
    // ...
}