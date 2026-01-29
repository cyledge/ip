public class Todo extends Task{
    Todo(String name) {
        super(name);
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
