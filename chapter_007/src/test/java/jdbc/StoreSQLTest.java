package jdbc;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StoreSQLTest {
    @Test
    public void whenGenerateAndReturListThenListsMustBeEqual() {
        StoreSQL storeSQL = new StoreSQL();
        storeSQL.generate(3);
        List<Entry> result = storeSQL.getEntriesList();
        List<Entry> expected = new LinkedList<Entry>(Arrays.asList(new Entry(1), new Entry(2), new Entry(3)));
        Assert.assertThat(result, Is.is(expected));
    }
}