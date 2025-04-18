package calculator.utils;

import calculator.exception.CalculatorIOException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    @Test
    void testReadLineFromFile() throws Exception {
        // Создаём временный файл с содержимым
        File tempFile = File.createTempFile("testInput", ".txt");
        Files.write(tempFile.toPath(), "first line\nsecond line".getBytes());

        try (InputReader reader = new InputReader(tempFile.getAbsolutePath())) {
            assertEquals("first line", reader.readLine());
            assertEquals("second line", reader.readLine());
            assertNull(reader.readLine());  // конец файла
        }

        // Удаляем временный файл
        tempFile.delete();
    }

    @Test
    void testFileNotFoundThrows() {
        assertThrows(CalculatorIOException.class, () -> new InputReader("non_existent_file.txt"));
    }

    @Test
    void testReadLineFromSystemIn() {
        // Подменяем System.in на ByteArrayInputStream
        String input = "line1\nline2\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputReader reader = new InputReader();  // вызов конструктора без параметров
        assertEquals("line1", reader.readLine());
        assertEquals("line2", reader.readLine());
        assertNull(reader.readLine());

        // Возвращаем исходный System.in
        System.setIn(originalIn);
    }
}
