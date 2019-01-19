package inout;
import java.io.*;
import java.lang.*;
import java.util.*;

public class StreamHandler {
	
	public boolean isNumber(InputStream in) {
		boolean result = true;
		char currentSymbol;
		int inputCharacter;
		StringBuilder outNumber = new StringBuilder();
		try (InputStream stream = in) {
			while ((inputCharacter = stream.read()) != -1) {
				currentSymbol = (char) inputCharacter;
				if (currentSymbol < '0' || currentSymbol > '9') {
					result = false;
					break;
				}
				outNumber.append(currentSymbol);
			}
		} catch (Exception ex) {
			result = false;
		}
		return (!result) ? result : (Integer.valueOf(outNumber.toString()) % 2 == 0);
	}
	
	/**
	 * dropAbuses(InputStream in, OutputStream out, String[] abuse). 
	 * Write input stream into output stream without abuse words.
	 * It is assumed that stream has only one spliter - space.
	 * All spliters are also going to write.
	 *
	*/

	public void dropAbuses(Reader input, Writer output, String[] abuse) {
		try (BufferedReader in = new BufferedReader(input);
			BufferedWriter out = new BufferedWriter((output))) {
			Set<String> abuses = new HashSet<>(Arrays.asList(abuse));
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				Arrays.stream(currentLine.trim().split("\\s+"))
						.filter((word) -> !(abuses.contains(word))).forEach(
						(word) -> writeWord(word, out)
				);
				out.write(System.lineSeparator());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void writeWord(String word, Writer out) {
		try {
			out.write(word);
			out.write(" ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}