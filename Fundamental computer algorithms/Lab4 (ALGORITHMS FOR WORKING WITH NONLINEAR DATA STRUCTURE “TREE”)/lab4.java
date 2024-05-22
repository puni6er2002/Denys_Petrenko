class Student {
    String lastName;
    String firstName;
    int course;
    int studentId;
    boolean armyService;

    public Student(String lastName, String firstName, int course, int studentId, boolean armyService) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.studentId = studentId;
        this.armyService = armyService;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %d курс, ID: %d, Служив: %b)", lastName, firstName, course, studentId, armyService);
    }
}

class TreeNode {
    Student student;
    TreeNode left;
    TreeNode right;

    public TreeNode(Student student) {
        this.student = student;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(Student student) {
        root = insertRec(root, student);
        System.out.println("Вмiст бiнарного дерева пiсля додавання:");
        printTree(root, "", true);
        System.out.println();
    }
    
    // Метод для відображення бінарного дерева у вигляді дерева консольного виводу
    public void printTree(TreeNode node, String indent, boolean isLeft) {
        if (node != null) {
            System.out.print(indent);
            if (isLeft)
                System.out.print("<-- ");
            else
                System.out.print("-->  ");
            System.out.println(node.student);
            printTree(node.right, indent + (isLeft ? "│   " : "    "), false);
            printTree(node.left, indent + (isLeft ? "│   " : "    "), true);
        }
    }

    private TreeNode insertRec(TreeNode root, Student student) {
        if (root == null) {
            root = new TreeNode(student);
            return root;
        }

        if (student.studentId < root.student.studentId)
            root.left = insertRec(root.left, student);
        else if (student.studentId > root.student.studentId)
            root.right = insertRec(root.right, student);

        return root;
    }

    // Прямий обхід дерева для виведення в консоль
    public void preorderTraversalPrint(TreeNode node) {
        if (node != null) {
            System.out.println(node.student);
            preorderTraversalPrint(node.left);
            preorderTraversalPrint(node.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Додавання студентів у бінарне дерево
        tree.insert(new Student("Петров", "Iван", 3, 123, false));
        tree.insert(new Student("Iванов", "Петро", 2, 456, true));
        tree.insert(new Student("Сидорова", "Марiя", 4, 789, false));
        tree.insert(new Student("Ковальчук", "Олексiй", 1, 111, true));
        tree.insert(new Student("Бондаренко", "Анна", 3, 222, false));
        tree.insert(new Student("Коваленко", "Iгор", 2, 333, true));
        tree.insert(new Student("Мельник", "Олена", 4, 444, false));

        // Виведення вмісту дерева згідно з прямим обходом у консоль
        System.out.println("\nПрямий обхiд:");
        tree.preorderTraversalPrint(tree.root);
    }
}
