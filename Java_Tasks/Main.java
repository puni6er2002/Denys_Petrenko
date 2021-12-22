import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // First task
        Task1 task1 = new Task1();
        System.out.println("First task");
        System.out.println("Output:");
        System.out.println(task1.getIntegersFromList(Arrays.asList(1,2,'a','b')));
        System.out.println(task1.getIntegersFromList(Arrays.asList(1,2,'a','b',0,15)));
        System.out.println(task1.getIntegersFromList(Arrays.asList(1,2,'a','b',"aasf",'1',"123",231)));
        // Second task
        Task2 task2 = new Task2();
        System.out.println("\n\nSecond task");
        System.out.println("Output:");
        System.out.println(task2.first_NonRepeatingLetter("SsTtRrEe"));
        System.out.println(task2.first_NonRepeatingLetter("stress"));
        System.out.println(task2.first_NonRepeatingLetter("sTreSS"));
        // Third task
        Task3 task3 = new Task3();
        System.out.println("\n\nThird task");
        System.out.println("Output:");
        System.out.printf("16 => %d\n", task3.digitalRoot(16));
        System.out.printf("942 => %d\n", task3.digitalRoot(942));
        System.out.printf("132189 => %d\n", task3.digitalRoot(132189));
        System.out.printf("493193 => %d\n", task3.digitalRoot(493193));
        // Fourth task
        Task4 task4 = new Task4();
        int[] array = { 1, 3, 6, 2, 2, 0, 4, 5 };
        System.out.println("\n\nFourth task");
        System.out.println("Output:");
        System.out.println("Result (for loop): " + task4.countnumber_OfPairs(array, 5, false));
        System.out.println("Result (for stream): " + task4.countnumber_OfPairs(array, 5, true));
        // Fifth task
        Task5 task5 = new Task5();
        System.out.println("\n\nFifth task:");
        String str = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
        System.out.println("Result: " + task5.sorteOrder(str));
        }
}
