package View.Process;

import Controller.Account.BorrowerController;
import Model.Book;
import View.FunctioningOption;
import View.Menu;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class BorrowerProcessor extends AccountProcessor {
    private static BorrowerProcessor processor = null;
    private BorrowerController controller;
    private BorrowerProcessor() {
        this.controller = BorrowerController.getController();
        allMethods.put("Show Personal Info", new FunctioningOption() {
            public Menu doSth() {
                return showPersonalInfo();
            }
        });
        allMethods.put("Edit Personal Info", new FunctioningOption() {
            public Menu doSth() {
                return editPersonalInfo();
            }
        });
        allMethods.put("Show Borrowed Books", new FunctioningOption() {
            public Menu doSth() {
                return showBorrowedBooks();
            }
        });
        allMethods.put("Edit Borrowed Books", new FunctioningOption() {
            public Menu doSth() {
                return editBorrowedBooks();
            }
        });
        allMethods.put("Log out", new FunctioningOption() {
            public Menu doSth() {
                return logOut();
            }
        });
    }

    public static BorrowerProcessor getProcessor() {
        if(processor == null)
            processor = new BorrowerProcessor();
        return processor;
    }

    public Menu showPersonalInfo()
    {
        while(true)
        {
            System.out.println("0. Back");
            showInfo();
            String command = Menu.getIn().nextLine().trim();
            if(command.equals("0"))
            {
                Menu menu = Menu.makeMenu("Borrower Menu");
                menu.setName(controller.getUsername() + " Menu");
                return menu;
            }
            else
                System.out.println("Invalid Command");
        }
    }

    private void showInfo()
    {
        System.out.println("+-----------------+------------+------------+");
        String firstname = controller.getInfo("Firstname");
        if(firstname == null)
            firstname = "Not Assigned";
        System.out.format("| %-15s | %-1s |%n", "First Name", firstname);
        String lastname = controller.getInfo("Lastname");
        if(lastname == null)
            lastname = "Not Assigned";
        System.out.format("| %-15s | %-1s |%n", "Last Name", lastname);
        String email = controller.getInfo("Email");
        if(email == null)
            email = "Not Assigned";
        System.out.format("| %-15s | %-1s |%n", "E-Mail", email);
        System.out.println("+-----------------+------------+------------+");
    }

    public Menu editPersonalInfo()
    {
        while(true)
        {
            showInfo();
            System.out.println("0. Back");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Email");
            String command = Menu.getIn().nextLine().trim();
            if(Pattern.matches("\\d+", command) && (Integer.parseInt(command) < 3))
            {
                if(Integer.parseInt(command) == 1)
                    editName();
                if(Integer.parseInt(command) == 2)
                    editEmail();
                if(Integer.parseInt(command) == 0)
                {
                    Menu menu = Menu.makeMenu("Borrower Menu");
                    menu.setName(controller.getUsername() + " Menu");
                    return menu;
                }
            }
            else
                System.out.println("Invalid Command");
        }
    }

    private void editName() {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Enter Your Name In This Format: Firstname, Lastname");
            String name = Menu.getIn().nextLine().trim();
            if(name.equals("0"))
                return;
            if(Pattern.matches("(\\S+), (\\S+)", name))
            {
                String[] inputs = name.split(",");
                controller.setName(inputs[0].trim(), inputs[1].trim());
                System.out.println("Successfully Modified");
                return;
            }
            else
                System.out.println("Invalid Format");
        }
    }

    private void editEmail()
    {
        System.out.println("0. Back");
        System.out.println("Enter Your Email");
        String email = Menu.getIn().nextLine().trim();
        if(email.equals("0"))
            return;
        controller.setEmail(email);
        System.out.println("Successfully Modified");
    }

    public Menu showBorrowedBooks()
    {
        while(true)
        {
            System.out.println("0. Back");
            showBooks();
            String command = Menu.getIn().nextLine().trim();
            if(command.equals("0"))
            {
                Menu menu = Menu.makeMenu("Borrower Menu");
                menu.setName(controller.getUsername() + " Menu");
                return menu;
            }
            else
                System.out.println("Invalid Command");
        }
    }

    private void showBooks()
    {
        ArrayList<Book> donatedBooks = controller.getBorrowedBooks();
        System.out.println("+-----------------+------------+------------+");
        System.out.format("| %-10s | %-20s | %-20s |%n", "ID", "Name", "Donator");
        for (Book donatedBook : donatedBooks) {
            System.out.format("| %-10s | %-20s | %-20s |%n", donatedBook.getId(), donatedBook.getName(), donatedBook.getDonator());
        }
        System.out.println("+-----------------+------------+------------+");
    }

    public Menu editBorrowedBooks()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("1. Borrow Book");
            System.out.println("2. Give Back Borrowed Book");
            System.out.println("3. Show Borrowed Books");
            String command = Menu.getIn().nextLine().trim();
            if (Pattern.matches("\\d+", command) && (Integer.parseInt(command) < 4))
            {
                if(Integer.parseInt(command) == 0)
                {
                    Menu menu = Menu.makeMenu("Borrower Menu");
                    menu.setName(controller.getUsername() + " Menu");
                    return menu;
                }
                else if(Integer.parseInt(command) == 3)
                    showBooks();
                else if(Integer.parseInt(command) == 1)
                    borrowBook();
                else if(Integer.parseInt(command) == 2)
                    giveBackBorrowedBook();

            }
            else
                System.out.println("Invalid Command");
        }
    }

    private void giveBackBorrowedBook() {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Enter Book Id: ");
            String Id = Menu.getIn().nextLine().trim();
            if(Id.equals("0"))
                return;
            if(controller.isIdCorrect(Id))
            {
                if(controller.isBookBorrowed(Id))
                {
                    controller.giveBackBorrowedBook(Id);
                    System.out.println("Successfully Given Back");
                    return;
                }
                else
                    System.out.println("You Haven't borrowed This book");
            }
            else
                System.out.println("Invalid Id");

        }
    }

    private void borrowBook() {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Enter Book Id: ");
            String Id = Menu.getIn().nextLine().trim();
            if(Id.equals("0"))
                return;
            if(controller.isIdCorrect(Id))
            {
                if(controller.canBorrowBook(Id))
                {
                    controller.borrowBook(Id);
                    System.out.println("Successfully Borrowed");
                    return;
                }
                else
                    System.out.println("The Book Is Already Borrowed By Someone");
            }
            else
                System.out.println("Invalid Id");

        }
    }
}
