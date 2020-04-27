package View.Process;


import Controller.Account.IOController;
import View.Menu;

public abstract class AccountProcessor extends Processor {
    private IOController IOController = Controller.Account.IOController.getController();

    public Menu logOut()
    {
        IOController.setLoggedIn(false);
        return Menu.makeMenu("Main Menu");
    }
}
