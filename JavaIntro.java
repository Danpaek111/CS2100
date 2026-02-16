// Name: DANIEL PAEK
// Computing ID: jff7dm@virginia.edu
// Homework Name: JavaIntro
// Resources used (if applicable): None

import java.util.Random;
import java.util.Scanner;

public class JavaIntro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CS 2100 HW1: JavaIntro");
        //for hilo
        System.out.println("\nhilo(max, target)");
        System.out.print("Enter max (>=1): ");
        int max = sc.nextInt();
        System.out.print("Enter target (between 1 and max): ");
        int target = sc.nextInt();
        int guesses = hilo(max, target);
        System.out.println("hilo returned: " + guesses);
        //for rpsls
        System.out.println("\nrpsls(userChoice, computerChoice)");
        System.out.print("Enter user choice (rock/paper/scissors/lizard/spock): ");
        String userChoice = sc.next();
        System.out.print("Enter computer choice (rock/paper/scissors/lizard/spock): ");
        String computerChoice = sc.next();
        int rpsResult = rpsls(userChoice, computerChoice);
        System.out.println("rpsls returned: " + rpsResult + (rpsResult == 1 ? " (user wins)" : (rpsResult == 0 ? " (computer wins)" : " (tie/unknown)")));
        //for fuzzbizz
        System.out.println("\nfuzzbizz(one, two, input)");
        System.out.print("Enter divisor one: ");
        int one = sc.nextInt();
        System.out.print("Enter divisor two: ");
        int two = sc.nextInt();
        System.out.print("Enter input number: ");
        int input = sc.nextInt();
        String fb = fuzzbizz(one, two, input);
        System.out.println("fuzzbizz returned: " + fb);
        //for countRuns
        System.out.println("\ncountRuns(numbers)");
        System.out.print("Enter array length: ");
        int n = sc.nextInt();
        int[] numbers = new int[Math.max(n, 0)];
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter element " + i + ": ");
            numbers[i] = sc.nextInt();
        }
        int runs = countRuns(numbers);
        System.out.println("countRuns returned: " + runs);
        sc.close();
        System.out.println("\nDone.");
    }

    //hilo
    public static int hilo(int max, int target) {
        Random rand = new Random();
        int low = 1;
        int high = max;
        int count = 0;

        while (low <= high) {
            int guess = rand.nextInt(high - low + 1) + low; // random inclusive
            count++;
            if (guess == target) {
                return count;
            } else if (guess < target) {
                low = guess + 1;
            } else { // guess > target
                high = guess - 1;
            }
        }
        //this shouldn't ever happen, only here for return statement
        return -1;
    }

    //rpsls
    public static int rpsls(String userChoice, String computerChoice) {
        if (userChoice == null || computerChoice == null) return -1;

        String u = userChoice.trim().toLowerCase();
        String c = computerChoice.trim().toLowerCase();

        if (u.equals(c)){
            return -1;
        }
        if (beats(u, c)){
            return 1; //beats, a helper for rpsls, will be called
        }
        if (beats(c, u)){
            return 0;
        }
        //only if no winner determined
        return -1;
    }
    //beats used to return true if a beats b
    private static boolean beats(String a, String b) {
        return (a.equals("scissors") && (b.equals("paper") || b.equals("lizard")))
                || (a.equals("paper") && (b.equals("rock") || b.equals("spock")))
                || (a.equals("rock") && (b.equals("lizard") || b.equals("scissors")))
                || (a.equals("lizard") && (b.equals("spock") || b.equals("paper")))
                || (a.equals("spock") && (b.equals("scissors") || b.equals("rock")));
    }

    //fuzzbizz
    public static String fuzzbizz(int one, int two, int input) {
        // Assuming not dividing by zero in mod
        if (one == 0 || two == 0){
            return "none";
        }
        boolean divOne = (input % one == 0);
        boolean divTwo = (input % two == 0);
        if (divOne && divTwo) {
            return "fuzzbizz";
        }
        if (divOne) {
            return "fuzz";
        }
        if (divTwo) {
            return "bizz";
        }
        return "none";
    }

    //countRuns
    public static int countRuns(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return 0;
        }
        int runs = 0;
        boolean inRun = false;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == numbers[i - 1]) {
                if (!inRun) {
                    runs++;
                    inRun = true;
                }
            }
            else {
                //for if run ended or not yet in a run
                inRun = false;
            }
        }
        return runs;
    }
}
