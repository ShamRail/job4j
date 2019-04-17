package arrays;

import java.util.Scanner;

public class Task {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int result = 0;
        for (int l = 1; l <= n / 2; l++) {
            if ((n - l) % l == 0) {
                result++;
            }
        }
        System.out.println(result);
    }
}
