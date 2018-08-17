package ru.job4j.department;


import ru.job4j.departaments.Departament;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Department {

    /**Store*/

    private LinkedHashMap<String, TreeSet<String>> departments = new LinkedHashMap<>();

    /**getter Method*/

    public LinkedHashMap<String, TreeSet<String>> getDepartment() {
        return this.departments;
    }

    /**
     * add.
     * add new department with it's above departments.
     * @param departament department code.
     * */

    public void add(String departament) {
        int indexOfFirtstSlash = departament.indexOf("\\");
        String key = (indexOfFirtstSlash == -1) ? departament : departament.substring(0, indexOfFirtstSlash);
        if (!this.departments.containsKey(key)) {
            this.departments.put(key, new TreeSet<>());
        }
        if (indexOfFirtstSlash != -1) {
            this.departments.get(key).addAll(new Departament().divideOnSubDepartments(departament));
        }
    }

    /**
     * ascendingSort.
     * sort according to ascending order.
     * */

    public void ascendingSort() {
        this.departments = new LinkedHashMap<>(new TreeMap<>(this.departments));
    }

    /**
     * descendingSort.
     * sort according to descending order.
     * */

    public void descendingSort() {
        TreeMap<String, TreeSet<String>> temp = new TreeMap<>(Comparator.reverseOrder());
        temp.putAll(this.departments);
        this.departments = new LinkedHashMap<>(temp);
    }

}
