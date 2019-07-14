package ru.job4j.crud.persistent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JSONObjectTest {

    @Test
    public void test() throws IOException {
        JSONObject object = new JSONObject(0, "n", "s", "m", "d");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        JSONObject object1 = mapper.readValue(json, JSONObject.class);
        assertThat(object1.getId(), Is.is(0));
        assertThat(object1.getDescription(), Is.is("d"));
    }


}