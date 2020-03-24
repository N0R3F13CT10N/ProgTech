import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID uuid;
    private Integer clientId;
    private BigDecimal amount;

    public Account(UUID uuid, Integer clientId, BigDecimal amount, String accCode) {
        this.uuid = uuid;
        this.clientId = clientId;
        this.amount = amount;
        this.accCode = accCode;
    }

    private String accCode;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }
}
