package cybot;

import cybot.task.Deadline;
import cybot.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {


    @Test
    public void createDeadline_missingBy_throwsException() {
        MyException exception = assertThrows(MyException.class, () -> {
            Deadline.createDeadline("Submit report");
        });
        assertTrue(exception.getMessage().contains("/by"));
    }


    @Test
    public void toString_deadline_correctFormat() throws MyException {
        Deadline deadline = Deadline.createDeadline("Submit report /by 2023-12-25 22:55");
        deadline.markDone();
        assertEquals("[D][X] Submit report (by: 25 Dec 2023, 10:55 pm)", deadline.toString());
    }
}