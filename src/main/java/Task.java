public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String box;
        if (done) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return box + name;
    }
}
