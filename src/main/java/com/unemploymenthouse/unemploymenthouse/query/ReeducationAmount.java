package com.unemploymenthouse.unemploymenthouse.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReeducationAmount {
    private String instit;
    private Long amount;

    public ReeducationAmount(String instit, Long amount) {
        this.instit = instit;
        this.amount = amount;
    }
}
