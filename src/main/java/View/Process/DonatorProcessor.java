package View.Process;

import Controller.Account.DonatorController;
import Model.Book;
import View.FunctioningOption;
import View.Menu;

import java.util.ArrayList;

public class DonatorProcessor extends AccountProcessor {
    private static DonatorProcessor processor = null;
    private DonatorController controller;

    private DonatorProcessor() {
        controller = DonatorController.getController();
        allMethods.put("Donate Book", new FunctioningOption() {
            public Menu doSth() {
                return donateBook();
            }
        });
        allMethods.put("Show Donated Books", new FunctioningOption() {
            public Menu doSth() {
                return showDonatedBooks();
            }
        });
        allMethods.put("Log out", new FunctioningOption() {
            public Menu doSth() {
                return logOut();
            }
        });
    }

    public static DonatorProcessor getProcessor() {
        if(processor == null)
            processor = new DonatorProcessor();
        return processor;
    }

    public Menu donateBook()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Please Enter The Name Of Donated Book: ");
            String bookName = Menu.getIn().nextLine().trim();
            if(bookName.equals("0"))
            {
                Menu menu = Menu.makeMenu("Donator Menu");
                menu.setName(controller.getUsername() + " Menu");
                return menu;
            }
            controller.donateBook(bookName);
            System.out.println("Successfully Added. Tnx for your Donation");
        }
    }

    public Menu showDonatedBooks()
    {
        while(true)
        {
            System.out.println("0. Back");
            showBooks();
            String command = Menu.getIn().nextLine().trim();
            if(command.equals("0"))
            {
                Menu menu = Menu.makeMenu("Donator Menu");
                menu.setName(controller.getUsername() + " Menu");
                return menu;
            }
            else
                System.out.println("Invalid Command");
        }
    }

    private void showBooks() {
        ArrayList<Book> donatedBooks = controller.getDonatedBooks();
        System.out.println("+-----------------+------------+------------+");
        System.out.format("| %-10s | %-20s | %-20s |%n", "ID", "Name", "Borrower");
        for (Book donatedBook : donatedBooks) {
            String Borrower = (donatedBook.isBorrowed() ? donatedBook.getBorrower() : "Not Borrowed");
            System.out.format("| %-10s | %-20s | %-20s |%n", donatedBook.getId(), donatedBook.getName(), Borrower);
        }
        System.out.println("+-----------------+------------+------------+");
    }
}
