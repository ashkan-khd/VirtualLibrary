package View;

import View.Process.Processor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private static Scanner in = new Scanner(System.in);
    private String commandProcessor;
    private String name;
    private String parentMenu;
    private ArrayList<String> options;

    private Menu(String name, String parentMenu, ArrayList<String> options) {
        this.name = name;
        this.parentMenu = parentMenu;
        this.options = options;
    }

    public static Menu makeMenu(String name)
    {
        try {
            return new GsonBuilder().setPrettyPrinting().create().fromJson(getJsonFromDB(name), Menu.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getJsonFromDB(String name) throws FileNotFoundException {
        File file = new File("menujsons\\" + name + ".json");
        if(file.exists())
        {
            String json = "";
            Scanner scanf = new Scanner(file);
            while(scanf.hasNextLine())
            {
                json += scanf.nextLine();
                json += "\n";
            }
            //System.out.println("JSON IS = " + json.substring(0, json.length() - 1));
            return json.substring(0, json.length() - 1);
        }
        System.out.println("Menu File Not Found");
        return null;
    }

    public void show()
    {
        System.out.println(this.name + ":");
        if(parentMenu != null)
            System.out.println("0. Back");
        for(int i = 0; i < options.size(); ++i)
        {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        Menu nextMenu = this.execute();
        nextMenu.show();
    }

    private Menu execute() {
        String command = in.nextLine().trim();
        if(Pattern.matches("\\d+", command))
        {
            if(Integer.parseInt(command) == 0 && parentMenu != null)
                return makeMenu(parentMenu);
            else
            {
                for(int i = 0; i < options.size(); ++i)
                {
                    if(Integer.parseInt(command) == i + 1)
                    {
                        if(!Processor.isProcessInit())
                            Processor.initProcess();
                        if(Processor.getAllProcessors().get(commandProcessor).isFunctionWithName(options.get(i)))
                        {
                            return Processor.getAllProcessors().get(commandProcessor).executeFunction(options.get(i));
                        }
                        else
                        {
                            return Menu.makeMenu(options.get(i));
                        }
                    }
                }
                System.out.println("Invalid Command");
                return this;
            }
        }
        else
        {
            System.out.println("Invalid Command");
            return this;
        }
    }

    public static Scanner getIn() {
        return in;
    }
}
