package main.commands;/* Command interface
 */

import main.Context;

public interface Command {
  void execute (Context context, String command, String[] parameters);
  String getDescription ();
}

