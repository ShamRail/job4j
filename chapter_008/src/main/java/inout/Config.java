package inout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    /**
     * config file location.
     * */
    private final String path;

    /**
     * config file map presentation.
     * */
    private final Map<String, String> values = new LinkedHashMap<>();

    public Config(final String path) {
        this.path = path;
        this.load();
    }

    /**
     * load settings from file.
     * */

    private void load() {
        values.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().filter(s -> !s.contains("#") && s.contains("=")).forEach(line -> {
                String[] pair = line.split("=");
                values.put(pair[0], pair[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * save settings to file.
     */
    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
            for (Map.Entry<String, String> entry : values.entrySet()) {
                writer.write(String.format("%s=%s%s", entry.getKey(), entry.getValue(), System.lineSeparator()));
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key key.
     * @return value by key.
     */
    public String value(String key) {
        return values.get(key);
    }

    /**
     * put pair to settings map and save it.
     * @param key input key.
     * @param value input value.
     */
    public void put(String key, String value) {
        values.put(key, value);
        save();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}