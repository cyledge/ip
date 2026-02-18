package cybot.task;

import cybot.MyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

// index in TaskList is 0 based
public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Return the size of the TaskList
     * @return
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Check whether the index input is a valid index
     * @param index
     * @throws MyException
     */
    private void checkIndex(int index) throws MyException {
        if (index < 0 || index >= tasks.size()) {
            throw new MyException(String.format("Task# %d > tasks (%d) you have", (index + 1), tasks.size()));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Add the Task to the task list
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delete the task if the index is valid
     * @param index
     * @return deleted Task
     */
    public Task delete(int index) throws MyException {
        checkIndex(index);
        return tasks.remove(index);
    }

    /**
     * Wrap checkIndex, return the Task if the index is valid
     * @param index
     * @return
     * @throws MyException
     */
    public Task tryGet(int index) throws MyException {
        checkIndex(index);
        return get(index);
    }

    /**
     * Get the task at that index
     * @param index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Find the tasks contain that keyword
     * @param keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList matchList = new TaskList();
        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(keyword)) {
                matchList.add(t);
            }
        }
        return matchList;
    }

    /**
     * Return a new TaskList that fulfill that condition
     * @param condition
     * @return
     */
    public TaskList filter(Predicate<Task> condition) {
        ArrayList<Task> tasksList = new ArrayList<>(this.tasks.stream().filter(condition).toList());
        return new TaskList(tasksList);
    }

    public void sort(Comparator<? super Task> comparator) {
        Collections.sort(tasks, comparator);
    }


}
