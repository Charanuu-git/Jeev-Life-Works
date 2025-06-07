import java.util.Scanner;

public class week1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // enter two integers
        System.out.print("Enter the first integer: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter the second integer: ");
        int num2 = scanner.nextInt();

        // enter floating point number
        System.out.print("Enter a floating-point number: ");
        double floatNum = scanner.nextDouble();

        // enter a single character
        System.out.print("Enter a single character: ");
        char ch = scanner.next().charAt(0);

        // enter a boolean value
        System.out.print("Enter a boolean value (true/false): ");
        boolean boolVal = scanner.nextBoolean();

        // enter a name
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // printing the output
        System.out.println();
        System.out.println("Sum of " + num1 + " and " + num2 + " is: " + (num1 + num2));
        System.out.println("Difference between " + num1 + " and " + num2 + " is: " + (num1 - num2));
        System.out.println("Product of " + num1 + " and " + num2 + " is: " + (num1 * num2));
        System.out.println(floatNum + " multiplied by 2 is: " + (floatNum * 2));
        System.out.println("The next character after '" + ch + "' is: " + (char)(ch + 1));
        System.out.println("The opposite of " + boolVal + " is: " + (!boolVal));
        System.out.println("Hello, " + name + "!");
    }
}