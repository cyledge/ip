package cybot.command;

import cybot.task.TaskList;
import cybot.Storage;

public class ExitCommand extends Command {

    public String execute(TaskList tasks, Storage storage)  {
        return "Bye. Hope to see you again soon!" + "\n";

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
