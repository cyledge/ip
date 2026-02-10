package cybot.command;

import cybot.Storage;
import cybot.task.Deadline;
import cybot.task.TaskList;

import java.util.ArrayList;

public class SortCommand extends Command {

    private static String OUTPUT_STRING = "Here are the sorted deadline: \n";
    @Override
    public String execute(TaskList taskList, Storage storage) {
        TaskList deadlineList = taskList.filter(t -> t instanceof Deadline);
        deadlineList.sort((t1, t2) -> {
            if (t1 instanceof Deadline && t2 instanceof Deadline) {
                return (((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy()));
            }
            return 0;
        });

        return showTaskList(deadlineList, OUTPUT_STRING);
    }
}
