package cybot.task;

import cybot.MyException;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public static final String DEADLINE_FORMAT = "deadline <name> /by <" + Task.DATE_PATTERN + ">";

    public Deadline(String name, LocalDateTime deadlines) {
        super(name);
        this.deadline = deadlines;
    }

    /**
     * Factory method to create Deadline from raw input
     * @param input "<name> /by YYYY-MM-DD HH:mm
     * @return Deadline obj
     * @throws  MyException for invalid input
     */
    public static Deadline createDeadline(String input) throws MyException {
        String[] parts = input.split(" /by");
        if (parts.length < 2) {
            throw new MyException("Please use: " + DEADLINE_FORMAT);
        }

        String name = parts[0].trim();
        String by = parts[1].trim();

        try {
            LocalDateTime byDateTime = Task.parseFileDateTime(by);
            return new Deadline(name, byDateTime);
        } catch (IllegalArgumentException e) {
            throw new MyException("Please use: " + DEADLINE_FORMAT);
        }

    }


    public LocalDateTime getBy() {
        return this.deadline;
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
