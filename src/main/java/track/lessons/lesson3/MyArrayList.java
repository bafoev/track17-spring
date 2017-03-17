package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * <p>
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private int[] data;
    private int mcapacity = 10;


    public MyArrayList() {
        data = new int[mcapacity];

    }

    public MyArrayList(int capacity) {

        if (mcapacity < capacity) {
            mcapacity = capacity;
        }
        data = new int[mcapacity];
    }

    @Override
    public void add(int item) {
        if (length == mcapacity) {
            mcapacity = mcapacity * 4;
            int[] temp = new int[mcapacity];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
            data[length++] = item;
        } else {
            data[length++] = item;

        }

    }

    @Override
    public int remove(int idx) throws NoSuchElementException {

        if (idx < 0 || idx >= length) {
            throw new NoSuchElementException();
        } else {
            final int value = data[idx];
            System.arraycopy(data, idx + 1, data, idx, data.length - idx - 1);
            length--;
            if (mcapacity / 4 > length && mcapacity >= 20) {
                mcapacity = mcapacity / 2;
                int[] temp = new int[mcapacity];
                System.arraycopy(data, 0, temp, 0, length);
                data = temp;

            }
            return value;
        }

    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        if (idx < 0 || idx >= length) {
            throw new NoSuchElementException();
        } else {
            return data[idx];
        }
    }


    @Override
    public int size() {
        return length;
    }
}