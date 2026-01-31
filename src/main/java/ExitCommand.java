public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMsg();
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
