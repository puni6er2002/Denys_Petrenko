import java.util.ArrayList;

public class Main {

    public static class Student {
        private static int nextId = 1; // Змінна для створення унікального ID для кожного студента
        private int studentId; // ID студента
        private String lastName;
        private String firstName;
        private int dayOfBirth;
        private int monthOfBirth;
        private int yearOfBirth;
        private String hobby;

        public Student(String lastName, String firstName, int dayOfBirth, int monthOfBirth, int yearOfBirth, String hobby) {
            this.studentId = nextId++; // Присвоєння ID та інкрементування для наступного студента
            this.lastName = lastName;
            this.firstName = firstName;
            this.dayOfBirth = dayOfBirth;
            this.monthOfBirth = monthOfBirth;
            this.yearOfBirth = yearOfBirth;
            this.hobby = hobby;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getDayOfBirth() {
            return dayOfBirth;
        }

        public void setDayOfBirth(int dayOfBirth) {
            this.dayOfBirth = dayOfBirth;
        }

        public int getMonthOfBirth() {
            return monthOfBirth;
        }

        public void setMonthOfBirth(int monthOfBirth) {
            this.monthOfBirth = monthOfBirth;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        @Override
        public String toString() {
            return String.format("(%d) (%s, %s, Дата народження: %d/%d/%d, Хобi: %s)", studentId, lastName, firstName, dayOfBirth, monthOfBirth, yearOfBirth, hobby);
        }
    }

    public static ArrayList<Student> sequentialSearch(Student[] students, String hobby) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getHobby().equals(hobby)) {
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    public static void removeSummerTourismStudents(Student[] students) {
        ArrayList<Student> updatedStudents = new ArrayList<>();
        for (Student student : students) {
            if (!(student.getMonthOfBirth() >= 6 && student.getMonthOfBirth() <= 8) || !student.getHobby().equals("туризм")) {
                updatedStudents.add(student);
            }
        }
        students = updatedStudents.toArray(new Student[0]);
    }

    public static void main(String[] args) {
        // Ініціалізуємо масив студентів
        Student[] students = {
            new Student("Петров", "Iван", 15, 6, 2000, "туризм"),
            new Student("Iванов", "Петро", 20, 8, 2001, "малювання"),
            new Student("Сидоров", "Олег", 12, 5, 2002, "музика"),
            new Student("Ковальчук", "Марiя", 10, 10, 2000, "спорт"),
            new Student("Василенко", "Анна", 5, 2, 1999, "лiтература"),
            new Student("Ткаченко", "Олександр", 18, 7, 2001, "театр"),
            new Student("Савченко", "Катерина", 25, 12, 1998, "малювання"),
            new Student("Павленко", "Юрiй", 8, 9, 2000, "спорт"),
            new Student("Мельник", "Оксана", 30, 4, 2001, "музика"),
            new Student("Попов", "Вадим", 14, 3, 1999, "спорт"),
            new Student("Козлов", "Iгор", 7, 8, 2002, "туризм"),
            new Student("Григоренко", "Тетяна", 3, 1, 2000, "лiтература"),
            new Student("Бойко", "Роман", 28, 8, 2001, "малювання"),
            new Student("Лисенко", "Iрина", 19, 6, 1999, "спорт"),
            new Student("Мороз", "Андрiй", 23, 9, 2000, "музика"),
            new Student("Шевченко", "Вiктор", 11, 7, 2002, "театр"),
            new Student("Коваленко", "Олена", 9, 5, 2001, "туризм"),
            new Student("Павлюк", "Наталiя", 17, 4, 1999, "малювання"),
            new Student("Сергiєнко", "Максим", 22, 11, 2000, "спорт"),
            new Student("Кучеренко", "Лiдiя", 6, 10, 2002, "музика")
        };

        // Виводимо вміст попереднього масиву студентів
        System.out.println("Попереднiй масив студентiв:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Виконуємо пошук студентів за заданим хобі (туризм)
        ArrayList<Student> foundStudents = sequentialSearch(students, "туризм");
        if (!foundStudents.isEmpty()) {
            System.out.println("\nЗнайдено студентiв з хобi туризм:");
            for (Student student : foundStudents) {
                System.out.println(student);
            }
        } else {
            System.out.println("\nСтудентiв з вказаним хобi не знайдено.");
        }

        // Видаляємо студентів, народжених улітку та захоплюються туризмом
        ArrayList<Student> updatedStudents = new ArrayList<>();
        for (Student student : students) {
            if (!(student.getMonthOfBirth() >= 6 && student.getMonthOfBirth() <= 8) || !student.getHobby().equals("туризм")) {
                updatedStudents.add(student);
            }
        }
        students = updatedStudents.toArray(new Student[0]);
        
        // Виводимо вміст масиву після видалення
        System.out.println("\nМасив пiсля видалення студентiв, що народилися лiтом та захоплюються туризмом:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
