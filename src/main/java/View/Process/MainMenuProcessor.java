package View.Process;

import Controller.Account.IOController;
import View.FunctioningOption;
import View.Menu;


public class MainMenuProcessor extends Processor{
    private static MainMenuProcessor processor = null;
    private IOController controller;

    private MainMenuProcessor() {
        controller = IOController.getController();
        allMethods.put("User Menu", new FunctioningOption() {
            public Menu doSth() {
                return IOManaging();
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
        if(controller.isLoggedIn())
        {
            //TODO
            return null;
        }
        else
        {
            return Menu.makeMenu("IOMenu");
        }
    }
}
