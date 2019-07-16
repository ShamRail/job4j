package ru.job4j.cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.models.Hall;

public class Test {

    public int row = 3;

    public int col = 3;

    public boolean[][] arr = {{true, false}, {false, true}, {false, false}};

    public static void main(String[] args) throws JsonProcessingException {
        boolean[][] arr = {{true, false}, {false, true}, {false, false}};
        Hall hall = new Hall(arr);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(hall);
        System.out.println(json);
    }

}
