package com.springmvcsampler.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Created by atheedom on 25/07/15.
 */
public class BigDecimalWrapper extends BigDecimal {


    public BigDecimalWrapper multiply(BigDecimal augend){
        if(augend.compareTo(BigDecimal.ZERO) != 0){
            return new BigDecimalWrapper(super.multiply(augend).toString());
        } else {
            return this;
        }
    }

    public BigDecimalWrapper divide(BigDecimal augend){
        if(augend.compareTo(BigDecimal.ZERO) != 0){
            return new BigDecimalWrapper(super.divide(augend).toString());
        } else {
            return this;
        }
    }

    public BigDecimalWrapper add(BigDecimal augend){
        return new BigDecimalWrapper(super.add(augend).toString());
    }

    public BigDecimalWrapper subtract(BigDecimal augend){
        return new BigDecimalWrapper(super.subtract(augend).toString());
    }


    public BigDecimalWrapper(char[] in, int offset, int len) {
        super(in, offset, len);
    }

    public BigDecimalWrapper(char[] in, int offset, int len, MathContext mc) {
        super(in, offset, len, mc);
    }

    public BigDecimalWrapper(char[] in) {
        super(in);
    }

    public BigDecimalWrapper(char[] in, MathContext mc) {
        super(in, mc);
    }

    public BigDecimalWrapper(String val) {
        super(val);
    }

    public BigDecimalWrapper(String val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalWrapper(double val) {
        super(val);
    }

    public BigDecimalWrapper(double val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalWrapper(BigInteger val) {
        super(val);
    }

    public BigDecimalWrapper(BigInteger val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalWrapper(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public BigDecimalWrapper(BigInteger unscaledVal, int scale, MathContext mc) {
        super(unscaledVal, scale, mc);
    }

    public BigDecimalWrapper(int val) {
        super(val);
    }

    public BigDecimalWrapper(int val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimalWrapper(long val) {
        super(val);
    }

    public BigDecimalWrapper(long val, MathContext mc) {
        super(val, mc);
    }
}
