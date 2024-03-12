package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientService {
    private static final Logger loger = LogManager.getLogger(ClientService.class);

    private Connection connection;

    public ClientService() {
        connection = H2Database.getINSTANCE().getConnection();
    }

    /**
     * Додає нового клієнта з іменем name. Повертає ідентифікатор щойно створеного клієнта
     *
     * @param name
     * @return id
     */
    long create(String name) {
        if (isInvalidName(name)) {
            return -1;
        }
        try (PreparedStatement statement = connection.prepareStatement("insert into CLIENT (name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.executeUpdate();
            if (statement.getGeneratedKeys().next()) {
                return statement.getGeneratedKeys().getInt(1);
            } else
                return -1;
        } catch (Exception e) {
            loger.error("Error during executing query. Message error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Повертає назву клієнта з ідентифікатором id
     *
     * @param id
     * @return clientName
     */
    String getById(long id) {
        if (isInvalidateId(id)) {
            return null;
        }
        try (PreparedStatement statement = connection.prepareStatement("select name from CLIENT where id=(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, Math.toIntExact(id));
            statement.executeQuery();
            if (statement.getResultSet().next()) {
                return statement.getResultSet().getString("name");
            } else
                return null;
        } catch (Exception e) {
            loger.error("Error during executing query. Message error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Встановлює нове ім'я name для клієнта з ідентифікатором id
     *
     * @param id
     * @param name
     */
    void setName(long id, String name) {
        if (isInvalidName(name) || isInvalidateId(id)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement("update CLIENT set name=(?) where id=(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            loger.error("Error during executing query. Message error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Видаляє клієнта з ідентифікатором id
     *
     * @param id
     */
    void deleteById(long id) {
        if (isInvalidateId(id)) {
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement("delete CLIENT where id=(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            loger.error("Error during executing query. Message error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Повертає всіх клієнтів з БД у вигляді колекції об'єктів типу Client
     *
     * @return List<Client>
     */
    List<Client> listAll() {
        String query = "select * from CLIENT;";
        List<Client> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = executeResultQuery(query);
            while (resultSet.next()) {
                Client item = new Client();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                resultList.add(item);
            }
            return resultList;
        } catch (Exception e) {
            loger.error("Can't find get list of clients");
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet executeResultQuery(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            loger.info("Query were executed successful");
            return resultSet;

        } catch (SQLException e) {
            loger.error("Error while execute sql statement");
            throw new RuntimeException(e);
        }
    }

    private boolean isInvalidateId(long id) {
        if (id < 1) {
            loger.error("Error getting client name, id length must be more than 0");
            return false;
        } else return true;
    }

    private boolean isInvalidName(String name){
        Objects.requireNonNull(name, "Client name must be defined");
        if (name.length() <= 2 || name.length() >= 50) {
            loger.error("Error creating client, name length must be more than 2 and less than 50 characters");
            return false;
        }
        else return true;
    }
}
