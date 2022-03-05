import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = DistributorSerializer.class)
public class Distributor {
    public static final double PROFIT = 0.2;
    private int id;
    private int contractLength;
    @JsonAlias({ "initialBudget", "budget" })
    private int budget;
    @JsonAlias({ "initialInfrastructureCost", "infrastructureCost" })
    private int infrastructureCost;
    @JsonAlias({ "initialProductionCost", "productionCost" })
    private int productionCost;
    private final List<Contract> contracts = new ArrayList<>();
    private final List<Data> data = new ArrayList<>();
    private boolean isBankrupt = false;

    /**
     * adauga in bugetul unui consumator pretul unei facturi
     */
    public void acceptConsumerPayment(final long payment) {
        budget += payment;
    }

    /**
     * returneaza pretul unui contract
     */
    public long getContractPrice(final int consumerId) {
        for (Contract c : contracts) {
            if (c.getConsumerId() == consumerId) {
                return c.getPrice();
            }
        }
        return 0;
    }

    /**
     * scade numarul de luni dintr-un contract
     */
    public void decreaseContractTime(final Consumer consumer) {
        for (Contract c : contracts) {
            if (c.getConsumerId() == consumer.getId()) {
                c.decreaseContractTime();
            }
        }
    }

    /**
     * verifica daca un contract a expirat
     */
    public boolean isContractExpired(final int consumerId) {
        for (Contract c : contracts) {
            if (c.getConsumerId() == consumerId) {
                if (c.getRemainedContractMonths() == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * plateste costul infrastructurii si de productie
     */
    public void payCosts() {
        int budgetBackup = budget;
        int toPay = infrastructureCost + contracts.size() * productionCost;
        boolean x = false;

        budget -= toPay;

        if (budget < 0) {
            budget = budgetBackup;
            isBankrupt = true;
            contracts.clear();
        }
    }

    /**
     * returneaza profitul unui distribuitor
     */
    private long getProfit() {
        return Math.round(Math.floor(PROFIT * productionCost));
    }

    /**
     * returneaza pretul contractului
     */
    public long getPrice() {
        return Math.round(Math.floor(infrastructureCost
                / (!contracts.isEmpty() ? contracts.size() : 1))
                + productionCost + getProfit());

    }

    /**
     * returneaza lista contractelor fiecarul distribuitor
     */
    public List<Contract> getContracts() {
        return contracts;
    }

    /**
     * adauga un consumator la un contract
     */
    public void addContract(final Consumer consumer) {
        contracts.add(new Contract(consumer.getId(), getPrice(), getContractLength()));
    }

    /**
     * scoate un consumator dintr-un contract
     */
    public void removeContract(final Consumer consumer) {
        Contract contractToRemove = null;
        for (Contract c : contracts) {
            if (c.getConsumerId() == consumer.getId()) {
                contractToRemove = c;
            }
        }

        if (contractToRemove != null) {
            contracts.remove(contractToRemove);
        }
    }

    /**
     * returneaza id-ul unui distribuitor
     */
    public int getId() {
        return id;
    }

    /**
     * seteaza id-ul unui distribuitor
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * returneaza lungimea unui contract
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     * seteaza lungimea unui contract
     */
    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * returneaza bugetul unui distribuitor
     */
    public int getBudget() {
        return budget;
    }

    /**
     * seteaza bugetul unui distribuitor
     */
    public void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * returneaza costul infrastructurii
     */
    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * seteaza costul infrastructurii
     */
    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * returneaza costul de productie
     */
    public int getProductionCost() {
        return productionCost;
    }

    /**
     * seteaza costul de productie
     */
    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * afiseaza datele unui distribuitor
     */
    @Override
    public String toString() {
        return "Distributor{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", budget=" + budget
                + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost
                + '}';
    }

    /**
     * verifica daca un distribuitor a intrat in faliment
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }
}
