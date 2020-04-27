package Controller.Account;

import Model.AccountTable;

import java.sql.SQLException;

public class IOController {
    private static IOController controller = null;
    private boolean isLoggedIn = false;
    private boolean isBorrower = false;

    private IOController() {
    }

    public static IOController getController() {
        if(controller == null)
            controller = new IOController();
        return controller;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isUsernameFree(String username)
    {
        try {
            return AccountTable.isUsernameFree(username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void creatAccount(String username, String password, String type)
    {
        try {
            AccountTable.createAccount(username, password, type + "s");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordCorrect(String username, String password)
    {
        try {
            return AccountTable.isPasswordCorrect(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getAccountType(String username) {
        try {
            return AccountTable.getAccountType(username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void signIn(String username, String type)
    {
        if(type.equals("Borrower"))
        {
            isBorrower = true;
            BorrowerController.getController().setUsername(username);
        }
        else
        {
            isBorrower = false;
            //TODO
        }

    }
}
