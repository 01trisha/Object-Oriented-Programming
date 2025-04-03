package calculator.command;

import calculator.context.ExecutableContex;

public class CommentCommand implements Command{
    @Override
    public void execute(ExecutableContex context, String[] args) {
        String comment = String.join(" ", args);
    }
}
