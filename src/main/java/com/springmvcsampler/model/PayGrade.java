package com.springmvcsampler.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by developer on 21/07/2015.
 */
@Entity
@Table(name = "PayGrades")
public class PayGrade extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String grade;

    @Column(unique = true, nullable = false)
    private BigDecimal pay;

    public PayGrade() {}


    public PayGrade(String grade, BigDecimal pay) {
        this.grade = grade;
        this.pay = pay;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayGrade payGrade = (PayGrade) o;
        return Objects.equals(grade, payGrade.grade) &&
                Objects.equals(pay, payGrade.pay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, pay);
    }
}
