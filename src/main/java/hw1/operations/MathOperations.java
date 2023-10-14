package hw1.operations;

/**
 * Класс с математическими операциями
 */
public class MathOperations {
    /**
     * Функция суммирования двух чисел
     *
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return сумма a и b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Функция вычетания двух чисел
     *
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return разность a и b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Функция умножения двух чисел
     *
     * @param a первый множитель
     * @param b втрой множитель
     * @return произведение a и b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Функция деления двух чисел
     *
     * @param a делимое
     * @param b делителем
     * @return частное a и b
     */
    public double divide(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Деление на ноль не допускается.");
        }
    }
}
