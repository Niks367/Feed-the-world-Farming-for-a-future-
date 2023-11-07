package main.commands;/* Command for exiting program
 */


import main.Context;
import main.commands.BaseCommand;
import main.commands.Command;

public class CommandExit extends BaseCommand implements Command {

    public CommandExit() {
        description = "Quit game";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.makeDone();
    }
}
