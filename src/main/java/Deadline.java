public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadlines) {
        super(name);
        this.deadline = deadlines;
    }


    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + deadline;
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",  super.toString(), this.deadline);
    }
}
