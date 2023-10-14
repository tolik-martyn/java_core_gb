package hw2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final int WIN_COUNT = 4;
    private static final int SIZE_X = 4;
    private static final int SIZE_Y = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static Scanner scanner;
    private static char[][] field;
    private static final Random random = new Random();

    private static Scanner getScanner() {
        if (scanner == null) scanner = new Scanner(System.in);
        return scanner;
    }

    public static void main(String[] args) {
        do {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Победил игрок!")) break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Победил компьютер!")) break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
        } while (getScanner().next().equalsIgnoreCase("Y"));
    }

    /**
     * Инициализация игрового поля
     */
    private static void initialize() {

        System.out.println("Новая игра:");
        field = new char[SIZE_X][SIZE_Y];

        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {

        System.out.print("+");
        for (int i = 0; i < SIZE_Y * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();
        for (int x = 0; x < SIZE_X; x++) {

            System.out.print(x + 1 + "|");
            for (int y = 0; y < SIZE_Y; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < SIZE_Y * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Метод проверки состояния игры
     *
     * @param c   фишка игрока
     * @param str победный слоган
     * @return состояние игры (true - игра завершена!)
     */
    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка победы
     *
     * @param c фишка игрока
     * @return результат проверки
     */
    static boolean checkWin(char c) {
        // Проверка по горизонталям
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y <= SIZE_Y - WIN_COUNT; y++) {
                boolean hasWin = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (field[x][y + i] != c) {
                        hasWin = false;
                        break;
                    }
                }
                if (hasWin) {
                    return true;
                }
            }
        }

        // Проверка по вертикалям
        for (int x = 0; x <= SIZE_X - WIN_COUNT; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                boolean hasWin = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (field[x + i][y] != c) {
                        hasWin = false;
                        break;
                    }
                }
                if (hasWin) {
                    return true;
                }
            }
        }

        // Проверка по диагонали вправо-вниз
        for (int x = 0; x <= SIZE_X - WIN_COUNT; x++) {
            for (int y = 0; y <= SIZE_Y - WIN_COUNT; y++) {
                boolean hasWin = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (field[x + i][y + i] != c) {
                        hasWin = false;
                        break;
                    }
                }
                if (hasWin) {
                    return true;
                }
            }
        }

        // Проверка по диагонали вправо-вверх
        for (int x = 0; x <= SIZE_X - WIN_COUNT; x++) {
            for (int y = WIN_COUNT - 1; y < SIZE_Y; y++) {
                boolean hasWin = true;
                for (int i = 0; i < WIN_COUNT; i++) {
                    if (field[x + i][y - i] != c) {
                        hasWin = false;
                        break;
                    }
                }
                if (hasWin) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверка на ничью
     *
     * @return результат проверки
     */
    private static boolean checkDraw() {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Обработка хода игрока
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.printf("Ход игрока (введите координаты через пробел):" +
                    "%nX (от 1 до %d) и Y (от 1 до %d)  >>>%n", SIZE_X, SIZE_Y);

            x = getScanner().nextInt() - 1;
            y = getScanner().nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        int x, y;
        for (x = 0; x < SIZE_X; x++) {
            for (y = 0; y < SIZE_Y; y++) {
                if (isCellEmpty(x, y)) {
                    field[x][y] = DOT_AI; // Сначала поставим фишку компьютера на пустую клетку
                    if (checkWin(DOT_AI)) { // Проверим, приведет ли это к победе компьютера
                        System.out.printf("Ход компьютера: X (%d) и Y (%d)%n", x + 1, y + 1);
                        return;
                    }
                    field[x][y] = DOT_HUMAN; // Если первый if не сработал, то поставим фишку игрока на это место
                    if (checkWin(DOT_HUMAN)) { // Проверим, приведет ли это к победе игрока
                        field[x][y] = DOT_AI; // Если да, то сделаем ход компьютера на эту клетку
                        System.out.printf("Ход компьютера: X (%d) и Y (%d)%n", x + 1, y + 1);
                        return;
                    }
                    field[x][y] = DOT_EMPTY; // Вернем клетку в исходное состояние
                }
            }
        }

        // Если нет возможности помешать игроку победить или победить самому, выберем случайный ход
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
        System.out.printf("Ход компьютера: X (%d) и Y (%d)%n", x + 1, y + 1);
    }

    /**
     * Проверка, является ли ячейка пустой
     *
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива игрового поля)
     *
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y;
    }
}
