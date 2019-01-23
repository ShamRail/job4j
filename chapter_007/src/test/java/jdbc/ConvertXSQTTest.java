package jdbc;

import org.junit.Test;
import java.io.File;

public class ConvertXSQTTest {

    @Test
    public void whenTransformXMLThenMustBeFormattedProperlyBeXSL() {
        File source = new File("entries.xml");
        File target = new File("target_entries.xml");
        File scheme = new File("src/main/resources/scheme.xsl");
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generate(1000000);
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.getEntriesList());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(source, target, scheme);
    }

}