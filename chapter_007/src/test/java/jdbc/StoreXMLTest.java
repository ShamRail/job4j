package jdbc;

import org.junit.Test;
import java.io.File;

public class StoreXMLTest {

    @Test
    public void whenTrySaveListOfEntriesThenMustSavedSuccessfully() {
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generate(3);
        StoreXML storeXML = new StoreXML(new File("entries.xml"));
        storeXML.save(storeSQL.getEntriesList());
    }

}