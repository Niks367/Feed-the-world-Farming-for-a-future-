package main.commands;


import main.Context;

public class AllCommands extends BaseCommand implements Command {


    AllCommands() {
        description = "Other commands";
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.initInterfaces();
        switch (command) {


            default:

        }
    }
}

