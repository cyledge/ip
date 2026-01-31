package cybot;

import cybot.MyException;
import cybot.command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_todoCommand_returnsAddCommand() throws MyException {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineCommand_returnsAddCommand() throws MyException {
        Command command = Parser.parse("deadline Submit assignment /by 2023-12-25 22:25");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineMissingBy_throwsException() {
        MyException exception = assertThrows(MyException.class, () -> {
            Parser.parse("deadline Submit assignment");
        });
        assertTrue(exception.getMessage().contains("/by"));
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws MyException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws MyException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }


}
