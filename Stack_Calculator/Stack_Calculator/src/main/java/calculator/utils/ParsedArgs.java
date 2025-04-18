package calculator.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParsedArgs {
    private static final Logger logger = LogManager.getLogger(ParsedArgs.class);
    private final String command;
    private final String[] args;

    public ParsedArgs(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        logger.debug("getCommand called");
        return command;
    }

    public String[] getArgs() {
        logger.debug("getArgs called");
        return args;
    }
}