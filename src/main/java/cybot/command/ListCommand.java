package cybot.command;


import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui)  {
        ui.showTaskList(tasks);
        return "";
    }
}
