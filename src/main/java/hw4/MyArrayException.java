package hw4;

/**
 * Класс MyArrayException является базовым классом для всех пользовательских исключений,
 * связанных с обработкой двумерных массивов.
 */
class MyArrayException extends Exception {

    public MyArrayException(String message) {
        super(message);
    }
}
