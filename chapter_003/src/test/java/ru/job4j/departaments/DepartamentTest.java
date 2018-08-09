package ru.job4j.departaments;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class DepartamentTest {
    @Test
    public void whenDivideOnSubDepartments() {
        Departament departament = new Departament();
        TreeSet<String> resultOfDiving = departament.divideOnSubDepartments("K1\\SK1\\SSK2");
        StringBuilder firstAndLast = new StringBuilder();

        firstAndLast.append(resultOfDiving.first());
        firstAndLast.append(resultOfDiving.last());

        Assert.assertThat(firstAndLast.toString(), Is.is("K1\\SK1K1\\SK1\\SSK2"));
    }
    @Test
    public void whenAddingDeparmentThenAboveDepartamentsMustAddedToo() {
        Departament departaments = new Departament();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");

        LinkedHashMap<String, TreeSet<String>> result = departaments.getDepartaments();
        TreeSet<String> subDepartmets = result.get("K1");


        StringBuilder firstAndLast = new StringBuilder();
        firstAndLast.append(subDepartmets.first());
        firstAndLast.append(subDepartmets.last());
        Assert.assertThat(firstAndLast.toString(), Is.is("K1\\SK1K1\\SK1\\SSK2"));
    }
    @Test
    public void whenDescendingSort() {
        Departament departaments = new Departament();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K3");

        departaments.descengingSort();

        StringBuilder firstAndLast = new StringBuilder();
        departaments.getDepartaments().keySet().forEach(firstAndLast::append);

        Assert.assertThat(firstAndLast.toString(), Is.is("K3K2K1"));
    }

    @Test
    public void whenAscendingSort() {
        Departament departaments = new Departament();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K3");

        departaments.descengingSort();
        departaments.ascendingSort();

        StringBuilder firstAndLast = new StringBuilder();
        departaments.getDepartaments().keySet().forEach(firstAndLast::append);

        Assert.assertThat(firstAndLast.toString(), Is.is("K1K2K3"));
    }
}
