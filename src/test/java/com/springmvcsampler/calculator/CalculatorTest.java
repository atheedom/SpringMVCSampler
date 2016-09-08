package com.springmvcsampler.calculator;

import com.springmvcsampler.model.Calculator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by atheedom on 25/07/15.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    private static BigDecimal FIVE = new BigDecimal(5);
    private static BigDecimal TWO = new BigDecimal(2);
    private static BigDecimal TEN = BigDecimal.TEN;
    private static BigDecimal HUNDRED = new BigDecimal(100);
    private static BigDecimal TWENTY_FIVE = new BigDecimal(25);

    @Test
    public void shouldMultiply() {
        Calculator result = new Calculator(10).multiply(TEN);
        assertThat(result.bigDecimalValue()).isEqualTo(HUNDRED);
    }

    @Test
    public void shouldMultiplyByZero() {
        Calculator result = new Calculator(10).multiply(BigDecimal.ZERO);
        assertThat(result.bigDecimalValue()).isEqualTo(TEN);
    }

    @Test
    public void shouldDivideByZero() {
        Calculator result = new Calculator(10).divide(BigDecimal.ZERO);
        assertThat(result.bigDecimalValue()).isEqualTo(TEN);
    }

    @Test
    public void shouldDivide() {
        Calculator result = new Calculator(10).divide(FIVE);
        assertThat(result.bigDecimalValue()).isEqualTo(TWO);
    }

    @Test
    public void shouldMultiplyAndDivide(){
        Calculator result = new Calculator(10).multiply(FIVE).divide(TWO);
        assertThat(result.bigDecimalValue()).isEqualTo(TWENTY_FIVE);
    }

    @Test
    public void multiply_2d_by_3d() {
        Calculator result = new Calculator(2d).multiply(BigDecimal.valueOf(3d));
        assertThat(result.bigDecimalValue()).isEqualTo(new BigDecimal(2d).multiply(BigDecimal.valueOf(3d)));
    }
}
