package ru.job4j.professions;

import ru.job4j.profession.Profession;
import ru.job4j.professionsuse.*;

public class Doctor extends Profession {

    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public void healPatient(Patient patient) {

    }
}
