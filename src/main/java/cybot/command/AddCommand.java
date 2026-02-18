package cybot.command;

import cybot.MyException;
import cybot.task.Task;
import cybot.task.TaskList;
import cybot.Storage;


/**
 * Add function: add a Task
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MyException {
        tasks.add(task);
        storage.save(tasks);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:" + "\n");
        sb.append(task + "\n");
        sb.append(printNumTask(tasks.size()) + "\n");
        return sb.toString();
    }


}

