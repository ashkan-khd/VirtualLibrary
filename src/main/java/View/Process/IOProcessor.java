package View.Process;

import Controller.Account.IOController;
import View.FunctioningOption;
import View.Menu;

import java.util.regex.Pattern;

public class IOProcessor extends Processor {
    private static IOProcessor processor = null;
    private IOController controller = IOController.getController();

    private IOProcessor() {
        allMethods.put("Sign Up", new FunctioningOption() {
            public Menu doSth() {
                return SignUp();
            }
        });
    }

    public static IOProcessor getProcessor() {
        if(processor == null)
            processor = new IOProcessor();
        return processor;
    }

    public Menu SignUp()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Please Choose Your Type: ");
            System.out.println("1. Borrower");
            System.out.println("2. Donator");
            String command = Menu.getIn().nextLine();
            if(Pattern.matches("\\d+", command) && (Integer.parseInt(command) < 3))
            {
                if(Integer.parseInt(command) == 0)
                    return Menu.makeMenu("IOMenu");
                while (true)
                {
                    System.out.println("0. Back");
                    System.out.println("Please Choose Your Username: ");
                    String username = Menu.getIn().nextLine();
                    if(username.equals("0"))
                        break;
                    if(controller.isUsernameFree(username))
                    {
                        System.out.println("Please Choose Your Password: ");
                        String password = Menu.getIn().nextLine();
                        if(Integer.parseInt(command) == 1)
                            controller.creatAccount(username,password, "Borrower");
                        else
                            controller.creatAccount(username,password, "Donator");
                        System.out.println("Successfully Signed Up");
                        return Menu.makeMenu("IOMenu");
                    }
                    else
                        System.out.println("Username is already taken");
                }
            }
            else
                System.out.println("Invalid Command");
        }

    }
}
