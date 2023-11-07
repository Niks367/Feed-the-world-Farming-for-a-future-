package main.commands;/* Command for transitioning between spaces
 */

import business.utils.PrintingUtilities;
import main.Context;
import main.commands.BaseCommand;
import main.commands.Command;

public class CommandBuy extends BaseCommand implements Command {
    public CommandBuy() {
        description = "You can buy seeds phosphor or land at the shop";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            PrintingUtilities.printOnScreen("I don't seem to know where that is 🤔");
            return;
        }
        context.buy(parameters[0]);
    }
}

