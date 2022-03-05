import java.util.List;

public class Data {
    private List<Consumer> consumers;
    private List<Distributor> distributors;

    /**
     * adauga un consumator in lista de consumatori dintr-o luna
     */
    public void addConsumer(final Consumer consumer) {
        consumers.add(consumer);
    }

    /**
     * Schimba costurile lunare ale unui distribuitor, daca apar modificari
     */
    public void updateDistributorCosts(final Distributor distributor) {
        for (Distributor d : distributors) {
            if (d.getId() == distributor.getId()) {
                d.setInfrastructureCost(distributor.getInfrastructureCost());
                d.setProductionCost(distributor.getProductionCost());
            }
        }
    }

    /**
     * returneaza lista de consumatori dintr-o luna
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     * seteaza lista de consumatori dintr-o luna
     */
    public void setConsumers(final List<Consumer> consumers) {
        this.consumers = consumers;
    }

    /**
     * returneaza lista de distribuitori dintr-o luna
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    /**
     * seteaza lista de distribuitori dintr-o luna
     */
    public void setDistributors(final List<Distributor> distributors) {
        this.distributors = distributors;
    }
}
