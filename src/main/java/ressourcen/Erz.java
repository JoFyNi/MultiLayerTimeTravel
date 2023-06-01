package ressourcen;

import java.io.Serializable;

public class Erz extends Ressource implements Serializable {
    public Erz(int menge) {
        super("Erz", menge);
    }

    @Override
    public String toString() {
        return "Erz: " + getMenge();
    }

    // Spezifische Methoden für die Holz-Ressource
    // ...
}
