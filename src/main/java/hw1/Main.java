package hw1;

import hw1.formatting.Formatter;
import hw1.operations.MathOperations;

public class Main {
    public static void main(String[] args) {
        double num1 = 10;
        double num2 = 5;

        MathOperations mathOperations = new MathOperations();
        Formatter formatter = new Formatter();

        double sum = mathOperations.add(num1, num2);
        double difference = mathOperations.subtract(num1, num2);
        double product = mathOperations.multiply(num1, num2);
        double quotient = mathOperations.divide(num1, num2);

        formatter.displayResult("Сумма", sum);
        formatter.displayResult("Разность", difference);
        formatter.displayResult("Произведение", product);
        formatter.displayResult("Частное", quotient);
    }
}
