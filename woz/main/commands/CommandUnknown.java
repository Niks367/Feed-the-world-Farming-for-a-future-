package main.commands;/* Fallback for when a command is not implemented
 */

import business.utils.PrintingUtilities;
import main.Context;
import main.commands.BaseCommand;
import main.commands.Command;

public class CommandUnknown extends BaseCommand implements Command {
    @Override
    public void execute(Context context, String command, String[] parameters) {
        PrintingUtilities.printOnScreen("Woopsie, I don't understand '" + command + "' 😕");
    }
}
