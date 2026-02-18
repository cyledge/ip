package cybot.command;


import cybot.task.TaskList;
import cybot.Storage;

/**
 * Ask the bot to print the tasklist
 */
public class ListCommand extends Command {


    @Override
    public String execute(TaskList tasks, Storage storage)  {
        return showTaskList(tasks);
    }
}
