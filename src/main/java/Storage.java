import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file into ArrayList<Task>
     * @return list of tasks
     */
    public TaskList load() throws MyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // create directory if not exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // create file if not exist
        if (!file.exists()) {
            return new TaskList(tasks);
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new MyException("File not found: " + filePath);
        }

        return new TaskList(tasks);
    }

    /**
     * Parse a line from file into Task
     * @return Task of that list
     */
    private Task parseLineToTask(String line) throws  MyException {
        // format: "T | 1 | mytask | (D only) | (T only)"
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new MyException("Corrupted data: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskName = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(taskName);
            break;
        case "D":
            if (parts.length < 4) {
                throw new MyException("Corrupted deadline: " + line);
            }
            LocalDateTime by = Task.parseFileDateTime((parts[3]));
            task = new Deadline(taskName, by);
            break;
        case "E":
            if (parts.length < 5) {
                throw new MyException("Corrupted event: " + line);
            }
            LocalDateTime from = Task.parseFileDateTime((parts[3]));
            LocalDateTime to = Task.parseFileDateTime((parts[4]));
            task = new Event(taskName, from, to);
            break;
        default:
            throw new MyException("Unknown task type: " + line);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    /**
     * Saves task back to file in hard disk
     */
    public void save(TaskList tasks) throws MyException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new MyException("Error saving tasks: " + e.getMessage());
        }
    }

}
