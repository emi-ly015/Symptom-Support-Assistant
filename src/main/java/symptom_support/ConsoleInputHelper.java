package symptom_support;

import java.util.Scanner;

/**
 * Handles safe console input so main focuses on the application flow.
 */
public final class ConsoleInputHelper
{
    private ConsoleInputHelper() {}

    public static String readNonBlankString(Scanner scanner, String prompt)
    {
        String input = "";

        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("Input cannot be blank.");

        } while (input.isEmpty());
        return input;
    }

    public static int readPositiveInt(Scanner scanner, String prompt)
    {
        int value = 0;
        boolean valid;

        do {
            valid = true;
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty())
            {
                System.out.println("Please enter a number.");
                valid = false;
                continue;
            }

            try {
                value = Integer.parseInt(input);
                if (value <= 0)
                {
                    System.out.println("Number must be greater than 0.");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
                valid = false;
            }
        } while (!valid);
        return value;
    }

    public static int readSeverity(Scanner scanner)
    {
        int value = 0;
        boolean valid;

        do {
            valid = true;
            System.out.print("Severity (1-10): ");
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value < 1 || value > 10)
                {
                    System.out.println("Severity must be between 1 and 10.");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number between 1 and 10.");
                valid = false;
            }
        } while (!valid);
        return value;
    }

    public static int readNonNegativeInt(Scanner scanner, String prompt)
    {
        int value = 0;
        boolean valid;

        do {
            valid = true;
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty())
            {
                System.out.println("Please enter a number.");
                valid = false;
                continue;
            }
            try {
                value = Integer.parseInt(input);
                if (value < 0)
                {
                    System.out.println("Value cannot be negative.");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
                valid = false;
            }
        } while (!valid);
        return value;
    }
}