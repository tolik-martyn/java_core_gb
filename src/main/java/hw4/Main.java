package hw4;

public class Main {

    public static void main(String[] args) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = processArray(array);
            System.out.println("Сумма элементов массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Метод processArray() принимает двумерный строковый массив и выполняет обработку его элементов,
     * преобразуя их в int и подсчитывая их сумму.
     *
     * @param array Двумерный строковый массив, который требуется обработать.
     * @return Сумма всех чисел в массиве.
     * @throws MyArraySizeException Если переданный массив не соответствует размеру 4x4.
     * @throws MyArrayDataException Если в массиве обнаружены некорректные данные (не числа).
     */
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        if (array.length != 4) {
            throw new MyArraySizeException(4, 4, array.length, array[0].length, -1);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException(4, 4, array.length, array[i].length, i);
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}
