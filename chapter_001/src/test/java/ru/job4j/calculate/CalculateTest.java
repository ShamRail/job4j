package ru.job4j.calculate;

import  org.junit.Test;
import  org.hamcrest.core.Is;
import  org.junit.Assert;



public class CalculateTest {
	@Test
	public void whenOneAddToOnethenResultTwo() {
		Calculate calc = new Calculate();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expect = 2D;
		Assert.assertThat(result, Is.is(expect));
	}
	@Test
	public void whenOneSubOnethenResultZero() {
		Calculate calc = new Calculate();
		calc.sub(1D, 1D);
		double result = calc.getResult();
		double expect = 0D;
		Assert.assertThat(result, Is.is(expect));
	}
	@Test
	public void whenOneMultTwothenResultTwo() {
		Calculate calc = new Calculate();
		calc.mult(1D, 2D);
		double result = calc.getResult();
		double expect = 2D;
		Assert.assertThat(result, Is.is(expect));
	}
	@Test
	public void whenTwoDivTwothenResultOne() {
		Calculate calc = new Calculate();
		calc.div(2D, 2D);
		double result = calc.getResult();
		double expect = 1D;
		Assert.assertThat(result, Is.is(expect));
	}
}
