package ressourcen;

import java.io.Serializable;

public class Gold extends Ressource implements Serializable {
    public Gold(int menge) {
        super("Gold", menge);
    }

    @Override
    public String toString() {
        return "Gold: " + getMenge();
    }

    // Spezifische Methoden für die Gold-Ressource
    // ...
}