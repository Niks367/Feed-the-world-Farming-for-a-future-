package main;/* Command for exiting program
 */


class CommandExit extends BaseCommand implements Command {

  CommandExit () {
    description = "Quit game";
  }
  @Override
  public void execute (Context context, String command, String[] parameters) {
    context.makeDone();
  }
}
