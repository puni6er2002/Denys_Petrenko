import java.util.Arrays;
import java.util.Random;

// Завдання 1 рівня
public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 10000, 1000000}; // Розміри масивів N, N^2, N^3
        int numberOfArrays = 10; // Кількість масивів для кожного розміру

        // Масив для зберігання часу виконання для кожного розміру
        long[] executionTimes = new long[sizes.length];

        // Вимірюємо час виконання для кожного розміру масиву
        for (int i = 0; i < sizes.length; i++) {
            long totalTime = 0;
            for (int j = 0; j < numberOfArrays; j++) {
                int[] data = generateRandomArray(sizes[i]);
                long startTime = System.nanoTime();
                mergeSort(data);
                long endTime = System.nanoTime();
                totalTime += endTime - startTime;
            }
            // Зберігаємо середній час виконання для даного розміру масиву
            executionTimes[i] = totalTime / numberOfArrays;
        }
        System.out.println("Завдання 1 рiвня:");
        // Виводимо результат
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("Size: " + sizes[i] + ", Time: " + executionTimes[i] + " nanoseconds");
        }
    }

    // Сортування злиттям (bottom-down)
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
