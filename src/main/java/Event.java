public class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",  super.toString(), from, to);

    }
}
