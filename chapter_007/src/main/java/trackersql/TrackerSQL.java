package trackersql;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import javax.xml.ws.WebServiceException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {
    /**Исходные данные для работы с профилем бд*/
    private Properties config;

    private Connection connection = null;
    /**
     * При загрузке иницилизируем данные.
     * После проверяем существует ли бд, если нет создаем ее.
     * Подключаем к бд.
     * */
    public TrackerSQL() {
        initConfiguration();
        createIfNotExists();
        connect();
    }

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * createIfNotExists().
     * Сперва делает запрос на существование бд.
     * Если не существует создает.
     * */
    private void createIfNotExists() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select true as exists from pg_database where datname = 'tracker';");
            if (!(resultSet.next() && resultSet.getString("exists").equals("t"))) {
                statement.execute("create database tracker");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(connection);
            closeObj(statement);
            closeObj(resultSet);
        }
    }
    /**
     * connect().
     * Подключается к самому трекеру.
     * Создает необходимую структуру при необходимости.
     * */
    private void connect() {
        Statement statement = null;
        try {
            this.connection = DriverManager.getConnection(
                    config.getProperty("tracker_url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            statement = connection.createStatement();
            statement.execute(String.format("create table if not exists item(%s, %s, %s, %s);",
                    "id int primary key",
                    "name varchar(255)",
                    "description varchar(255)",
                    "create_date bigint"));
            statement.execute(String.format("create table if not exists comments(%s, %s, %s);",
                    "id serial primary key",
                    "content text",
                    "item_id int references item(id)"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(statement);
        }
    }
    /**
     * closeObj().
     * Вызывает метод закрытия у не null объектов, поддерживающих интерфейс AutoCloseable.
     * @param autoCloseable - закрываемый объект.
     * */
    private void closeObj(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception sql) {
                sql.printStackTrace();
            }
        }
    }
    /**
     * initConfiguration().
     * Загружает данные в config.
     * */
    private void initConfiguration() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            config = new Properties();
            config.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    /**
     * add().
     * Добавляет заявку в таблицу заявок, а комментарии к ней в таблицу комментариев.
     * Если не произошло исключение, то возвращает нынедобавленную заяку.
     * @param item заявка.
     * @return добаленная заявка.
     * */
    @Override
    public Item add(Item item) {
        Statement statement = null;
        try (PreparedStatement preparedStatement
                    = connection.prepareStatement("insert into item(id, name, description, create_date) values(?, ?, ?, ?);")) {
            preparedStatement.setInt(1, Integer.valueOf(item.getId()));
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(item.getCreate()));
            preparedStatement.execute();
            statement = connection.createStatement();
            putCommentsIntoItem(statement, item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(statement);
        }
        return item;
    }
    /**
     * putCommentsIntoItem(Statement statement, Item item).
     * Добавляет комментарии заяки в таблицу комментариев.
     * @param item заяка.
     * @param statement объект для запроса.
     * */
    private void putCommentsIntoItem(Statement statement, Item item) {
        try {
            for (String comment : item.getComments()) {
                statement.execute(String.format("insert into comments(content, item_id) values(%s, %s)", comment, item.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * replace(String id, Item item).
     * Обновляет данные заяки, комментарии перезаписывает.
     * @param id идентификатор заявки, которую заменяем.
     * @param item новая заявка.
     * */
    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update item set name = ?, description = ?, create_date = ? where id = ?;");
            Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(item.getCreate()));
            preparedStatement.setInt(4, Integer.parseInt(id));
            item.setId(id);
            preparedStatement.execute();
            statement.execute(String.format("delete from comments where item_id = %s;", id));
            putCommentsIntoItem(statement, item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * delete(String id).
     * Удаляет сначало комментарии к заяке, потом саму заявку.
     * @param id идентификатор заяки.
     * @return true если удаление прошло успешно иначе false.
     * */
    @Override
    public boolean delete(String id) {
        return executeQuery(String.format("delete from comments where item_id = %s;", id))
                && executeQuery(String.format("delete from item where id = %s;", id));
    }
    /**
     * executeQuery(String query).
     * Выполяет запрос.
     * @param query запрос.
     * @return true если запрос выполнился успешно иначе false.
     * */
    private boolean executeQuery(String query) {
        boolean result;
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    /**
     * findByFilter(String filter).
     * Возварщает список заявок по указзанному фильтру используя внешнее связывание.
     * @param filter фильтр поиска.
     * @return все заяки, удолетворяющие фильтру.
     * */
    private ArrayList<Item> findByFilter(String filter) {
        ArrayList<Item> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select * from item as i left join comments as c on i.id = c.item_id %s;", filter
            ))) {
            String lastId = "-1";
            Item item;
            while (resultSet.next()) {
                if (!resultSet.getString(7).equals(lastId)) {
                    item = new Item();
                    item.setId(resultSet.getString(1));
                    item.setName(resultSet.getString("name"));
                    item.setDescription(resultSet.getString("description"));
                    item.setCreate(resultSet.getInt("create_date"));
                    item.setComments(resultSet.getString("content"));
                    result.add(item);
                    lastId = item.getId();
                } else {
                    result.get(result.size() - 1).setComments(resultSet.getString("content"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * findByIName(String key).
     * @return все заяки.
     * */
    @Override
    public ArrayList<Item> findAll() {
        return findByFilter("");
    }
    /**
     * findByIName(String key).
     * Возварщает заяку по ее name.
     * @param key имя заявки.
     * @return все заяки с таким именем.
     * */
    @Override
    public ArrayList<Item> findByName(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("where i.name like '%");
        stringBuilder.append(key);
        stringBuilder.append("%'");
        return findByFilter(stringBuilder.toString());
    }
    /**
     * findById(String id).
     * Возварщает заяку по ее id.
     * @param id идентификатор заявки.
     * @return сама заяка.
     * */
    @Override
    public Item findById(String id) {
        return findByFilter(String.format("where i.id = %s", id)).get(0);
    }
    @Override
    public boolean addCommentById(String id, String comment) {
        return executeQuery(String.format("insert into comments(content, item_id) values (%S, %s);", id, comment));
    }
    /**
     * close().
     * Закрывает подключение.
     * */
    @Override
    public void close() throws WebServiceException {
        closeObj(this.connection);
    }
    /**
     * cleanTables().
     * Очищает таблицы.
     * */
    public void cleanTables() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from comments;");
            statement.execute("delete from item;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
