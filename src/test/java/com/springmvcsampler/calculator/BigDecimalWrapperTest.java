package com.springmvcsampler.calculator;

import com.springmvcsampler.model.BigDecimalWrapper;
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
public class BigDecimalWrapperTest {

    @Test
    public void shouldMultiply() {
        BigDecimalWrapper v = new BigDecimalWrapper(10).multiply(BigDecimal.ZERO);
        assertThat(v).isEqualTo(new BigDecimal(10));
    }

    @Test
    public void shouldDivide() {
        BigDecimalWrapper v = new BigDecimalWrapper(10).divide(BigDecimal.ZERO);
        assertThat(v).isEqualTo(new BigDecimal(10));
    }


    @Test
    public void doubleBroken() {

        BigDecimal bd = BigDecimal.valueOf(0.02d);
        BigDecimal clone = new BigDecimal(bd.doubleValue());
        System.out.println(bd);
        System.out.println(clone);
        System.out.println(bd.equals(clone));
        assertThat(bd).isNotEqualTo(clone);

    }

    @Test
    public void multiply_2d_with_3d() {
        BigDecimalWrapper v = new BigDecimalWrapper(2d).multiply(BigDecimal.valueOf(3d));
        assertThat(v).isEqualTo(new BigDecimal(2d).multiply(BigDecimal.valueOf(3d)));
    }




}
