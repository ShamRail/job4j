package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Config.
 * DB configuration.
 * */

public class Config {

    private final Properties values = new Properties();

    private static final Logger LOG = LogManager.getLogger(Config.class.getName());

    public Config() {
        init();
    }
    /**
     * init.
     * Load properties from db properties file.
     * */
    private void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            LOG.debug("Class has found.");
            try (InputStream in = Config.class.getClassLoader().getResourceAsStream("sqlite.properties")) {
                values.load(in);
                LOG.debug("Loading has finished.");
            } catch (Exception e) {
                LOG.error(String.format("Loading has fallen with %s", e.getClass().getSimpleName()));
            }
        } catch (Exception e) {
            LOG.error("Class has not found.");
        }

    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

}