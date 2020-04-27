package Model;

import java.sql.*;

public class AccountTable extends Database {

    public static boolean isUsernameFree(String username) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String command = "SELECT * from Borrowers WHERE Username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return false;
        command = "SELECT * from Donators WHERE Username=?";
        preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return false;
        return true;
    }

    public static void createAccount(String username, String password, String tableName) throws SQLException, ClassNotFoundException
    {
        Connection connection = getConnection();
        String command = "INSERT INTO " + tableName + " (Username, Password) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
    }
}
