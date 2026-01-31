public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return "";
    }
}
