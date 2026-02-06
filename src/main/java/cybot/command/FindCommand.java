package cybot.command;

import cybot.task.TaskList;
import cybot.Storage;

/**
 * Command to find the task
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Storage storage)  {
        TaskList matchList = tasks.findTasks(keyword);
        return showTaskList(matchList);
    }

}
