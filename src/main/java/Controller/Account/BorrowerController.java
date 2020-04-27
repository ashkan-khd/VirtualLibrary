package Controller.Account;


import Model.Book;
import Model.BookTable;
import Model.BorrowerTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowerController {
    private static BorrowerController controller = null;
    private String username;

    private BorrowerController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static BorrowerController getController() {
        if(controller == null)
            controller = new BorrowerController();
        return controller;
    }

    public String getInfo(String info) {
        try {
            return BorrowerTable.getInfo(this.username, info);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setName(String firstname, String lastname) {
        try {
            BorrowerTable.setName(this.username, firstname, lastname);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setEmail(String email)
    {
        try {
            BorrowerTable.setEmail(this.username, email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBorrowedBooks() {
        try {
            return BorrowerTable.getBorrowedBooks(this.username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isIdCorrect(String id) {
        try {
            return BookTable.isThereBookID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean canBorrowBook(String id) {
        try {
            return !(BookTable.isBorrowed(id));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void borrowBook(String id) {
        try {
            BookTable.borrowBook(id, this.username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isBookBorrowed(String id) {
        try {
            return BookTable.isBorrowedBy(id, this.username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void giveBackBorrowedBook(String id) {
        try {
            BookTable.getBackBorrowedBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
