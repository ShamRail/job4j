package additionalquestions;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class StoreTest {

    @Test
    public void whenDidNotOccurChangingThenAllInfoFieldsAreZero() {
        Store store = new Store();
        Store.User user1 = new Store.User(1, "Alex");
        Store.User user2 = new Store.User(2, "Max");

        List<Store.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);

        List<Store.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);

        Info info = store.getDifferenceInfo(previous, current);

        Assert.assertThat(info.getAddedCount(), Is.is(0));
        Assert.assertThat(info.getDeletedCount(), Is.is(0));
        Assert.assertThat(info.getChangedCount(), Is.is(0));
    }

    @Test
    public void whenOnlyDeleted() {
        Store store = new Store();
        Store.User user1 = new Store.User(1, "Alex");
        Store.User user2 = new Store.User(2, "Max");

        List<Store.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);

        List<Store.User> current = new ArrayList<>();
        current.add(user1);

        Info info = store.getDifferenceInfo(previous, current);

        Assert.assertThat(info.getAddedCount(), Is.is(0));
        Assert.assertThat(info.getDeletedCount(), Is.is(1));
        Assert.assertThat(info.getChangedCount(), Is.is(0));
    }

    @Test
    public void whenOnlyChanged() {
        Store store = new Store();
        Store.User user1 = new Store.User(1, "Alex");
        Store.User user2 = new Store.User(2, "Max");

        List<Store.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);

        List<Store.User> current = new ArrayList<>();
        current.add(user1);
        current.add(new Store.User(2, "Emily"));

        Info info = store.getDifferenceInfo(previous, current);

        Assert.assertThat(info.getAddedCount(), Is.is(0));
        Assert.assertThat(info.getDeletedCount(), Is.is(0));
        Assert.assertThat(info.getChangedCount(), Is.is(1));
    }

    @Test
    public void whenOnlyAdded() {
        Store store = new Store();
        Store.User user1 = new Store.User(1, "Alex");
        Store.User user2 = new Store.User(2, "Max");

        List<Store.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);

        List<Store.User> current = new ArrayList<>();
        current.add(user1);
        current.add(user2);
        current.add(new Store.User(3, "Emily"));

        Info info = store.getDifferenceInfo(previous, current);

        Assert.assertThat(info.getAddedCount(), Is.is(1));
        Assert.assertThat(info.getDeletedCount(), Is.is(0));
        Assert.assertThat(info.getChangedCount(), Is.is(0));
    }

    @Test
    public void whenOneAddedOneDeletedAndOneChanged() {
        Store store = new Store();
        Store.User user1 = new Store.User(1, "Alex");
        Store.User user2 = new Store.User(2, "Max");

        List<Store.User> previous = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        previous.add(new Store.User(3, "Marina"));

        List<Store.User> current = new ArrayList<>();
        current.add(user1);
        current.add(new Store.User(3, "Emily"));
        current.add(new Store.User(4, "Alina"));

        Info info = store.getDifferenceInfo(previous, current);

        Assert.assertThat(info.getAddedCount(), Is.is(1));
        Assert.assertThat(info.getDeletedCount(), Is.is(1));
        Assert.assertThat(info.getChangedCount(), Is.is(1));
    }


}