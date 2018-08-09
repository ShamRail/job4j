package ru.job4j.departaments;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Department.
 * Class use Map to store codes of departaments.
 * Mapping according to : code of high department(key) - code's of subdepartments(value's).
 * @version 1.0.
 * @since 09.08.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Departament {
    /**
     * Store of departments.
     * */
    private LinkedHashMap<String, TreeSet<String>> departaments = new LinkedHashMap<>();

    /**
     * getterMethod.
     * */

    public LinkedHashMap<String, TreeSet<String>> getDepartaments() {
        return this.departaments;
    }

    /**
     * divideOnSubDepartments.
     * Divide code of department to the TreeSet of all above department's code's.
     * @param departament code of adding department.
     * @param code's of all above department's.
     * */

    public TreeSet<String> divideOnSubDepartments(String departament) {
        TreeSet<String> result = new TreeSet<>();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < departament.length(); i++) {
            if (departament.charAt(i) == '\\') {
                result.add(stringBuffer.toString());
            }
            stringBuffer.append(departament.charAt(i));
        }
        result.add(departament);
        result.remove(result.first());
        return result;
    }

    /**
     * add.
     * Method add department code to the store
     * and add above department codes if it not exist's.
     * @param departament - new department code.
     * */

    public void add(String departament) {
        int indexOfFirtstSlash = departament.indexOf("\\");
        String key = (indexOfFirtstSlash == -1) ? departament : departament.substring(0, indexOfFirtstSlash);
        if (!this.departaments.containsKey(key)) {
            this.departaments.put(key, new TreeSet<>());
        }
        if (indexOfFirtstSlash != -1) {
            this.departaments.get(key).addAll(this.divideOnSubDepartments(departament));
        }
    }

    /**
     * ascendingSort.
     * sorting according to Natural order.
     * */

    public void ascendingSort() {
        this.departaments = this.departaments.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * ascendingSort.
     * sorting according to Reverse order.
     * */

    public void descengingSort() {
        this.departaments = this.departaments.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
