package hw4;

/**
 * Класс MyArraySizeException является подклассом MyArrayException и представляет исключение,
 * возникающее при передаче двумерного массива неправильного размера в метод.
 */
class MyArraySizeException extends MyArrayException {
    /**
     * @param expectedRows Ожидаемое количество строк в массиве.
     * @param expectedCols Ожидаемое количество столбцов в массиве.
     * @param actualRows   Фактическое количество строк в переданном массиве.
     * @param actualCols   Фактическое количество столбцов в переданном массиве.
     * @param wrongColumn  Номер столбца с неправильным количеством элементов.
     */
    public MyArraySizeException(int expectedRows, int expectedCols, int actualRows, int actualCols, int wrongColumn) {
        super("Неверный размер массива.\nОжидается строк: " + expectedRows + ", получено: " + actualRows +
                ".\nОжидается столбцов: " + expectedCols + ", получено: " + actualCols +
                ".\nНеправильное количество элементов в столбце c индексом: " + wrongColumn);
    }
}