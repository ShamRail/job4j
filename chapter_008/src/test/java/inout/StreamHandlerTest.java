package inout;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import java.io.*;

public class StreamHandlerTest {
	
	@Test
	public void whenValidSequenceAndNumberIsEvenThenReturnTrue() {
		String input = "124";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		StreamHandler streamHandler = new StreamHandler();
		assertThat(streamHandler.isNumber(inputStream), is(true));
	}
	
	@Test
	public void whenValidSequenceAndNumberIsNotEvenThenReturnFalse() {
		String input = "123";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		StreamHandler streamHandler = new StreamHandler();
		assertThat(streamHandler.isNumber(inputStream), is(false));
	}
	
	@Test
	public void whenIsNotValidSequenceThenReturnFalse() {
		String input = "123b";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		StreamHandler streamHandler = new StreamHandler();
		assertThat(streamHandler.isNumber(inputStream), is(false));
	}
}