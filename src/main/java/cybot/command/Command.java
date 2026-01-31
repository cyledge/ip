package cybot.command;

import cybot.MyException;
import cybot.task.TaskList;
import cybot.Storage;
import cybot.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MyException;

    public boolean isExit() {
        return false;
    }
}
