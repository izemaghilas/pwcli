package dz.izemaghilas.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static Connection connection;

    public static Connection getConnection() throws DBManagerException {
        if (connection == null) {
            try (InputStream inputStream = DBManager.class.getClassLoader().getResourceAsStream("application.properties")) {
                if (inputStream == null) {
                    throw new DBManagerException("could not found properties file");
                }

                Properties properties = new Properties();
                properties.load(inputStream);
                connection = DriverManager.getConnection(properties.getProperty("jdbc.url"));
            } catch (SQLException | IOException e) {
                throw new DBManagerException(e.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() throws DBManagerException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBManagerException(e.getMessage());
            }
        }
    }
}
