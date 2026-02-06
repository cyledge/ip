package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * @param index starts with 1
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui)  throws MyException {
        Task task = tasks.tryGet(index - 1);
        if (isDone) {
            task.markDone();
            ui.showMark(task);
        } else {
            task.unmarkDone();
            ui.showUnmark(task);
        }
        storage.save(tasks);
        return "";
    }
}
