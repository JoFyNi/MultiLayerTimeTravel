package gegner;

public class Bandit extends Gegner {
    private int leben;
    private int ausdauer;
    private int kraft;
    private int ruestung;
    private int wissen;
    private int fuehrung;

    public Bandit(int l, int a, int k, int r, int w, int f) {
        super(l,a,k,r,w,f);
    }

    @Override
    public String toString() {
        return "Bandit:\n" +
                "Leben: " + getLeben() +
                "\nAusdauer: " + " : " + getAusdauer() +
                "\nKraft: " + getAngriff() +
                "\nRüstung: " + getRuestung() +
                "\nWissen: " + getWissen() +
                "\nFührung: " + getFuehrung();
    }
}
