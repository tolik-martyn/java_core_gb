package hw5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Program {

    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final String TO_SEARCH = "GeekBrains";
    private static final String BACKUP_FILE_NAME = "./backup";

    /**
     * 1.  Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
     * 2.  Написать метод, «склеивающий» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
     * 3.* Написать метод, который проверяет, присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
     * 4.* Написать метод, проверяющий, есть ли указанное слово в папке
     * ДЗ:
     * 1. Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
     * 2. Доработайте класс Tree и метод print который мы разработали на семинаре. Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
     */

    public static void main(String[] args) {

        try {
            writeFileContents("sample01.txt", 25, 3);
            writeFileContents("sample02.txt", 25, 5);

            concatenate("sample01.txt", "sample02.txt", "sample.res.txt");

            System.out.printf("Файл sample.res.txt %s\n",
                    searchInFile("sample.res.txt", TO_SEARCH) ?
                            String.format("содержит искомое слово \"%s\".", TO_SEARCH) : "не содержит искомого слова.");

            String[] fileNames = new String[10];
            for (int i = 0; i < fileNames.length; i++) {
                fileNames[i] = "file_" + i + ".txt";
                writeFileContents(fileNames[i], 100, 4);
                System.out.printf("Файл %s создан.\n", fileNames[i]);
            }

            Tree.print(new File("."), "", true);

            File rootDir = new File(".");
            File[] files = rootDir.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile()) {
                    System.out.printf("Файл %s %s\n",
                            file.getName(),
                            searchInFile(file.getName(), TO_SEARCH) ?
                                    String.format("содержит искомое слово \"%s\".", TO_SEARCH) : "не содержит искомого слова.");
                }
            }

            backupFilesInDirectory(".", BACKUP_FILE_NAME);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void backupFilesInDirectory(String sourceDir, String backupDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File backupDirectory = new File(backupDir);

        if (!backupDirectory.exists()) {
            if (backupDirectory.mkdirs()) {
                System.out.println("Резервная директория успешно создана.");
            } else {
                System.err.println("Не удалось создать резервную директорию.");
                return;
            }
        }

        File[] files = sourceDirectory.listFiles();
        assert files != null;

        for (File file : files) {
            if (file.isFile()) {
                String backupPath = backupDir + "/" + file.getName();
                Files.copy(file.toPath(), new File(backupPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("Файл %s скопирован в %s\n", file.getName(), backupPath);
            }
        }
    }

    /**
     * Метод генерации некоторой последовательности символов
     *
     * @param size кол-во символов
     * @return последовательность символов
     */
    private static String generateSymbols(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Записать последовательность символов в файл
     *
     * @param fileName название файла
     * @param size     кол-во символов
     * @throws IOException пробросить исключение
     */
    private static void writeFileContents(String fileName, int size) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(size).getBytes());
        }
    }

    private static void writeFileContents(String fileName, int size, int words) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            for (int i = 0; i < words; i++) {
                if (random.nextInt(10) == 0) {
                    fileOutputStream.write(TO_SEARCH.getBytes());
                } else {
                    fileOutputStream.write(generateSymbols(size).getBytes());
                }
            }
        }
    }

    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {

        // Поток на запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {

            // Поток на чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                int c;
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }

            // Поток на чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                int c;
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

    private static boolean searchInFile(String fileName, String search) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            byte[] searchData = search.getBytes();
            int i = 0;
            int c;
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[i]) {
                    i++;
                } else {
                    i = 0;
                    if (c == searchData[i])
                        i++;
                    continue;
                }
                if (i == searchData.length) {
                    return true;
                }
            }
            return false;
        }
    }
}
