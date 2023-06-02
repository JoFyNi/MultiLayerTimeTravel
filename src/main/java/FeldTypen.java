public enum FeldTypen {
    /**
     * LAND: ein normales Landfeld mit einem Ressourcenwert von 1.
     * WASSER: ein Wasserfeld ohne Ressourcenwert.
     * GEBIRGE: ein Gebirgsfeld ohne Ressourcenwert.
     * STADT: ein Stadtfeld ohne Ressourcenwert.
     * RESSOURCE: ein Ressourcenfeld mit einem Ressourcenwert von 10.
     * WALD:
     * WÜSTE:
     * TURM:
     */
    LAND(1),
    WASSER(0),
    GEBIRGE(0),
    WALD(5),
    STADT(0),
    RESSOURCE(10);

    private int ressourcenWert;

    FeldTypen(int ressourcenWert) {
        this.ressourcenWert = ressourcenWert;
    }

    public int getRessourcenWert() {
        return ressourcenWert;
    }
}
