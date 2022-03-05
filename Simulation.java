import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Simulation {
    private int numberOfTurns;
    @JsonAlias({ "initialData" })
    private Data data;
    private List<MonthlyUpdate> monthlyUpdates;

    /**
     * returneaza numarul de tururi ramase in joc
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * seteaza numarul de tururi ramase in joc
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * returneaza datele primite in fisierele de input
     */
    public Data getInitialData() {
        return data;
    }

    /**
     * seteaza datele primite
     */
    public void setInitialData(final Data initialData) {
        this.data = initialData;
    }

    /**
     * returneaza modificarile lunare
     */
    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     * seteaza modificarile lunare
     */
    public void setMonthlyUpdates(final List<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     * simuleaza o luna cu datele primite, incepand cu luna urmatoare
     */
    private void simulateNextMonth() {
        for (Consumer c : data.getConsumers()) {
            if (!c.isBankrupt()) {
                c.addMonthlyIncome();
                if (!c.hasDistributor()) {
                    c.pickDistributor(data.getDistributors());
                }
                c.payBill();
                c.decreaseContractTime();
            } else {
                for (Distributor d : data.getDistributors()) {
                    d.removeContract(c);
                }
            }
        }

        for (Distributor d : data.getDistributors()) {
            if (!d.isBankrupt()) {
                d.payCosts();
            }
        }
    }

    /**
     * simuleaza datele dintr-o luna
     */
    public void simulate() {
        for (int turn = 0; turn < numberOfTurns + 1; ++turn) {
            if (turn > 0) {
                for (Consumer c : monthlyUpdates.get(turn - 1).getNewConsumers()) {
                    data.addConsumer(c);
                }
                for (Distributor d : monthlyUpdates.get(turn - 1).getCostsChanges()) {
                    data.updateDistributorCosts(d);
                }
            }
            //simulateNextMonth();
        }
    }

}
