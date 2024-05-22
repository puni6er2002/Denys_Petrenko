import java.util.Arrays;
import java.util.Random;

// Завдання 2 рівня
public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 10000, 1000000}; // Розміри масивів N, N^2, N^3
        int numberOfArrays = 10; // Кількість масивів для кожного розміру

        // Масив для зберігання часу виконання для кожного розміру
        long[][] executionTimes = new long[sizes.length][2];

        // Вимірюємо час виконання для кожного розміру масиву
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < numberOfArrays; j++) {
                int[] data = generateRandomArray(sizes[i]);

                // Реалізація порозрядного сортування
                int[] dataRadix = Arrays.copyOf(data, data.length);
                long startTimeRadix = System.nanoTime();
                radixLSDSort(dataRadix);
                long endTimeRadix = System.nanoTime();
                executionTimes[i][0] += endTimeRadix - startTimeRadix;

                // Реалізація сортування висхідним злиттям
                int[] dataMerge = Arrays.copyOf(data, data.length);
                long startTimeMerge = System.nanoTime();
                mergeSort(dataMerge);
                long endTimeMerge = System.nanoTime();
                executionTimes[i][1] += endTimeMerge - startTimeMerge;
            }
            // Зберігаємо середній час виконання для даного розміру масиву
            executionTimes[i][0] /= numberOfArrays;
            executionTimes[i][1] /= numberOfArrays;
        }

        // Виводимо результати
        System.out.println("Результати:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("Size: " + sizes[i] + "; Time for Radix LSD Sort: " + executionTimes[i][0] + " nanoseconds;     Time for Merge Sort: " + executionTimes[i][1] + " nanoseconds");
        }
    }

    // Реалізація порозрядного сортування (Radix LSD)
    public static void radixLSDSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Визначаємо мінімальне і максимальне значення
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // Виконуємо сортування по кожному розряду, починаючи з менш значущого розряду
        int exponent = 1;
        while ((maxValue - minValue) / exponent >= 1) {
            countingSortByDigit(array, 10, exponent, minValue);
            exponent *= 10;
        }
    }

    private static void countingSortByDigit(int[] array, int radix, int exponent, int minValue) {
        int bucketIndex;
        int[] buckets = new int[radix];
        int[] output = new int[array.length];

        // Ініціалізуємо корзини
        Arrays.fill(buckets, 0);

        // Рахуємо кількість елементів у кожній корзині
        for (int i = 0; i < array.length; i++) {
            bucketIndex = (array[i] / exponent) % radix;
            buckets[bucketIndex]++;
        }

        // Обчислюємо кумулятиви
        for (int i = 1; i < radix; i++) {
            buckets[i] += buckets[i - 1];
        }

        // Переміщуємо записи
        for (int i = array.length - 1; i >= 0; i--) {
            bucketIndex = (array[i] / exponent) % radix;
            output[--buckets[bucketIndex]] = array[i];
        }

        // Копіюємо назад
        System.arraycopy(output, 0, array, 0, array.length);
    }

    // Реалізація сортування висхідним злиттям (Merge sort)
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] aux, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, aux, low, mid);
            mergeSort(arr, aux, mid + 1, high);
            merge(arr, aux, low, mid, high);
        }
    }

    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        // Копіюємо елементи в допоміжний масив
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }
        // Злиття допоміжних масивів назад у arr
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    // Генерація випадкового масиву заданого розміру
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000); // Генеруємо випадкові числа від 0 до 999
        }
        return array;
    }
}
