package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;

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
    public String execute(TaskList tasks, Storage storage) throws MyException {
        tasks.tryGet(index - 1);
        Task removedTask = tasks.delete(index - 1);
        storage.save(tasks);
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task: " + "\n");
        sb.append(removedTask + "\n");
        sb.append(printNumTask(tasks.size()) + "\n");
        return sb.toString();
    }


}
