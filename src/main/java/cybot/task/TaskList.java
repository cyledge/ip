package cybot.task;

import cybot.MyException;

import java.util.ArrayList;

// index in TaskList is 0 based
public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public int size() {
        return tasks.size();
    }

    private void checkIndex(int index) throws MyException {
        if (index < 0 || index >= tasks.size()) {
            throw new MyException(String.format("Task# %d > tasks (%d) you have", (index + 1), tasks.size()));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws MyException {
        checkIndex(index);
        return tasks.remove(index);
    }

    public Task tryGet(int index) throws MyException {
        checkIndex(index);
        return get(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public TaskList findTasks(String keyword) {
        TaskList matchList = new TaskList();
        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(keyword)) {
                matchList.add(t);
            }
        }
        return matchList;
    }

    /*
    public void printTaskList() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

     */
}
