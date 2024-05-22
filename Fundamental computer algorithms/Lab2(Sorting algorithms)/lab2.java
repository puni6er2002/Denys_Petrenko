class Student {
    // Поля класу
    String lastName;
    String firstName;
    String group;
    int group_num;
    String faculty;

    // Конструктор класу
    public Student(String lastName, String firstName, String group, int group_num, String faculty) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.group_num = group_num;
        this.faculty = faculty;
    }
    
    // Перевизначений метод toString() для представлення об'єкта у вигляді рядка
    public String toString() {
        return lastName + " " + firstName + ", Група: " + group + ", " + "Номер групи: " + group_num + ", Факультет: " + faculty;
    }
}

public class lab2 {
    //Реалізація сортування вставкою
    public static void insertionSort(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].lastName.compareTo(key.lastName) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //Виід масиву студентів
    public static void printArray(Student[] arr) {
        for (Student student : arr) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Ініціалізуємо масив студентів
        Student[] students = {
                new Student("Петренко", "Денис", "IПЗ", 23121, "Iнженерiя програмного забезпечення"),
                new Student("Васькiвський", "Олександр", "IПЗ", 19121, "Iнженерiя програмного забезпечення"),
                new Student("Баранiченко", "Андрiй", "КА", 95, "Системний аналiз i управлiння"),
                new Student("Сидоров", "Олександр", "ДА", 92, "Штучний iнтелект"),
                new Student("Гнатюк", "Максим", "АР", 7555, "Автоматизацiя комп'ютерних систем"),
                new Student("Сергiєнко", "Анна", "ДА", 15, "Фiзика"),
                new Student("Василенко", "Свiтлана", "ВР", 69952, "Мiжнародне право")
        };

        //Виводимо масив перед сортуванням
        System.out.println("Масив студентiв перед сортуванням:");
        printArray(students);

        //Сортуємо масив
        insertionSort(students);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("");

        //Виводимо відсортований масив
        System.out.println("Масив студентiв пiсля сортування:");
        printArray(students);
    }
}
