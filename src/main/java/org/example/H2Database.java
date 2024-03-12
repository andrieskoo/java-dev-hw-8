package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.PropertyReader;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class H2Database {
    private static Logger loger = LogManager.getLogger(H2Database.class);

    private static H2Database INSTANCE;

    private final Connection connection;

    private H2Database() {

        try {
            String url = PropertyReader.getUrlH2Database();
            String username = PropertyReader.getUsernameH2Database();
            String pass = PropertyReader.getPasswordH2Database();
            connection = DriverManager.getConnection(url, username, pass);

            Flyway flyway = Flyway.configure().dataSource(url, username, pass).load();
            flyway.migrate();
            loger.info("Migration was executed successful");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static H2Database getINSTANCE() {
        return Objects.requireNonNullElseGet(INSTANCE, H2Database::new);
    }

    public Connection getConnection() {
        return connection;
    }
    public void close(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
