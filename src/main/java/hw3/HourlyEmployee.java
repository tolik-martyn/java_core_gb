package hw3;

/**
 * Класс для работников с почасовой оплатой
 */
class HourlyEmployee extends Employee {
    private double hourlyRate;

    public HourlyEmployee(String name, double hourlyRate) {
        super(name, 0);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateAverageMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
}
