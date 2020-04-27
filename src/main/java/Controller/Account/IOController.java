package Controller.Account;

import Model.AccountTable;

import java.sql.SQLException;

public class IOController {
    private static IOController controller = null;
    private boolean isLoggedIn = false;

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
}
