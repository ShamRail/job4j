package inout;
import java.io.*;
import java.lang.*;

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
	
}