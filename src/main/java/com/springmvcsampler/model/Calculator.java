package com.springmvcsampler.model;

import java.math.BigDecimal;

/**
 * Created by atheedom on 24/07/15.
 */
public class Calculator {

    private final BigDecimal bigDecimal;

    public Calculator(double num) {
        bigDecimal = new BigDecimal(num);
    }

    public Calculator(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Calculator multiply(BigDecimal augend) {
        if (augend.compareTo(BigDecimal.ZERO) != 0) {
            return new Calculator(bigDecimal.multiply(augend));
        } else {
            return this;
        }
    }

    public Calculator divide(BigDecimal augend) {
        if (augend.compareTo(BigDecimal.ZERO) != 0) {
            return new Calculator(bigDecimal.divide(augend));
        } else {
            return this;
        }
    }

    public BigDecimal bigDecimalValue() {
        return bigDecimal;
    }


}
