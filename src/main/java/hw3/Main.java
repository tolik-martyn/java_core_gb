package hw3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Создаем массив сотрудников и заполняем его
        Employee[] employees = new Employee[]{
                new FixedEmployee("Козлов", 3000),
                new HourlyEmployee("Петров", 12),
                new HourlyEmployee("Иванов", 10),
                new FixedEmployee("Сидоров", 2500),
                new HourlyEmployee("Смирнов", 15)
        };

        // Сортируем массив сотрудников по возрастанию среднемесячной заработной платы
        Arrays.sort(employees);

        // Выводим данные о сотрудниках
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
