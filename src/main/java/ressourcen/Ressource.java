package ressourcen;

public abstract class Ressource {
    protected String name;
    protected int menge;

    public Ressource(String name, int menge) {
        this.name = name;
        this.menge = menge;
    }

    public String getName() {
        return name;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}