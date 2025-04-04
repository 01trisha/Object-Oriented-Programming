package calculator.utils;

import calculator.exception.CalculatorException;

import java.io.*;
import java.util.Scanner;

public class InputReader implements AutoCloseable{
    private final Scanner scanner;

    public InputReader(String fileName) throws CalculatorException{
        try {
            FileInputStream file = new FileInputStream(fileName);
            this.scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            throw new CalculatorException("File " + fileName + " is not found", e);
        }
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
    @Override
    public void close(){
        scanner.close();
    }


}
