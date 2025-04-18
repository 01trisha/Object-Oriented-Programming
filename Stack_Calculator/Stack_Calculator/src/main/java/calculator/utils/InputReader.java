package calculator.utils;

import calculator.exception.CalculatorIOException;

import java.io.*;
import java.util.Scanner;

public class InputReader implements AutoCloseable{
    private final Scanner scanner;

    public InputReader(String fileName) throws CalculatorIOException {
        try {
            FileInputStream file = new FileInputStream(fileName);
            this.scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            throw new CalculatorIOException("File " + fileName + " is not found");
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
