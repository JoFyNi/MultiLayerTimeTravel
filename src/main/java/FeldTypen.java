public enum FeldTypen {
    /**
     * LAND: Ein normales Landfeld mit einem Ressourcenwert von 1.
     * WASSER: Ein Wasserfeld ohne Ressourcenwert.
     * GEBIRGE: Ein Gebirgsfeld ohne Ressourcenwert.
     * STADT: Ein Stadtfeld ohne Ressourcenwert.
     * RESSOURCE: Ein Ressourcenfeld mit einem Ressourcenwert von 10.
     */
    LAND(1),
    WASSER(0),
    GEBIRGE(0),
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
