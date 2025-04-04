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
}
