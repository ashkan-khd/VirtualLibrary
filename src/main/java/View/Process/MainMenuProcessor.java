package View.Process;

import Controller.Account.BorrowerController;
import Controller.Account.DonatorController;
import Controller.Account.IOController;
import Controller.BookController;
import Model.Book;
import View.FunctioningOption;
import View.Menu;

import java.util.ArrayList;


public class MainMenuProcessor extends Processor{
    private static MainMenuProcessor processor = null;
    private IOController IOcontroller;
    private BookController bookController;

    private MainMenuProcessor() {
        IOcontroller = IOController.getController();
        bookController = BookController.getController();
        allMethods.put("User Menu", new FunctioningOption() {
            public Menu doSth() {
                return IOManaging();
            }
        });
        allMethods.put("Books", new FunctioningOption() {
            public Menu doSth() {
                return showBooks();
            }
        });
    }

    public static MainMenuProcessor getProcessor() {
        if (processor == null)
            processor = new MainMenuProcessor();
        return processor;
    }

    public Menu IOManaging()
    {
        if(IOcontroller.isLoggedIn())
        {
            if(IOcontroller.isBorrower())
            {
                Menu menu = Menu.makeMenu("Borrower Menu");
                menu.setName(BorrowerController.getController().getUsername() + " Menu");
                menu.show();
            }
            else
            {
                Menu menu = Menu.makeMenu("Donator Menu");
                menu.setName(DonatorController.getController().getUsername() + " Menu");
                menu.show();
            }
            return null;
        }
        else
        {
            return Menu.makeMenu("IOMenu");
        }
    }

    public Menu showBooks()
    {
        ArrayList<Book> donatedBooks = bookController.getAllBooks();
        System.out.println("0. Back");
        System.out.println("+-----------------+------------+------------+");
        System.out.format("| %-10s | %-20s | %-20s | %-20s |%n", "ID", "Name", "Donator", "Borrower");
        for (Book donatedBook : donatedBooks) {
            String Borrower = (donatedBook.isBorrowed() ? donatedBook.getBorrower() : "Not Borrowed");
            System.out.format("| %-10s | %-20s | %-20s | %-20s |%n",
                    donatedBook.getId(), donatedBook.getName(), donatedBook.getDonator(), Borrower);
        }
        System.out.println("+-----------------+------------+------------+");
        while (true)
        {
            String command = Menu.getIn().nextLine().trim();
            if(command.equals("0"))
                return Menu.makeMenu("Main Menu");
            else
                System.out.println("Invalid Command");
        }
    }
}
