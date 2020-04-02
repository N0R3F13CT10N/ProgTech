package com.example.bankSpring.Entity;

import com.example.bankSpring.Config.Converter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private User user;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "accCode", nullable = false)
    private String accCode;

    @OneToMany(mappedBy="accFrom")
    private Set<Operation> opsFrom;

    @OneToMany(mappedBy="accTo")
    private Set<Operation> opsTo;

    public Set<Operation> getOpsFrom() {
        return opsFrom;
    }

    public void setOpsFrom(Set<Operation> opsFrom) {
        this.opsFrom = opsFrom;
    }

    public Set<Operation> getOpsTo() {
        return opsTo;
    }

    public void setOpsTo(Set<Operation> opsTo) {
        this.opsTo = opsTo;
    }

    public Account(UUID uuid, User user, BigDecimal amount, String accCode) {
        this.uuid = uuid;
        this.user = user;
        this.amount = amount;
        this.accCode = accCode;
    }


    public Account() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String toString() {
        return String.format(
                "%s %s",
                uuid.toString(), accCode);
    }

    public void withdraw(Double sum, String currency){
        this.setAmount(this.getAmount().subtract(Converter.convert(BigDecimal.valueOf(sum), currency, this.getAccCode())));
    }

    public void deposit(Double sum, String currency){
        this.setAmount(this.getAmount().add(Converter.convert(BigDecimal.valueOf(sum), currency, this.getAccCode())));
    }
}
