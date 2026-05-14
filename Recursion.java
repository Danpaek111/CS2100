public class Recursion {
    /* theValue(1, 7, 7)  This method keeps shrinking the range until
     it finds the largest C such that C*C <= N */
    /* Trace DispOct with N = 100
    1 % 8 = 1
    12 % 8 = 4
    100 % 8 = 4
    Prints:
    1
    4
    4
     */
    /* Trace R(X=5, Y=3)
    Code prints once before the recursive call,
    then again after the call returns.
    Prints:
    6 2
    7 1
    8 0
    8 0
    7 1
    6 2
     */
    /* What does Mystery(n) do? This is the Fibonacci sequence with:
    F(1)=1, F(2)=1
    So it returns Fibonacci(n)
     */
    /* What does Rose(n) do?
    Each recursive call halves n (integer division) until it reaches 1.
    So it returns the number of times you can divide by 2 before hitting 1.
    Rose(n) = log2(n)
     */
    /* What does Wow(12, 15) evaluate to?
    90
     */
    //1. Print digits in reverse order
    public static void printReverseDigits(int n) {
        if (n < 10) {
            System.out.print(n + " ");
            return;
        }
        System.out.print((n % 10) + " ");
        printReverseDigits(n / 10);
    }
    //2a. WriteLine(ch, n)
    public static void writeLine(char ch, int n) {
        if (n <= 0) return;
        System.out.print(ch);
        writeLine(ch, n - 1);
    }
    //2b. WriteBlock(ch, n, m) using writeLine
    public static void writeBlock(char ch, int n, int m) {
        if (m <= 0) return;
        writeLine(ch, n);
        System.out.println();
        writeBlock(ch, n, m - 1);
    }
    //3. Print array elements in reverse order
    int[] arr = {100, 200, 300, 400, 500, 600};
    public void printArrayReverse(int index) {
        if (index < 0) return;
        System.out.print(arr[index] + " ");
        printArrayReverse(index - 1);
    }
    //method call
    public void main(String[] args) {
        printArrayReverse(5);
        printReverseDigits(5);
        writeLine('d', 10);
        writeBlock('d', 5, 9);
    }
}
