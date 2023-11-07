package main.commands;/* Command for transitioning between spaces
 */

import business.utils.PrintingUtilities;
import main.Context;
import main.commands.BaseCommand;
import main.commands.Command;

public class CommandEnd extends BaseCommand implements Command {
    public CommandEnd() {
        description = "To end the day manually";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.initInterfaces();
        if (guardEq(parameters, 1)) {
            PrintingUtilities.printOnScreen("I don't seem to know where that is 🤔");
            return;
        }
        context.end(parameters[0]);
    }
}

