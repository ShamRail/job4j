package nonblocking;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;


public class BaseStorageTest {

    @Test
    public void whenAddAndGetMethodTest() {
        BaseStorage baseStorage = new BaseStorage();
        Base base1 = new Base(1, 1, "A");
        Base base2 = new Base(2, 1, "B");
        baseStorage.add(base1);
        baseStorage.add(base2);
        Assert.assertThat(baseStorage.getById(1).equals(base1), Is.is(true));
        Assert.assertThat(baseStorage.getById(2).equals(base2), Is.is(true));
        Assert.assertThat(baseStorage.getById(3), Is.is((Base) null));
    }

    @Test
    public void whenAddAndDeleteTest() {
        BaseStorage baseStorage = new BaseStorage();
        Base base1 = new Base(1, 1, "A");
        Base base2 = new Base(2, 1, "B");
        baseStorage.add(base1);
        baseStorage.add(base2);
        baseStorage.delete(base1);
        baseStorage.delete(base2);
        Assert.assertThat(baseStorage.getById(1), Is.is((Base) null));
        Assert.assertThat(baseStorage.getById(2), Is.is((Base) null));
    }

    @Test
    public void whenUpdateWithEqualVersionThenMustUpdate() {
        BaseStorage baseStorage = new BaseStorage();
        Base base1 = new Base(1, 1, "A");
        Base base2 = new Base(1, 1, "B");
        baseStorage.add(base1);
        baseStorage.add(base2);
        baseStorage.update(base2);
        Assert.assertThat(baseStorage.getById(1).getData(), Is.is("B"));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateWithUnEqualVersionThenMustBeException() {
        BaseStorage baseStorage = new BaseStorage();
        Base base1 = new Base(1, 1, "A");
        Base base2 = new Base(1, 2, "B");
        baseStorage.add(base1);
        baseStorage.add(base2);
        baseStorage.update(base2);
    }

    private class AddingThread implements Runnable {

        private BaseStorage baseStorage;

        public AddingThread(BaseStorage baseStorage) {
            this.baseStorage = baseStorage;
        }

        @Override
        public void run() {
            Base base1 = new Base(1, 1, "A");
            Base base2 = new Base(2, 1, "B");
            Base base3 = new Base(3, 1, "C");
            baseStorage.add(base1);
            baseStorage.add(base2);
            baseStorage.add(base3);
        }
    }

    private class CorrectUpdatingThread implements Runnable {

        private BaseStorage baseStorage;

        public CorrectUpdatingThread(BaseStorage baseStorage) {
            this.baseStorage = baseStorage;
        }

        @Override
        public void run() {
            Base base1 = new Base(1, 1, "X");
            Base base2 = new Base(2, 1, "Y");
            Base base3 = new Base(3, 1, "Z");
            baseStorage.update(base1);
            baseStorage.update(base2);
            baseStorage.update(base3);
        }
    }

    @Test
    public void whenStartAddingAndCorrectUpdatingThreadThenMustChangeStorage() throws InterruptedException {
        BaseStorage baseStorage = new BaseStorage();
        Thread threadForAdd = new Thread(new AddingThread(baseStorage));
        Thread threadForUpdate = new Thread(new CorrectUpdatingThread(baseStorage));
        threadForAdd.start();
        threadForUpdate.start();
        threadForAdd.join();
        threadForUpdate.join();
        Assert.assertThat(baseStorage.getById(1).getData(), Is.is("X"));
        Assert.assertThat(baseStorage.getById(2).getData(), Is.is("Y"));
        Assert.assertThat(baseStorage.getById(3).getData(), Is.is("Z"));
    }

    @Test
    public void whenStartAddingAndUnCorrectUpdatingThreadThenMustException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        BaseStorage baseStorage = new BaseStorage();
        Thread threadForAdd = new Thread(new AddingThread(baseStorage));
        Thread threadForUpdate = new Thread(() -> {
            try {
                Base base1 = new Base(1, 2, "X");
                baseStorage.update(base1);
            } catch (Exception e) {
                ex.set(e);
            }
        });
        threadForAdd.start();
        threadForUpdate.start();
        threadForAdd.join();
        threadForUpdate.join();
        Assert.assertThat(ex.get().getMessage(), Is.is("Optimistic error"));
    }

}