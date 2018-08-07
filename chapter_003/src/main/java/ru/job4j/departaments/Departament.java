package ru.job4j.departaments;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Department.
 * Class use Set to store codes of departaments, because it can't be equal.
 * @version 1.0.
 * @since 07.08.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Departament {
    /**
     * Store of departments.
     * */
    private TreeSet<String> departaments = new TreeSet<>();

    /**
     * getterMethod.
     * */

    public TreeSet<String> getDepartaments() {
        return this.departaments;
    }

    /**
     * add.
     * Method add department code to the store
     * and add above department codes if it not exist's.
     * @param departament - new department code.
     * */

    public void add(String departament) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < departament.length(); i++) {
            if (departament.charAt(i) == '\\' && !this.departaments.contains(stringBuffer.toString())) {
                this.departaments.add(stringBuffer.toString());
            }
            stringBuffer.append(departament.charAt(i));
        }
        this.departaments.add(departament);
    }

    /**
     * ascendingSort.
     * We not need to implement ascending sort apart, because
     * TreeSet use it by default (natural order).
     * However, if we need to change our store, we must realize it.
     * @return ascending sorted store.
     * */

    public TreeSet<String> ascendingSort() {
        TreeSet<String> result = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        result.addAll(this.departaments);
        this.departaments = result;
        return result;
    }

    /**
     * descendingSort.
     * To save hierarchy of the store it is sorted only by highest code's of departaments.
     * So, we need ignore symbols after last "K" (we can do it, because  every code has "K")
     * in code's except in highest. At first, input strings in param of comparator is checking on equality to highest code
     * (highest code can't contain "\"). If both strings is highest we reverse order(natural order by default).
     * At last, we find length of comparing part. If both strings are not highest length will be minimum of it's
     * last indexes of last "K". On other hand, if one of strings is highest(or both) we must't cut length to compare.
     * At last, strings are compared symbols by symbols till length.
     * If result = -1, first string is previous by second, else next by second.
     * @return descending sorted store.
     * */

    public TreeSet<String> descendingSort() {
        TreeSet<String> result = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 1;
                if (!o1.contains("\\") && !o2.contains("\\")) {
                    result = -1;
                } else {
                    int lenght = (!o1.contains("\\") || !o2.contains("\\"))
                            ? Math.min(o1.length(), o2.length()) : Math.min(o1.lastIndexOf("K"), o2.lastIndexOf("K")) + 1;
                    for (int i = 0; i < lenght; i++) {
                        if (o1.charAt(i) > o2.charAt(i)) {
                            result = -1;
                        }
                    }
                }
                return result;
            }
        });
        result.addAll(this.departaments);
        this.departaments = result;
        return result;
    }
}
