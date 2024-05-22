import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

// Клас, що представляє геометричну фігуру - Вектор
class Vector {
    private double magnitude; // Оголошення змінної "Модуль вектора"
    private double angle; // Оголошення змінної "Кут вектора з віссю OХ"

    // Конструктор, що генерує випадкові полярні координати вектора
    public Vector() {
        Random random = new Random();
        this.magnitude = random.nextDouble() * 100; // Генеруємо модуль від 0 до 100
        this.angle = random.nextDouble() * 360; // Генеруємо кут від 0 до 360
    }

    // Метод для обчислення координат кінця вектора
    public double[] calculateEndCoordinates() {
        double[] endCoordinates = new double[2];
        endCoordinates[0] = magnitude * Math.cos(Math.toRadians(angle)); // x = r * cos(θ)
        endCoordinates[1] = magnitude * Math.sin(Math.toRadians(angle)); // y = r * sin(θ)
        return endCoordinates;
    }

    // Перевизначений метод для виведення даних про вектор
    @Override 
    public String toString() {
        double[] endCoordinates = calculateEndCoordinates();
        return "Модуль вектора: " + magnitude + ", Кут: " + angle + ", X: " + endCoordinates[0] + ", Y: " + endCoordinates[1];
    }
}

// Клас, що представляє хеш-таблицю з роздільним зв'язуванням
class SeparateChainingHashTable {
    private ArrayList<Vector>[] table; // Динамічний масив списків елементів хеш-таблиці

    // Конструктор, ініціалізує масив списків заданого розміру
    public SeparateChainingHashTable(int size) {
        this.table = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();
        }
    }

    // Метод вставки елементу у хеш-таблицю з урахуванням колізій
    public boolean insert(Vector vector) {
        int hash = hashCode(vector); // Отримати хеш-код елементу

        if (table[hash].size() < 3) { // Обмеження на кількість елементів в одній позиції таблиці
            table[hash].add(vector); // Додати елемент у список
            return true; // Успішно вставлено
        }

        return false; // Колізія не вирішена
    }

    // Метод обчислення хеш-коду елементу
    private int hashCode(Vector vector) {
        // Обчислення хеш-коду за допомогою хеш-функції ділення за координатою X
        return Math.abs((int) Math.floor(vector.calculateEndCoordinates()[0])) % table.length;
    }

    // Метод виведення вмісту хеш-таблиці
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("Null"); // Позиція порожня
            } else {
                for (Vector vector : table[i]) {
                    System.out.println(i + ": " + vector); // Вивести елемент
                }
            }
        }
    }
    // Метод видалення елементів з хеш-таблиці за заданим критерієм
    public void removeByYCoordinate(double yCoord) {
        for (int i = 0; i < table.length; i++) {
            if (!table[i].isEmpty()) {
                // Використовуємо ітератор для безпечного видалення елементів
                Iterator<Vector> iterator = table[i].iterator();
                while (iterator.hasNext()) {
                    Vector vector = iterator.next();
                    if (vector.calculateEndCoordinates()[1] < yCoord) {
                        iterator.remove(); // Видалити елемент
                        System.out.println("Видалено елемент на позицiї " + i);
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введiть розмiр хеш-таблицi: ");
        int size = scanner.nextInt();
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable(size); // Створення хеш-таблиці

        // Вставка випадкових векторів у хеш-таблицю
        for (int i = 0; i < size; i++) {
            Vector vector = new Vector(); // Створення нового вектора
            boolean inserted = hashTable.insert(vector); // Вставка в хеш-таблицю
            if (!inserted) {
                System.out.println("Failed to insert vector: " + vector);
            }
        }

        // Виведення вмісту хеш-таблиці
        System.out.println("\nХеш-таблиця:");
        hashTable.printTable();

        // Введення значення координати Х
        System.out.print("\nВведiть значення координати Y для видалення: ");
        double yCoord = scanner.nextDouble();

        // Видалення елементів з хеш-таблиці за заданим критерієм (координата Х менше введеного значення)
        System.out.println("\nВидалення елементiв з координатою Y менше " + yCoord + ":");
        hashTable.removeByYCoordinate(yCoord);

        // Виведення вмісту хеш-таблиці після видалення
        System.out.println("\nХеш-таблиця пiсля видалення:");
        hashTable.printTable();

        scanner.close();
    }
}
