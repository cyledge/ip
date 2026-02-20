# cybot User Guide
CYbot is a desktop app for managing tasks.

<img width="601" height="955" alt="image" src="https://github.com/user-attachments/assets/a5a497ef-267b-4fd8-8784-52a6197bb7b7" />

## Features
- Add different types of tasks (todo, deadline, event)
- View all tasks in the task list
- Delete task from task list
- Mark/Unmark task as done/not done
- Find task(s) related to a keyword
- Sort the deadline
   
## Commands

### 1. `todo`: add Todo task to your list
Todo [T] - a task with a name only
**Format:** `todo <task name>`
**Example:** `todo book flight`  
```
Got it. I've added this task:
[T][ ] book flight
Now you have 1 tasks in the list.
```

### 2. `deadline`: add Deadline task to your list
Deadline [D] - a task with a deadline  
**Format:** `deadline <task name> /by <date>`
  * `<date>`: YYYY-MM-DD HH:mm 
**Example:** `deadline apply visa /by 2026-02-14 23:59`  
```
Got it. I've added this task:
[D][ ] apply visa (by: 14 Feb 2026, 11:59pm)
Now you have 2 tasks in the list.
```

### 3. `event`: add Event task to your list
Event [E] - a task with a start time and end time
**Format:** `event <task name> /from <date> /to <date>`
  * `<date>`: YYYY-MM-DD HH:mm 
**Example:** `event date with Trump /from 2026-02-14 12:00 /to 2026-02-14 13:00`  
```
Got it. I've added this task:
[E][ ] date with Trump (from: 14 Feb 2026, 12:00pm to: 14 Feb 2026, 01:00pm)
Now you have 3 tasks in the list.
```

### 4. `list`: list task list
**Format:** `list`  
```
Here are the tasks in your list:
1. [T][ ] book flight
2. [D][ ] apply visa (by: 14 Feb 2026, 11:59pm)
3. [E][ ] date with Trump (from: 14 Feb 2026, 12:00pm to: 14 Feb 2026, 01:00pm)
```

### 5. `find`: find task(s) that contains the keyword
**Format:** `find <keyword>`
**Example:** `find book`  
```
Here is the find result:
1. [T][ ] book flight
```

### 6. `mark`: mark the task with given index
**Format:** `mark <index>`
**Example:** `mark 1`  
```
Nice! I've marked this task as done:
[T][X] book flight
```

### 7. `unmark`: unmark the task with given index
**Format:** `unmark <index>`
**Example:** `unmark 1`  
```
Unmarked this task: 
[T][ ] book flight
```

### 8. `delete`: delete the task with given index
**Format:** `delete <index>`
**Example:** `delete 3`  
```
Nice! I've removed this task:
[E][ ] date with Trump (from: 14 Feb 2026, 12:00pm to: 14 Feb 2026, 01:00pm)
Now you have 2 tasks in the list.
```

### 9. `sort`: sort the deadline with their date
**Format:** `sort`  
```
Here are the sorted deadline:
1. [D][ ] apply visa (by: 14 Feb 2026, 11:59pm)
2. [D][ ] dinner with Biden (by: 17 Feb 2026, 08:30pm)
```

### 10. `bye`: exit the program
**Format:** `bye`
