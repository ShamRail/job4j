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
	
	public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
		try (InputStream input = in;
			 OutputStream output = out) {
			HashSet<String> abusesSet = new HashSet<String>(Arrays.asList(abuse));
			StringBuilder currentWord = new StringBuilder();
			int currentByte;
			char currentCharacter;
			do {
				currentByte = input.read();
				currentCharacter = (char) currentByte;
				if (endOfWordOrText(currentByte)) {
					writeIfNotContains(abusesSet, currentWord.toString(), out);
					writeSpliter(currentByte, out);
					currentWord = new StringBuilder();
				} else {
					currentWord.append(currentCharacter);
				}
			} while (currentByte != -1);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean endOfWordOrText(int readByte) {
		return ((char) readByte == ' ') || (readByte == -1);
	}
	
	private void writeIfNotContains(HashSet<String> abuseWords, String word, OutputStream out) throws IOException {
		if (!abuseWords.contains(word)) {
			out.write(word.getBytes());
		}
	}
	
	private void writeSpliter(int readByte, OutputStream out) throws IOException {
		if (readByte != -1) {
			out.write(readByte);
		}
	}
}