package calculator.utils;

import java.io.*;
import java.util.Scanner;

public class InputReader{
    private final Scanner scanner;

    public InputReader(String fileName) throws FileNotFoundException{
        FileInputStream file = new FileInputStream(fileName);
        this.scanner = new Scanner(file);
    }

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine(){
        if (scanner.hasNextLine()){
            return scanner.nextLine();
        }
        return null;
    }


}
