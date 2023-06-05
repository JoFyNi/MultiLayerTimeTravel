public class Status {
    private int leben;
    private int ausdauer;
    private int kraft;
    private int ruestung;
    private int wissen;
    private int fuehrung;

    public Status(int leben, int ausdauer, int kraft, int ruestung, int wissen, int fuehrung) {
        this.leben = leben;
        this.ausdauer = ausdauer;
        this.kraft = kraft;
        this.ruestung = ruestung;
        this.wissen = wissen;
        this.fuehrung = fuehrung;
    }

    // Getter und Setter für die einzelnen Parameter

    public int getLeben() {
        return leben;
    }

    public void setLeben(int leben) {
        this.leben = leben;
    }

    public int getAusdauer() {
        return ausdauer;
    }

    public void setAusdauer(int ausdauer) {
        this.ausdauer = ausdauer;
    }

    public int getKraft() {
        return kraft;
    }

    public void setKraft(int kraft) {
        this.kraft = kraft;
    }

    public int getRuestung() {
        return ruestung;
    }

    public void setRuestung(int ruestung) {
        this.ruestung = ruestung;
    }

    public int getWissen() {
        return wissen;
    }

    public void setWissen(int setWissen) {
        this.wissen = setWissen;
    }

    public int getFuehrung() {
        return fuehrung;
    }

    public void setFuehrung(int setFuehrung) {
        this.fuehrung = setFuehrung;
    }

    public void copy(Status status) {
        status.getLeben();
        status.getAusdauer();
        status.getKraft();
        status.getRuestung();
        status.getWissen();
        status.getFuehrung();
    }
}
