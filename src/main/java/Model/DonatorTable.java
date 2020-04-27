package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DonatorTable extends Database{

    public static void donateBook(String username, String bookName, String bookId) throws SQLException, ClassNotFoundException
    {
        String command = "INSERT INTO Books (BookID, BookName, Donator, isBorrowed) Values(?, ?, ?, ?)";
        PreparedStatement preparedStatement = getPreparedStatement(command);
        preparedStatement.setString(1, bookId);
        preparedStatement.setString(2, bookName);
        preparedStatement.setString(3, username);
        preparedStatement.setBoolean(4, false);
        preparedStatement.execute();
    }

}
