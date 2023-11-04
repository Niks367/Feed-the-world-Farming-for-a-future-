package main;/* Command for transitioning between spaces
 */

class CommandSupport extends BaseCommand implements Command {
    CommandSupport () {
        description = "Support projects at the University";
    }

    @Override
    public void execute (Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.support(parameters[0]);
    }
}

