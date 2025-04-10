package calculator.command;

import calculator.context.ExecutableContex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CommentCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) {
        String comment = String.join(" ", args);
        logger.info("Executing comment command: {}", comment);
    }
}