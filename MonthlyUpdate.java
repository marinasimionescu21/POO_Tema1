import java.util.List;

public class MonthlyUpdate {
    private List<Consumer> newConsumers;
    private List<Distributor> costsChanges;

    /**
     * returneaza lista de consumatori noi dintr-o luna
     */
    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     * seteaza lista de consumatori noi dintr-o luna
     */
    public void setNewConsumers(final List<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    /**
     * returneaza schimbarile de cost care au loc intr-o luna
     */
    public List<Distributor> getCostsChanges() {
        return costsChanges;
    }

    /**
     * seteaza schimbarile de cost
     */
    public void setCostsChanges(final List<Distributor> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
