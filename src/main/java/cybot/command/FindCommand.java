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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchList = tasks.findTasks(keyword);
        ui.showTaskList(matchList);
        return "";
    }

}
