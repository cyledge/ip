package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;

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
    public String execute(TaskList tasks, Storage storage)  throws MyException {
        Task task = tasks.tryGet(index - 1);
        StringBuilder sb = new StringBuilder();
        if (isDone) {
            task.markDone();
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            task.unmarkDone();
        }
        storage.save(tasks);
        sb.append(task + "\n");
        return sb.toString();
    }
}
