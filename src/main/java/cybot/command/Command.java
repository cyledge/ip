package cybot.command;

import cybot.MyException;
import cybot.task.TaskList;
import cybot.Storage;

/**
 * Different command to be input by the user
 */
public abstract class Command {

    /**
     * Execute the cammand, all the logic here
     * @param tasks a task list
     * @param storage current storage of the bot
     * @return a string to be printed
     * @throws MyException
     */
    public abstract String execute(TaskList tasks, Storage storage)  throws MyException;

    /**
     * Determine whether this is an exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }

    protected static String printNumTask(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    protected static String showTaskList(TaskList tasks) {
        String output = "Here are the tasks in your list:\n";
        return showTaskList(tasks, output);
    }

    protected static String showTaskList(TaskList tasks, String outputString) {
        if (tasks.size() == 0) {
            return "No task in your list!\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(outputString);
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }
}
