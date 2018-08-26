package generetics;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserStoreTest {

    Store<User> userStore = new UserStore();

    @Before
    public void setup() {
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.add(new User("3"));
        userStore.add(new User("4"));
        userStore.add(new User("5"));
    }

    @Test
    public void whenTestAddAndFindByIdMethod() {
        Assert.assertThat(userStore.findById("1").getId(), Is.is("1"));
        Assert.assertThat(userStore.findById("2").getId(), Is.is("2"));
        Assert.assertThat(userStore.findById("3").getId(), Is.is("3"));
        Assert.assertThat(userStore.findById("4").getId(), Is.is("4"));
        Assert.assertThat(userStore.findById("5").getId(), Is.is("5"));
    }

    @Test
    public void whenTryFindNoExistElementThenReturnNull() {
        Assert.assertThat(userStore.findById("6"), Is.is((User) null));
    }

    @Test
    public void whenReplaceElementWithExistIdMustReturnTrue() {
        Assert.assertThat(userStore.replace("3", new User("10")), Is.is(true));
        Assert.assertThat(userStore.findById("10").getId(), Is.is("10"));
    }

    @Test
    public void whenReplaceElementWithNotExistIdMustReturnTrue() {
        Assert.assertThat(userStore.replace("6", new User("10")), Is.is(false));
    }

    @Test
    public void whenDeleteElementWithExistIdMustReturnTrue() {
        Assert.assertThat(userStore.delete("3"), Is.is(true));
        Assert.assertThat(userStore.findById("3"), Is.is((User) null));
    }

    @Test
    public void whenDeleteElementWithNotExistIdMustReturnTrue() {
        Assert.assertThat(userStore.delete("6"), Is.is(false));
    }
}

