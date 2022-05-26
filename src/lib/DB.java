package lib;

import java.io.IOException;
import java.sql.*;

public final class DB {

    private static Connection connection;

    private static void createConnection() {
        // db parameters
        try {
            Utils.ensureFileExists(Configuration.DATABASE_PATH);

            String url = "jdbc:sqlite:" + Configuration.DATABASE_PATH;
            // create a connection to the database

            connection = DriverManager.getConnection(url);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Could not connect to database, : (");
        }
    }

    public static Connection getConnection()  {
        if (connection == null) createConnection();

        return connection;
    }

    public static Statement getStatement() throws SQLException {
        Connection connection = getConnection();

        return connection.createStatement();
    }

    public static void executeStatement(String sql) throws SQLException {
        getStatement().execute(sql);
    }

    /** Utility wrapper on native sql prepared statement */
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}

