package list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;


public class ConcurrentSimpleArrayListTest {

    private class WritingThread implements Runnable {

        private ConcurrentSimpleArrayList<Integer> arrayList;
        private int[] data;

        public WritingThread(ConcurrentSimpleArrayList<Integer> arrayList, int[] data) {
            this.arrayList = arrayList;
            this.data = data;
        }

        @Override
        public void run() {
            for (int aData : data) {
                arrayList.add(aData);
            }
        }
    }

    @Test
    public void whenTwoThreadsAddDataThenAllDataMustInList() throws InterruptedException {
        ConcurrentSimpleArrayList<Integer> arrayList = new ConcurrentSimpleArrayList<>();
        int[] dataFirst = {1, 2, 3};
        int[] dataSecond = {4, 5};
        Thread thread1 = new Thread(new WritingThread(arrayList, dataFirst));
        Thread thread2 = new Thread(new WritingThread(arrayList, dataSecond));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(arrayList.size(), Is.is(5));
    }

    @Test
    public void whenAddDataAfterCreatingIteratorThenMustNotBeException() {
        ConcurrentSimpleArrayList<Integer> arrayList = new ConcurrentSimpleArrayList<>();
        arrayList.add(1);
        Iterator<Integer> integerIterator = arrayList.iterator();
        arrayList.add(3);
        integerIterator.next();
    }

}