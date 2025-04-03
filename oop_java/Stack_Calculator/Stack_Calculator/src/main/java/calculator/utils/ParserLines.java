package calculator.utils;

import java.util.Arrays;

public class ParserLines {

    public ParsedArgs parse(String line){
        if (line == null || line.trim().isEmpty()){
            return null;
        }

        String[] splitLine = line.split(" ");

        String command = splitLine[0];

        String[] args = Arrays.copyOfRange(splitLine, 1, splitLine.length);
        
        return new ParsedArgs(command, args);
    }

    public static class ParsedArgs{
        private final String command;
        private final String[] args;

        public ParsedArgs(String command, String[] args) {
            this.command = command;
            this.args = args;
        }

        public String getCommand(){
            return command;
        }
        
        public String[] getArgs(){
            return args;
        }
    }
}
