package main;/* Command registry
 */

import main.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    Context context;
    Command fallback;
    Map<String, Command> commands = new HashMap<>();

    Registry(Context context, Command fallback) {
        this.context = context;
        this.fallback = fallback;
    }

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    // reads input from user, first word is command after that
    // is put into new list in getParameters

    public void dispatch(String line) {
        String[] elements = line.split(" ");
        String command = elements[0];
        String[] parameters = getParameters(elements);
        Command handler = getCommand(command);
        (handler == null ? fallback : handler).execute(context, command, parameters);
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public String[] getCommandNames() {
        return commands.keySet().toArray(new String[0]);
    }

    // helpers returnerer resten af listen

    private String[] getParameters(String[] input) {
        String[] output = new String[input.length - 1];
        for (int i = 0; i < output.length; i++) {
            output[i] = input[i + 1];
        }
        return output;
    }
}

