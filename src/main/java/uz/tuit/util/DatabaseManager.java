package uz.tuit.util;

import uz.tuit.config.Config;

import java.sql.*;

public class DatabaseManager {

    private Config config = new Config();
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(config.getDatabaseUser(), config.getDatabasePassword(), config.getDatabaseUrl());
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Disconnection Failed! Check output console" + e);
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            e.printStackTrace();
            throw e;
        } finally {
            disconnect();
        }

        return resultSet;
    }

    public void executeCommand(String command) throws SQLException {
        Statement statement = null;
        try {
            connect();
            statement = connection.createStatement();
            statement.executeUpdate(command);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            e.printStackTrace();
            throw e;
        } finally {
            disconnect();
        }
    }
}
