package Controller;

import Model.Book;
import Model.BookTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookController {
    private static BookController controller = null;

    private BookController() {
    }

    public static BookController getController() {
        if(controller == null)
            controller = new BookController();
        return controller;
    }

    public ArrayList<Book> getAllBooks()
    {
        try {
            return BookTable.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
