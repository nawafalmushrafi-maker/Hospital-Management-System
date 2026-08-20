package utils;

import java.util.Scanner;

public class InputHandler {

    private static Scanner scanner =
            new Scanner(System.in);


    // Read Text

    public static String readText(String prompt) {

        while (true) {

            IO.println(prompt);

            String value = scanner.nextLine();

            if (HelperUtils.isValidText(value)) {
                return value;
            }

            System.out.println(
                    "Invalid input. Please enter text."
            );
        }
    }


    // Read Integer

    public static int readInt(String prompt) {

        while (true) {

            IO.println(prompt);

            String value = scanner.nextLine();

            try {

                return Integer.parseInt(value);

            } catch (NumberFormatException e) {

                IO.println(
                        "Please enter a whole number."
                );
            }
        }
    }


    // Read Integer With Range

    public static int readInt(
            String prompt,
            int min,
            int max) {

        while (true) {

            int number = readInt(prompt);

            if (HelperUtils.isInRange(
                    number,
                    min,
                    max)) {

                return number;
            }

            IO.println(
                    "Number must be between "
                            + min
                            + " and "
                            + max
                            + "."
            );
        }
    }


    // Read Decimal

    public static double readDouble(String prompt) {

        while (true) {

            IO.println(prompt);

            String value = scanner.nextLine();

            try {

                return Double.parseDouble(value);

            } catch (NumberFormatException e) {

                IO.println(
                        "Please enter a valid decimal number."
                );
            }
        }
    }


    // Yes / No

    public static boolean readConfirmation(
            String prompt) {

        String[] allowed = {
                "yes",
                "no",
                "y",
                "n"
        };

        while (true) {

            String value = readText(prompt);

            if (HelperUtils.isOneOf(
                    value,
                    allowed)) {

                return value.equalsIgnoreCase("yes")
                        || value.equalsIgnoreCase("y");
            }

            IO.println(
                    "Please enter yes or no."
            );
        }
    }


    // Allowed Values

    public static String readOneOf(
            String prompt,
            String[] allowedValues) {

        while (true) {

            String value = readText(prompt);

            if (HelperUtils.isOneOf(
                    value,
                    allowedValues)) {

                return value;
            }

            IO.println(
                    "Please choose an allowed value."
            );
        }
    }
}

