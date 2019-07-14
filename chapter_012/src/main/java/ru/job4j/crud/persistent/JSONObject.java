package ru.job4j.crud.persistent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        getterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        setterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        isGetterVisibility      = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
        creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class JSONObject {

    private final int id;

    private final String name;

    private final String surname;

    private final String gender;

    private final String description;

    public JSONObject(@JsonProperty(value = "id") int id,
                      @JsonProperty(value = "name") String name,
                      @JsonProperty(value = "surname") String surname,
                      @JsonProperty(value = "gender") String gender,
                      @JsonProperty(value = "description") String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }
}
