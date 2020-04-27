package Controller.Account;


import Model.Book;
import Model.BookTable;
import Model.DonatorTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonatorController {
    private static DonatorController controller = null;
    private String username;

    private DonatorController() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static DonatorController getController() {
        if(controller == null)
            controller = new DonatorController();
        return controller;
    }

    public void donateBook(String bookName) {
        String bookID;
        while (true)
        {
            bookID = generatID();
            try {
                if(!BookTable.isThereBookID(bookID))
                    break;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            DonatorTable.donateBook(this.username, bookName, bookID);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String generatID() {
        char[] validChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String Id = "";
        for(int i = 0; i < 6; ++i)
        {
            Id += validChars[(((int)(Math.random()*100000)) % validChars.length)];
        }
        return Id;
    }

    public ArrayList<Book> getDonatedBooks() {
        try {
            return BookTable.getDonatedBooksAsList(this.username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
