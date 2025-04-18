package calculator.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ParserLines {
    private static final Logger logger = LogManager.getLogger(ParserLines.class);

    public ParserLines(){
        logger.info("Create object of ParserLines");
    };
    public ParsedArgs parse(String line){
        
        if (line == null || line.trim().isEmpty()){
            return null;
        }

        String[] splitLine = line.split(" ");

        String command = splitLine[0];

        String[] args = Arrays.copyOfRange(splitLine, 1, splitLine.length);
        logger.debug("Parsed line: command={}, args={}", command, Arrays.toString(args));
        return new ParsedArgs(command, args);
    }
}