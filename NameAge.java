import java.util.Scanner;
public class NameAge {
    public static void main(String[] args){
        String name;
        int age;
        int year;
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        name = scan.nextLine();
        System.out.println("What is your age?");
        age = scan.nextInt();
        year = 2026 - age;
        System.out.print("Hi " + name + "! You were born in ");
        System.out.println(year + " or " + (year-1));
    }
}
