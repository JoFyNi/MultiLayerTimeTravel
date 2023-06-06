package gegner;

public abstract class Gegner {
    protected int leben;
    protected int ausdauer;
    protected int kraft;
    protected int ruestung;
    protected int wissen;
    protected int fuehrung;

    public Gegner(int l, int a, int k, int r, int w, int f) {
        this.leben = l;
        this.ausdauer = a;
        this.kraft = k;
        this.ruestung = r;
        this.wissen = w;
        this.fuehrung = f;
    }

    public int getLeben() {
        return leben;
    }
    public int getAusdauer() {
        return ausdauer;
    }
    public int getAngriff() {
        return kraft;
    }
    public int getRuestung() {
        return ruestung;
    }
    public int getWissen() {
        return wissen;
    }
    public int getFuehrung() {
        return fuehrung;
    }

    public void setLeben(int l) {
        this.leben = l;
    }
    public void setAusdauer(int a) {
        this.ausdauer = a;
    }
    public void setAngriff(int k) {
        this.kraft = k;
    }

    public void setRuestung(int r) {
        this.ruestung = r;
    }
    public void setWissen(int w) {
        this.wissen = w;
    }
    public void setFuehrung(int f) {
        this.fuehrung = f;
    }
}
