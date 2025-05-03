package utils;

import java.io.FileWriter;
import java.io.IOException;

import lab2.FunctionSystem;
import log.Logarithm;
import static log.Logarithm.ln;
import trig.Trigonometry;

public class CSVExporter {
    private static final String SEPARATOR = ";";

    public static void exportModule(String moduleName, double startX, double endX, double step) {
        String fileName = String.format("%.2f_Результаты_модуля_(%s).csv", startX, moduleName);
        
        try (FileWriter writer = new FileWriter(fileName)) {
            // Заголовок файла
            writer.write("x" + SEPARATOR + "result\n");
            
            // Генерация данных
            for (double x = startX; x <= endX; x += step) {
                double result = computeModule(moduleName, x);
                writer.write(x + SEPARATOR + result + "\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static double computeModule(String moduleName, double x) {
        switch (moduleName.toLowerCase()) {
            case "sin":
                return Trigonometry.sin(x);
            case "cos":
                return Trigonometry.cos(x);
            case "ln":
                return ln(x);
            case "system":
                FunctionSystem system = new FunctionSystem();
                return system.calculate(x);
            case "csc":
                return Trigonometry.csc(x);
            case "sec":
                return Trigonometry.sec(x);
            default:
                throw new IllegalArgumentException("Неизвестный модуль: " + moduleName);
        }
    }
}