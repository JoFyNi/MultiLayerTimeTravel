package ressourcen;

public abstract class Ressource {
    protected String name;
    protected int menge;

    public Ressource(String setName, int setMenge) {
        this.name = setName;
        this.menge = setMenge;
    }

    public String getName() {
        return name;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int newMenge) {
        this.menge = newMenge;
    }
}