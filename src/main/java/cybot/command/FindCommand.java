package cybot.command;

import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

/**
 * Command to find the task
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui)  {
        TaskList matchList = tasks.findTasks(keyword);
        ui.showTaskList(matchList);
        return "";
    }

}
