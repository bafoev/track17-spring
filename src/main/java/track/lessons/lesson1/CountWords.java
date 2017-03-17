package track.lessons.lesson1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * Задание 1: Реализовать два метода
 * <p>
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 * <p>
 * <p>
 * Пример файла - words.txt в корне проекта
 * <p>
 * ******************************************************************************************
 * Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 * <p>
 * Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 * <p>
 * ******************************************************************************************
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     *
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */

    public long countNumbers(File file) throws Exception {


        long sum = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                sum += scanner.nextInt();
                scanner.nextInt();
            } else {
                scanner.nextLine();
            }
        }
        return sum;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     *
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        StringBuilder str1 = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                scanner.nextLine();
            } else {
                String next = scanner.nextLine();
                if (!next.equals("")) {
                    str1.append(next + " ");
                }
            }

        }
        return str1.toString().trim();
    }
}

