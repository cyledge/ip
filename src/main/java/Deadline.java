public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadlines) {
        super(name);
        this.deadline = deadlines;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",  super.toString(), this.deadline);
    }
}
