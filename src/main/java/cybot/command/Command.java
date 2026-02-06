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
}
