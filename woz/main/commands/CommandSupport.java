package main.commands;/* Command for transitioning between spaces
 */

import business.utils.PrintingUtilities;
import main.Context;
import main.commands.BaseCommand;
import main.commands.Command;

public class CommandSupport extends BaseCommand implements Command {
    public CommandSupport() {
        description = "Support projects at the University";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.initInterfaces();
        if (guardEq(parameters, 1)) {
            PrintingUtilities.printOnScreen("I don't seem to know where that is 🤔");
            return;
        }
        context.support(parameters[0]);
    }
}

