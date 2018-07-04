package ru.job4j.calculate;

import  org.junit.Test;
import  org.hamcrest.core.Is.is;
import  org.junit.Assert.assertThat;



public class CalculateTest {
	@Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "Petr Arsentev";
		String expect = "Echo, echo, echo : Petr Arsentev"; 
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}