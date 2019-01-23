package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileReader;

/**
 * ConvertXSQT.
 * Convert XML to XML by XSL.
 * */

public class ConvertXSQT {

    private static final Logger LOG = LogManager.getLogger(ConvertXSQT.class.getName());

    /**
     * convert.
     * Convert XML to XML by XSL.
     * @param source - xml source file.
     * @param dest - xml destination file.
     * @param scheme - xls source file.
     * */
    public void convert(File source, File dest, File scheme) {
        try(FileReader fileReader = new FileReader(source)) {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            LOG.debug("XSL scheme has handled successfully.");
            transformer.transform(new StreamSource(fileReader), new StreamResult(dest));
            LOG.debug("Transformation has finished!");
        } catch (Exception e) {
            LOG.error(String.format("Transformation has fallen with %s", e.getClass().getSimpleName()));
            LOG.error(String.format("Exception message: %s", e.getMessage()));
        }
    }
}
