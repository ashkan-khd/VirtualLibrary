package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowerTable extends Database{

    public static String getInfo(String username, String columnName) throws SQLException, ClassNotFoundException
    {
        String command = "SELECT " + columnName + " from Borrowers WHERE Username=?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(columnName);
    }

    public static void setName(String username, String firstname, String lastname) throws SQLException, ClassNotFoundException
    {
        String command = "UPDATE Borrowers SET Firstname = ?, Lastname = ? WHERE Username = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, username);
        preparedStatement.execute();
    }

    public static void setEmail(String username, String email) throws SQLException, ClassNotFoundException
    {
        String command = "UPDATE Borrowers SET Email = ? WHERE Username = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, username);
        preparedStatement.execute();
    }

    public static ArrayList<Book> getBorrowedBooks(String username) throws SQLException, ClassNotFoundException
    {
        String command = "SELECT * From Books WHERE Borrower = ? AND isBorrowed = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, username);
        preparedStatement.setBoolean(2, true);
        return BookTable.getBooksFromResultSet(preparedStatement.executeQuery());
    }
}
