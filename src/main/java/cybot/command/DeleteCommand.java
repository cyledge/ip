package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public class DeleteCommand extends Command {

    private int index;

    /**
     *
     * @param index starts from 1
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MyException {
        tasks.tryGet(index - 1);
        Task removedTask = tasks.delete(index - 1);
        storage.save(tasks);
        ui.showTaskDeleted(removedTask, tasks.size());
        return "";
    }

}
