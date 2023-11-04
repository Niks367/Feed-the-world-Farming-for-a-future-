package main;

public class AllCommands extends BaseCommand implements Command {


    AllCommands() {
        description = "Other commands";
    }

    public void printOnScreen(String text) {
        System.out.println(text);
    }

    @Override
    public void execute(Context context, String command, String[] parameters) {
        context.initInterfaces();
        switch (command) {


            case "end_day":
                Context.farmImplementation.endDay();
                break;



            default:

        }
    }
}

