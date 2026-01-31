package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MyException {
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return "";
    }


}

