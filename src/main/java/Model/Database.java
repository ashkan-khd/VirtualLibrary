package Model;

import java.io.File;
import java.sql.*;

public class Database {
    private static Connection connection = null;
    private static boolean isDatabaseInit = false;
    private static String localDBUrl = "jdbc:sqlite:database\\database.sqlite";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(!isDatabaseInit)
            initDatabase();
        if(connection == null)
            connection = DriverManager.getConnection(localDBUrl);
        return connection;
    }

    private static void initDatabase() throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        File file = new File("database");
        file.mkdir();

        try {
            connection = DriverManager.getConnection(localDBUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name from sqlite_master WHERE type = 'table' AND name = 'Borrowers'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE Borrowers (" +
                        "Username varchar(25)," +
                        "Password varchar(50)," +
                        "Firstname varchar(50)," +
                        "Lastname varchar(50)," +
                        "primary key(Username)" +
                        ");");
            }
            resultSet = statement.executeQuery("SELECT name from sqlite_master WHERE type = 'table' AND name = 'Donators'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE Donators (" +
                        "Username varchar(25)," +
                        "Password varchar(50)," +
                        "Firstname varchar(50)," +
                        "Lastname varchar(50)," +
                        "primary key(Username)" +
                        ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
