package trackerusage;

import ru.job4j.tracker.*;
import java.util.Scanner;

/*
* Для анализа была использована программа jconsole.
* По ней видно:
* - состояние хипа, его пулов и генераций
* - кол-во работающих потоков
* - кол-во используемых классов
* - использование процессора
* - информация о среде запуска
* - информация о состоянии виртуальной машины
*
* До вылета OutOfMemoryException происходит переполнение OldGeneration.
* */

public class TrackerUsage {

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        for (int i = 0; i < 1000; i++) {
            tracker.add(new Item(Integer.toString(i), Integer.toString(i)));
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        for (int i = 1001; i < 10000; i++) {
            tracker.add(new Item(Integer.toString(i), Integer.toString(i)));
        }
    }

}
