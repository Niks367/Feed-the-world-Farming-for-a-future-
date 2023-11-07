package main.commands;/* Help command
 */

import business.utils.PrintingUtilities;
import main.Context;
import main.Registry;

import java.util.Arrays;

public class CommandHelp extends BaseCommand implements Command {
    Registry registry;

    public CommandHelp(Registry registry) {
        this.registry = registry;
        this.description = "Display a help message";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        String[] commandNames = registry.getCommandNames();
        Arrays.sort(commandNames);

        // find max length of command name
        int max = 0;
        for (String commandName : commandNames) {
            int length = commandName.length();
            if (length > max) max = length;
        }

        // present list of commands
        PrintingUtilities.printOnScreen("Commands:");
        for (String commandName : commandNames) {
            String description = registry.getCommand(commandName).getDescription();
            System.out.printf(" - %-" + max + "s %s%n", commandName, description);
        }
    }
}
