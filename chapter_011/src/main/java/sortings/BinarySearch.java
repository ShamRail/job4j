package sortings;

public class BinarySearch {

    private Integer[] data;

    public BinarySearch(Integer[] data) {
        this.data = data;
    }

    public int findIndex(int value) {
        sort();
        int result = -1;
        int left = 0;
        int right = data.length - 1;
        int mid;
        int midElem;
        while (left <= right) {
            mid = (left + right) / 2;
            midElem = data[mid];
            if (midElem == value) {
                result = mid;
                break;
            }
            if (value < midElem) {
                right = midElem - 1;
            }
            if (value > midElem) {
                left = midElem + 1;
            }
        }
        return result;
    }

    private void sort() {
        new SelectionSort().sort(data);
    }

}
