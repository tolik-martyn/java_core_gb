package hw4;

/**
 * Класс MyArrayDataException является подклассом MyArrayException и представляет исключение,
 * возникающее при обнаружении некорректных данных (например, текст вместо числа) в элементах массива.
 */
class MyArrayDataException extends MyArrayException {
    /**
     * @param row Строка, где обнаружены некорректные данные.
     * @param col Столбец, где обнаружены некорректные данные.
     */
    public MyArrayDataException(int row, int col) {
        super("Неверные данные в ячейке с индексами: [" + row + "][" + col + "]");
    }
}
