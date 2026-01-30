import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    Deadline(String name, LocalDateTime deadlines) {
        super(name);
        this.deadline = deadlines;
    }



        @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + deadline.format(FILE_FORMATTER);
    }


    @Override
    public String toString() {
        String formattedDdl = deadline.format(DISPLAY_FORMATTER);
        return String.format("[D]%s (by: %s)",  super.toString(), formattedDdl);
    }
}
