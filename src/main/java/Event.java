import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + from.format(FILE_FORMATTER) + " | " + to.format(FILE_FORMATTER);
    }

    @Override
    public String toString() {
        String formattedTo = to.format(DISPLAY_FORMATTER);
        String formattedFrom = from.format(DISPLAY_FORMATTER);

        return String.format("[E]%s (from: %s to: %s)",  super.toString(), formattedFrom, formattedTo);

    }
}
