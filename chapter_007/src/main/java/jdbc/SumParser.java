package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;

/**
 * SumParser.
 * Calculate average value source file elements.
 * */

public class SumParser {

    private static final Logger LOG = LogManager.getLogger(SumParser.class.getName());

    /**Sum of element's*/
    private int sum = 0;
    /**Count of element's*/
    private int count = 0;
    /**
     * getAverageSum.
     * @param source - source file.
     * @return average value of element's.
     * */
    public int getAverageSum(File source) {
        LOG.debug("Calculation begins ...");
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(source, new DefaultHandler(){
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equals("entry")) {
                        sum += Integer.parseInt(attributes.getValue("field"));
                        count++;
                    }
                }
            });
            LOG.debug("Sum and count have found.");
        } catch (Exception e) {
            LOG.error(String.format("%s occurs.", e.getClass().getSimpleName()));
        }
        return (count != 0) ? sum / count : -1;
    }
}
