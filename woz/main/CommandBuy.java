package main;/* Command for transitioning between spaces
 */

class CommandBuy extends BaseCommand implements Command {
    CommandBuy () {
        description = "You can buy seeds phosphor or land at the shop";
    }

    @Override
    public void execute (Context context, String command, String[] parameters) {
        if (guardEq(parameters, 1)) {
            System.out.println("I don't seem to know where that is 🤔");
            return;
        }
        context.buy(parameters[0]);
    }
}

