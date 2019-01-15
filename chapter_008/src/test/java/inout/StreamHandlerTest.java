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
	
	@Test
	public void whenOneAbuseAndOneMatchWordThenItMustBeRemoved() {
		String input = "one two three";
		String[] abuses = {"two"};
		String expected = "one  three";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler streamHandler = new StreamHandler();
		streamHandler.dropAbuses(inputStream, outputStream, abuses);
		assertThat(outputStream.toString(), is(expected));
	}
	
	@Test
	public void whenOneAbuseAndSomeMatchesWordThenItMustBeRemoved() {
		String input = "one two three four five one two";
		String[] abuses = {"one"};
		String expected = " two three four five  two";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler streamHandler = new StreamHandler();
		streamHandler.dropAbuses(inputStream, outputStream, abuses);
		assertThat(outputStream.toString(), is(expected));
	}
	
	@Test
	public void whenSomeAbuseAndSomeMatchesWordThenItMustBeRemoved() {
		String input = "one two three four five one two";
		String[] abuses = {"one", "two"};
		String expected = "  three four five  ";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler streamHandler = new StreamHandler();
		streamHandler.dropAbuses(inputStream, outputStream, abuses);
		assertThat(outputStream.toString(), is(expected));
	}
	
	@Test
	public void whenStreamHasntMatchesThenOutputDoesntChange() {
		String input = "one two three four five one two";
		String[] abuses = {"seven", "nine"};
		String expected = "one two three four five one two";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler streamHandler = new StreamHandler();
		streamHandler.dropAbuses(inputStream, outputStream, abuses);
		assertThat(outputStream.toString(), is(expected));
	}
}