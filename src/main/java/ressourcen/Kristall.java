package ressourcen;

import java.io.Serializable;

public class Kristall extends Ressource implements Serializable {
    public Kristall(int menge) {
        super("Kristall", menge);
    }

    @Override
    public String toString() {
        return "Erz: " + getMenge();
    }

    // Spezifische Methoden für die Holz-Ressource
    // ...
}
