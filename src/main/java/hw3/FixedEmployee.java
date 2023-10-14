package hw3;

/**
 * Класс для работников с фиксированной оплатой
 */
class FixedEmployee extends Employee {
    public FixedEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return getMonthlySalary();
    }
}
