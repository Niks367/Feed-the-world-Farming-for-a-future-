package main;/* Command for transitioning between spaces
 */

class CommandShow extends BaseCommand implements Command {
    CommandShow () {
        description = "Show stats for phosphor, seeds, hunger, money, population, days played, exits";
    }

    @Override
    public void execute (Context context, String command, String[] parameters) {
        //context.initInterfaces();
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.show(parameters[0]);
        }
    }

