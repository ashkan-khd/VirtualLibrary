package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookTable extends Database{


    public static boolean isThereBookID(String bookID) throws SQLException, ClassNotFoundException
    {
        String command = "SELECT BookID From Books WHERE BookID = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, bookID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return true;
        return false;
    }

    public static ArrayList<Book> getDonatedBooksAsList(String username) throws SQLException, ClassNotFoundException
    {
        String command = "SELECT * From Books WHERE Donator = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getBooksFromResultSet(resultSet);
    }

    public static ArrayList<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        String command = "SELECT * From Books ORDER BY Donator";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        return getBooksFromResultSet(preparedStatement.executeQuery());
    }

    protected static ArrayList<Book> getBooksFromResultSet(ResultSet resultSet) throws SQLException
    {
        ArrayList<Book> donatedBooks = new ArrayList<Book>();
        while (resultSet.next())
        {
            donatedBooks.add(new Book(resultSet.getString("BookId"), resultSet.getString("BookName"),
                    resultSet.getString("Donator"), resultSet.getBoolean("isBorrowed"),
                    resultSet.getString("Borrower")));
        }
        return donatedBooks;
    }

    public static boolean isBorrowed(String bookId) throws SQLException, ClassNotFoundException
    {
        String command = "SELECT * From Books Where BookID = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, bookId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean("isBorrowed");
    }

    public static void borrowBook(String bookId, String borrower) throws SQLException, ClassNotFoundException {
        String command = "UPDATE Books SET isBorrowed = ?, Borrower = ? WHERE BookID = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setBoolean(1, true);
        preparedStatement.setString(2, borrower);
        preparedStatement.setString(3, bookId);
        preparedStatement.execute();
    }

    public static void getBackBorrowedBook(String bookId) throws SQLException, ClassNotFoundException {
        String command = "UPDATE Books SET isBorrowed = ? WHERE BookID = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setBoolean(1, false);
        preparedStatement.setString(2, bookId);
        preparedStatement.execute();
    }

    public static boolean isBorrowedBy(String bookId, String username) throws SQLException, ClassNotFoundException {
        String command = "SELECT * From Books Where BookID = ?";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, bookId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Borrower").equals(username);
    }

}
