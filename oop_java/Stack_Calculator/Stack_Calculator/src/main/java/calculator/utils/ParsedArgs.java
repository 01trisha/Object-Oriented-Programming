package calculator.utils;

public class ParsedArgs {
    private final String command;
    private final String[] args;

    public ParsedArgs(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}