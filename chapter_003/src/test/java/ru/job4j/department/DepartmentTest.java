package ru.job4j.department;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class DepartmentTest {

    @Test
    public void whenAddingDeparmentThenAboveDepartamentsMustAddedToo() {
        Department departaments = new Department();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");

        LinkedHashMap<String, TreeSet<String>> result = departaments.getDepartment();
        TreeSet<String> subDepartmets = result.get("K1");

        StringBuilder firstAndLast = new StringBuilder();
        firstAndLast.append(subDepartmets.first());
        firstAndLast.append(subDepartmets.last());

        Assert.assertThat(firstAndLast.toString(), Is.is("K1\\SK1K1\\SK1\\SSK2"));
    }

    @Test
    public void whenAscendingSort() {
        Department departaments = new Department();
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K3");

        departaments.ascendingSort();

        StringBuilder firstAndLast = new StringBuilder();
        departaments.getDepartment().keySet().forEach(firstAndLast::append);

        Assert.assertThat(firstAndLast.toString(), Is.is("K1K2K3"));
    }

    @Test
    public void whenDescendingSort() {
        Department departaments = new Department();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K3");

        departaments.descendingSort();

        StringBuilder firstAndLast = new StringBuilder();
        departaments.getDepartment().keySet().forEach(firstAndLast::append);

        Assert.assertThat(firstAndLast.toString(), Is.is("K3K2K1"));
    }

}
