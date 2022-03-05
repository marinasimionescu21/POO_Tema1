import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ContractSerializer.class)
public class Contract {
    private int consumerId;
    private long price;
    private int remainedContractMonths;

    Contract(final int consumerId, final long price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * scade numarul de luni din contract
     */
    public void decreaseContractTime() {
        --remainedContractMonths;
    }

    /**
     * returneaza id-ul consumatorului care apartine contractului
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     * seteaza id-ul consumatorului
     */
    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * returneaza pretul contractului
     */
    public long getPrice() {
        return price;
    }

    /**
     * seteaza pretul contractului
     */
    public void setPrice(final long price) {
        this.price = price;
    }

    /**
     * returneaza numarul de luni ramase in contract
     */
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * seteaza numarul de luni ramase in contract
     */
    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
