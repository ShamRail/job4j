package ru.job4j.departaments;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.TreeSet;

public class DepartamentTest {

    @Test
    public void whenAddDepartmentCodeThenMustAddedItAboveDepartmentCodes() {
        Departament departament = new Departament();
        departament.add("K1\\SK1\\SSK1");
        TreeSet<String> departaments = departament.getDepartaments();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(departaments.pollFirst());
        stringBuilder.append(departaments.pollFirst());
        stringBuilder.append(departaments.pollFirst());
        Assert.assertThat(stringBuilder.toString(), Is.is("K1K1\\SK1K1\\SK1\\SSK1"));
    }

    @Test
    public void whenDescendingSort() {
        Departament departaments = new Departament();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K3");
        TreeSet<String> result = departaments.descendingSort();
        StringBuilder firstAndLast = new StringBuilder();
        firstAndLast.append(result.first());
        firstAndLast.append(result.last());
        Assert.assertThat(firstAndLast.toString(), Is.is("K3K1\\SK1\\SSK2"));
    }

    @Test
    public void whenAscendingSort() {
        Departament departaments = new Departament();
        departaments.add("K1\\SK1\\SSK2");
        departaments.add("K2\\SK1\\SSK2");
        departaments.add("K3");
        TreeSet<String> result = departaments.descendingSort();
        result = departaments.ascendingSort();
        StringBuilder firstAndLast = new StringBuilder();
        firstAndLast.append(result.first());
        firstAndLast.append(result.last());
        Assert.assertThat(firstAndLast.toString(), Is.is("K1K3"));
    }
}
