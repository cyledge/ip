package cybot.command;


import cybot.task.TaskList;
import cybot.Storage;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String execute(TaskList tasks, Storage storage)  {
        return showTaskList(tasks);
    }
}
