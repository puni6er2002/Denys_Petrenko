import java.util.Arrays;
import java.util.Random;

// Завдання 3 рівня
public class Main {

    public static void main(String[] args) {
        int[] sizes = {10000}; // Розміри масивів
        int numberOfArrays = 10; // Кількість масивів для кожного розміру

        // Масив для зберігання часу виконання для кожного розміру та типу набору даних
        long[][][] executionTimes = new long[sizes.length][3][2]; // 3 типи наборів даних: відсортований, випадковий, зворотній порядок

        // Генератор випадкових чисел
        Random random = new Random();

        // Вимірюємо час виконання для кожного розміру масиву та кожного типу набору даних
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < numberOfArrays; j++) {
                // Генеруємо дані для кожного типу набору даних
                int[] dataSorted = generateSortedArray(sizes[i]);
                int[] dataRandom = generateRandomArray(sizes[i], random);
                int[] dataReverse = generateReverseSortedArray(sizes[i]);

                // Вимірюємо час виконання для кожного типу набору даних
                measureExecutionTime(dataSorted, executionTimes[i][0]);
                measureExecutionTime(dataRandom, executionTimes[i][1]);
                measureExecutionTime(dataReverse, executionTimes[i][2]);
            }
            // Зберігаємо середній час виконання для даного розміру масиву та кожного типу набору даних
            for (int k = 0; k < 3; k++) {
                executionTimes[i][k][0] /= numberOfArrays;
                executionTimes[i][k][1] /= numberOfArrays;
            }
        }

        // Виводимо результати
        System.out.println("Результати:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("Size: " + sizes[i]);
            System.out.println("Sorted Order: Time for Merge Sort: " + executionTimes[i][0][0] + " nanoseconds");
            System.out.println("Random Order: Time for Merge Sort: " + executionTimes[i][1][0] + " nanoseconds");
            System.out.println("Reverse Order: Time for Merge Sort: " + executionTimes[i][2][0] + " nanoseconds");
        }
    }

    // Вимірює час виконання сортування для даного масиву
    private static void measureExecutionTime(int[] data, long[] executionTime) {
        long startTime = System.nanoTime();
        mergeSort(data);
        long endTime = System.nanoTime();
        executionTime[0] += endTime - startTime;
    }

    // Генерує відсортований масив
    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    // Генерує випадковий масив
    private static int[] generateRandomArray(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // Генерує масив в зворотньому порядку
    private static int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    // Реалізація сортування висхідним злиттям (Merge sort)
    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    // Bottom-down mergesort для підмасиву arr[low..high]
    private static void mergeSort(int[] arr, int[] aux, int low, int high) {
        if (low >= high) return;
        int mid = low + (high - low) / 2;
        mergeSort(arr, aux, low, mid);
        mergeSort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    // Злиття двох підмасивів arr[low..mid] та arr[mid+1..high]
    private static void merge(int[] arr, int[] aux, int low, int mid, int high) {
        // Копіюємо елементи в допоміжний масив
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }
        // Злиття допоміжних масивів назад у arr
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if      (i > mid)              arr[k] = aux[j++]; // Перший підмасив закінчився
            else if (j > high)             arr[k] = aux[i++]; // Другий підмасив закінчився
            else if (aux[j] < aux[i])     arr[k] = aux[j++]; // Елемент з другого підмасиву менший
            else                           arr[k] = aux[i++]; // Елемент з першого підмасиву менший
        }
    }
}
