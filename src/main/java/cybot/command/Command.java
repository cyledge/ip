package cybot.command;

import cybot.MyException;
import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage, Ui ui)  throws MyException;

    public boolean isExit() {
        return false;
    }

    protected static String printNumTask(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    protected static String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "No task in your list!\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }
}
