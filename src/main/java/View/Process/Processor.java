package View.Process;

import View.FunctioningOption;
import View.Menu;

import java.util.HashMap;

public abstract class Processor {
    protected static HashMap<String, Processor> allProcessors = new HashMap<String, Processor>();
    private static boolean isProcessInit = false;
    protected HashMap<String, FunctioningOption> allMethods = new HashMap<String, FunctioningOption>();

    public static boolean isProcessInit() {
        return isProcessInit;
    }

    public static void initProcess()
    {
        isProcessInit = true;
        allProcessors.put("Main Menu Processor", MainMenuProcessor.getProcessor());
        allProcessors.put("IO Processor", IOProcessor.getProcessor());
    }

    public static HashMap<String, Processor> getAllProcessors() {
        return allProcessors;
    }

    public boolean isFunctionWithName(String name)
    {
        return allMethods.containsKey(name);
    }

    public Menu executeFunction(String name)
    {
        return allMethods.get(name).doSth();
    }
}
