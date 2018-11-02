package additionalquestions;

import org.junit.Test;

public class StringWriterTest {
    @Test
    public void stringWriterTest() {
        StringPresentation stringPresentation = new StringPresentation();
        Thread thread1 = new Thread(new StringWriter(stringPresentation, 1));
        Thread thread2 = new Thread(new StringWriter(stringPresentation, 2));

        thread1.start();
        thread2.start();
    }
}