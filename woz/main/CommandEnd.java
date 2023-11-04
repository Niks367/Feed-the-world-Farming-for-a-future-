package main;/* Command for transitioning between spaces
 */

class CommandEnd extends BaseCommand implements Command {
    CommandEnd () {
        description = "To end the day manually";
    }

    @Override
    public void execute (Context context, String command, String[] parameters) {
        context.initInterfaces();
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.end(parameters[0]);
    }
}

