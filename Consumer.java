import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(using = ConsumerSerializer.class)
public class Consumer {
    public static final double PROCENT = 1.2;
    private int id;
    @JsonAlias({"initialBudget"})
    private int budget;
    private int monthlyIncome;
    private Distributor distributor = null;
    private boolean isBankrupt = false;
    private boolean payPostponed = false;
    private long missedPay = 0;


    /**
     * Consumatorul alege distribuitorul cu pretul cel mai mic
     */
    public void pickDistributor(final List<Distributor> distributors) {
        long minPrice = 0;
        int distributorIndex = 0;
        for (; distributorIndex < distributors.size(); ++distributorIndex) {
            if (!distributors.get(distributorIndex).isBankrupt()) {
                minPrice = distributors.get(distributorIndex).getPrice();
                distributor = distributors.get(distributorIndex);
            }
        }
        for (; distributorIndex < distributors.size(); ++distributorIndex) {
            Distributor d = distributors.get(distributorIndex);
            if (d.isBankrupt()) {
                continue;
            }
            if (d.getPrice() < minPrice) {
                distributor = d;
                minPrice = d.getPrice();
            }
        }
        distributor.addContract(this);
    }

    /**
     * verifica daca un consumator are distribuitor
     */
    public boolean hasDistributor() {
        if (distributor == null) {
            return false;
        } else if (distributor.isBankrupt()) {
            return false;
        } else if (distributor.isContractExpired(id)) {
            distributor.removeContract(this);
            return false;
        }
        return true;
    }

    /**
     * adauga venitul lunar
     */
    public void addMonthlyIncome() {
        budget += monthlyIncome;
    }

    /**
     * plateste factura lunara distribuitorului
     */
    public void payBill() {
        if (payPostponed) {
            long toPay = Math.round(Math.floor(missedPay * PROCENT)
                    + distributor.getContractPrice(id));
            if (budget - toPay < 0) {
                isBankrupt = true;
            } else {
                budget -= toPay;
                distributor.acceptConsumerPayment(toPay);
                payPostponed = false;
                missedPay = 0;
            }
            return;
        }

        if (budget - distributor.getContractPrice(id) < 0) {
            payPostponed = true;
            missedPay = distributor.getContractPrice(id);
        } else {
            budget -= distributor.getContractPrice(id);
            distributor.acceptConsumerPayment(distributor.getContractPrice(id));
        }
    }

    /**
     * Scade in fiecare luna durata contractului
     */
    public void decreaseContractTime() {
        distributor.decreaseContractTime(this);
    }

    /**
     * returneaza id-ul fiecarui consumator
     */
    public int getId() {
        return id;
    }

    /**
     * returneaza bugetul fiecarui consumator
     */
    public int getBudget() {
        return budget;
    }

    /**
     * returneaza venitul lunar al fiecarui distribuitor
     */
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * seteaza id-ul consumatorului
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * seteaza bugetul consumatorului
     */
    public void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * seteaza venitul lunar al distribuitorului
     */
    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * verifica daca un consumator a intrat in faliment
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }
}
