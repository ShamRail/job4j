package combinatorics;

import java.util.Scanner;

public class ArrayPermutation {

    static int num = 1;

    static void swap(int[] arr, int i, int j) {
        int s = arr[i];
        arr[i] = arr[j];
        arr[j] = s;
    }

    static boolean nextSet(int[] arr) {
        boolean result = true;
        int j = arr.length - 2;
        while (j != -1 && arr[j] >= arr[j + 1]) {
            j--;
        }
        if (j == -1) {
            result = false;
        }
        if (result) {
            int k = arr.length - 1;
            while (arr[j] >= arr[k]) {
                k--;
            }
            swap(arr, j, k);
            int l = j + 1;
            int r = arr.length - 1;
            while (l < r) {
                swap(arr, l++, r--);
            }
        }
        return result;
    }
    static void print(int[] arr) {
        System.out.print(String.format("%d: ", num++));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(String.format("%d ", arr[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("N = ");
        n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        print(arr);
        while (nextSet(arr)) {
            print(arr);
        }
    }

}
