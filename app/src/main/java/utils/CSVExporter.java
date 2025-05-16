package utils;

import java.io.FileWriter;
import java.io.IOException;

import lab2.FunctionSystem;
import log.Logarithm;
import trig.Trigonometry;

public class CSVExporter {
    private static final String SEPARATOR = ";";

    private static final Trigonometry trig = new Trigonometry();
    private static final Logarithm log = new Logarithm();
    private static final FunctionSystem system = new FunctionSystem(1e-4, trig, log);

    public static void exportModule(String moduleName, double startX, double endX, double step) {
        String fileName = String.format("%.2f_Результаты_модуля_(%s).csv", startX, moduleName);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("x" + SEPARATOR + "result\n");

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
                return trig.sin(x);
            case "cos":
                return trig.cos(x);
            case "ln":
                return log.ln(x);
            case "system":
                return system.calculate(x);
            case "csc":
                return trig.csc(x);
            case "sec":
                return trig.sec(x);
            default:
                throw new IllegalArgumentException("Неизвестный модуль: " + moduleName);
        }
    }
}
