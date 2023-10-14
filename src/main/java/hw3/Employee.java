package hw3;

import java.text.DecimalFormat;

/**
 * Абстрактный класс работника
 */
abstract class Employee implements Comparable<Employee> {
    private String name;
    private double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public abstract double calculateAverageMonthlySalary();

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.calculateAverageMonthlySalary(), other.calculateAverageMonthlySalary());
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Сотрудник: " + getName() + ", среднемесячная з/п: " + df.format(calculateAverageMonthlySalary());
    }
}
