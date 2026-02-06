package cybot.command;

import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public String execute(TaskList tasks, Storage storage, Ui ui)  {
        return "Bye. Hope to see you again soon!" + "\n";

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
