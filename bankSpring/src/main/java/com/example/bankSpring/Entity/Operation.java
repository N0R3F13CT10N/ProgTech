package com.example.bankSpring.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "operation")
public class Operation implements Comparable<Operation> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Integer id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "accCode", nullable = false)
    private String accCode;

    @ManyToOne
    @JoinColumn(name="accFrom")
    private Account accFrom;

    @ManyToOne
    @JoinColumn(name="accTo", nullable=false)
    private Account accTo;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @Column(name = "sumBefore", nullable = false)
    private BigDecimal sumBefore;

    @Column(name = "sumAfter", nullable = false)
    private BigDecimal sumAfter;

    public Operation() {
    }

    public Operation(Integer id, Date date, String accCode, Account accFrom, Account accTo, BigDecimal sum, BigDecimal sumBefore, BigDecimal sumAfter) {
        this.id = id;
        this.date = date.toString();
        this.accCode = accCode;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
        this.sumBefore = sumBefore;
        this.sumAfter = sumAfter;
    }

    public Operation(Date date, String accCode, Account accTo, BigDecimal sum, BigDecimal sumBefore, BigDecimal sumAfter) {
        this.date = date.toString();
        this.accCode = accCode;
        this.accTo = accTo;
        this.sum = sum;
        this.sumBefore = sumBefore;
        this.sumAfter = sumAfter;
    }

    public Operation(Date date, String accCode, Account accFrom, Account accTo, BigDecimal sum, BigDecimal sumBefore, BigDecimal sumAfter) {
        this.date = date.toString();
        this.accCode = accCode;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
        this.sumBefore = sumBefore;
        this.sumAfter = sumAfter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        return  simpleDateFormat.parse(date);
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public Account getAccFrom() {
        return accFrom;
    }

    public void setAccFrom(Account accFrom) {
        this.accFrom = accFrom;
    }

    public Account getAccTo() {
        return accTo;
    }

    public void setAccTo(Account accTo) {
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


    @Override
    public int compareTo(Operation o) {
        try {
            return getDate().compareTo(o.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
