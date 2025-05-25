package com.unemploymenthouse.unemploymenthouse.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReeducationAmount {
    private String instit;
    private Long amount;

    public ReeducationAmount(String instit, Long amount) {
        this.instit = instit;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ReeducationAmount{" +
                "instit='" + instit + '\'' +
                ", amount=" + amount +
                '}';
    }
}
