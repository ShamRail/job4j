package ru.job4j.cinema.services;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.models.Hall;
import ru.job4j.cinema.models.Place;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HallDAO {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final HallDAO INSTANCE = new HallDAO();

    private HallDAO() {
        initConfig();
        initTable();
        fillTable();
    }

    private void initConfig() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    private void initTable() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute("drop table if exists places;");
             statement.execute(String.format("create table if not exists places(%s, %s, %s, %s);",
                    "id serial primary key",
                    "halls_row int",
                    "halls_col int",
                    "isBusy boolean")
             );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTable() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into places(halls_row, halls_col, isBusy) values(?, ?, ?);")) {
             connection.setAutoCommit(false);
             for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                     ps.setInt(1, i + 1);
                     ps.setInt(2, j + 1);
                     ps.setBoolean(3, false);
                     ps.addBatch();
                 }
             }
             ps.executeBatch();
             connection.commit();
             connection.setAutoCommit(true);
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    public static HallDAO getInstance() {
        return INSTANCE;
    }

    public Hall getHall() {
        Hall hall = null;
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select halls_row, halls_col, isBusy from places;")){
             List<Place> places = new LinkedList<>();
             while (rs.next()) {
                 int row = rs.getInt(1);
                 int col = rs.getInt(2);
                 boolean isBusy = rs.getBoolean(3);
                 places.add(new Place(row, col, isBusy));
             }
             int maxRow = -1;
             int maxCol = -1;
             for (Place place : places) {
                 maxRow = Math.max(maxRow, place.getRow());
                 maxCol = Math.max(maxCol, place.getCol());
             }
             boolean[][] table = new boolean[maxRow][maxCol];
             for (Place place : places) {
                 table[place.getRow() - 1][place.getCol() - 1] = place.isBusy();
             }
             hall = new Hall(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hall;
    }

    public void occupy(Place place) {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
        ) {
             statement.execute(String.format("update places set isBusy = true where halls_row = %s and halls_col = %s;",
                     place.getRow(),
                     place.getCol()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
