package com.unemploymenthouse.unemploymenthouse.query;

public class ReeducationAmount {
    private String instit;
    private Long amount;

    public ReeducationAmount(String instit, Long amount) {
        this.instit = instit;
        this.amount = amount;
    }

    public String getInstit() {
        return instit;
    }

    public void setInstit(String instit) {
        this.instit = instit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
