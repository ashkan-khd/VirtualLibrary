package Model;

import java.sql.*;

public class AccountTable extends Database {

    public static boolean isUsernameFree(String username) throws SQLException, ClassNotFoundException {
        if(getAccountType(username) != null)
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

    public static boolean isPasswordCorrect(String username, String password) throws SQLException, ClassNotFoundException
    {
        String tableName = getAccountType(username) + "s";
        String command = "SELECT Password from " + tableName + " Where Username=?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Password").equals(password);
    }

    public static String getAccountType(String username) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String command = "SELECT * from Borrowers WHERE Username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return "Borrower";
        command = "SELECT * from Donators WHERE Username=?";
        preparedStatement = connection.prepareStatement(command);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return "Donator";
        return null;
    }
}
