package synchronizy;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserStorageTest {

    private final class UserStorageThread implements Runnable {

        private final UserStorage userStorage;
        private int rangeBeginning;
        private int rangEnding;

        public UserStorageThread(UserStorage userStorage, int rangeBeginning, int rangeEnding) {
            this.userStorage = userStorage;
            this.rangeBeginning = rangeBeginning;
            this.rangEnding = rangeEnding;
        }

        @Override
        public void run() {
            for (int i = rangeBeginning; i <= rangEnding; i++) {
                userStorage.add(new User(i, i * 100));
            }
            userStorage.transfer(rangEnding, rangeBeginning, 100);
        }
    }

    UserStorage userStorage = new UserStorage();
    UserStorageThread userStorageThread1 = new UserStorageThread(userStorage, 1, 3);
    UserStorageThread userStorageThread2 = new UserStorageThread(userStorage, 4, 5);

    Thread thread1;
    Thread thread2;

    @Before
    public void setUp() throws InterruptedException {
        thread1 = new Thread(userStorageThread1);
        thread2 = new Thread(userStorageThread2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
    @Test
    public void whenAfterThreadProcessingThenSizeMustBe5() {
        Assert.assertThat(userStorage.getCount(), Is.is(5));
    }

    @Test
    public void whenTestGetByIdMethod() {
        Assert.assertThat(userStorage.getById(1), Is.is(200));
        Assert.assertThat(userStorage.getById(2), Is.is(200));
        Assert.assertThat(userStorage.getById(3), Is.is(200));
        Assert.assertThat(userStorage.getById(4), Is.is(500));
        Assert.assertThat(userStorage.getById(5), Is.is(400));
        Assert.assertThat(userStorage.getById(6), Is.is(-1));
    }

    @Test
    public void whenAddNewUserThenReturnTrue() {
        Assert.assertThat(userStorage.add(new User(6, 600)), Is.is(true));
    }

    @Test
    public void whenUpdateUserThenReturnTrue() {
        Assert.assertThat(userStorage.update(new User(3, 3000)), Is.is(true));
        Assert.assertThat(userStorage.getById(3), Is.is(3000));
    }

    @Test
    public void whenDeleteUserThenReturnTrue() {
        Assert.assertThat(userStorage.delete(new User(3, 200)), Is.is(true));
        Assert.assertThat(userStorage.getById(3), Is.is(-1));
    }

    @Test
    public void whenTransferThenChangesAmounts() {
        Assert.assertThat(userStorage.getById(1), Is.is(200));
        Assert.assertThat(userStorage.getById(5), Is.is(400));
        Assert.assertThat(userStorage.transfer(5, 1, 400), Is.is(true));
        Assert.assertThat(userStorage.getById(1), Is.is(600));
        Assert.assertThat(userStorage.getById(5), Is.is(0));
    }
}