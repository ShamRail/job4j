package generetics;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleStoreTest {
    Store<Role> roleStore = new RoleStore();

    @Before
    public void setup() {
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));
        roleStore.add(new Role("4"));
        roleStore.add(new Role("5"));
    }

    @Test
    public void whenTestAddAndFindByIdMethod() {
        Assert.assertThat(roleStore.findById("1").getId(), Is.is("1"));
        Assert.assertThat(roleStore.findById("2").getId(), Is.is("2"));
        Assert.assertThat(roleStore.findById("3").getId(), Is.is("3"));
        Assert.assertThat(roleStore.findById("4").getId(), Is.is("4"));
        Assert.assertThat(roleStore.findById("5").getId(), Is.is("5"));
        Assert.assertThat(roleStore.findById("6"), Is.is((Role) null));
    }

    @Test
    public void whenTryFindNoExistElementThenReturnNull() {
        Assert.assertThat(roleStore.findById("6"), Is.is((Role) null));
    }

    @Test
    public void whenReplaceElementWithExistIdMustReturnTrue() {
        Assert.assertThat(roleStore.replace("3", new Role("10")), Is.is(true));
        Assert.assertThat(roleStore.findById("10").getId(), Is.is("10"));
    }

    @Test
    public void whenReplaceElementWithNotExistIdMustReturnTrue() {
        Assert.assertThat(roleStore.replace("6", new Role("10")), Is.is(false));
    }

    @Test
    public void whenDeleteElementWithExistIdMustReturnTrue() {
        Assert.assertThat(roleStore.delete("3"), Is.is(true));
        Assert.assertThat(roleStore.findById("3"), Is.is((Role) null));
    }

    @Test
    public void whenDeleteElementWithNotExistIdMustReturnTrue() {
        Assert.assertThat(roleStore.delete("6"), Is.is(false));
    }
}