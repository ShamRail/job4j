package jdbc;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;

public class SumParserTest {

    @Test
    public void whenInsertTenNumberAndSaveThenMustReturnFive() {
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generate(10);
        File file = new File("entries.xml");
        StoreXML storeXML = new StoreXML(file);
        storeXML.save(storeSQL.getEntriesList());

        Assert.assertThat(new SumParser().getAverageSum(file), Is.is(5));
    }
}