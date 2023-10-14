package hw1.formatting;

/**
 * Класс для форматированного вывода результатов
 */
public class Formatter {
    /**
     * Метод для форматированного вывода результатов
     *
     * @param operation название операции
     * @param result результат операции
     */
    public void displayResult(String operation, double result) {
        System.out.printf("%s: %.2f%n", operation, result);
    }
}
