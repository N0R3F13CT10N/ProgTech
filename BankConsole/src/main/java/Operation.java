import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Operation {
    private Integer id;
    private Date date;
    private String accCode;
    private UUID accFrom;
    private UUID accTo;
    private BigDecimal sum;
    private BigDecimal sumBefore;
    private BigDecimal sumAfter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Operation(Integer id, Date date, String accCode, UUID accFrom, UUID accTo, BigDecimal sum, BigDecimal sumBefore, BigDecimal sumAfter) {
        this.id = id;
        this.date = date;
        this.accCode = accCode;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
        this.sumBefore = sumBefore;
        this.sumAfter = sumAfter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public UUID getAccFrom() {
        return accFrom;
    }

    public void setAccFrom(UUID accFrom) {
        this.accFrom = accFrom;
    }

    public UUID getAccTo() {
        return accTo;
    }

    public void setAccTo(UUID accTo) {
        this.accTo = accTo;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSumBefore() {
        return sumBefore;
    }

    public void setSumBefore(BigDecimal sumBefore) {
        this.sumBefore = sumBefore;
    }

    public BigDecimal getSumAfter() {
        return sumAfter;
    }

    public void setSumAfter(BigDecimal sumAfter) {
        this.sumAfter = sumAfter;
    }

    public Operation(Date date, String accCode, UUID accFrom, UUID accTo, BigDecimal sum, BigDecimal sumBefore, BigDecimal sumAfter) {
        this.date = date;
        this.accCode = accCode;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
        this.sumBefore = sumBefore;
        this.sumAfter = sumAfter;
    }
}
