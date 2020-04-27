package Controller.Account;


import Model.BorrowerTable;

import java.sql.SQLException;

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
}
