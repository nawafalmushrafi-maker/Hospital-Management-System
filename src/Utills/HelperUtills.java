package Utills;
public class HelperUtills {
    private static int idCounter = 1;
    // Empty Checks
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
    public static boolean isEmpty(Object[] collection) {
        if (collection == null || collection.length == 0) {
            return true;
        }
        for (Object item : collection) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }
    // Valid Text
    public static boolean isValidText(String text) {
        return !isEmpty(text);
    }
    public static boolean isValidText(
            String text,
            int minLength) {
        return !isEmpty(text)
                && text.trim().length() >= minLength;
    }
    public static boolean isValidText(
            String text,
            int minLength,
            int maxLength) {
        if (isEmpty(text)) {
            return false;
        }
        int length = text.trim().length();
        return length >= minLength
                && length <= maxLength;
    }
    // ID Generation

    public static String generateId() {
        String id = String.valueOf(idCounter);
        idCounter++;
        return id;
    }
    public static String generateId(String prefix) {
        String id = prefix + idCounter;
        idCounter++;
        return id;
    }
    // Positive Check
    public static boolean isPositive(int number) {
        return number > 0;
    }
    public static boolean isPositive(double number) {
        return number > 0;
    }
    // Range Checks
    public static boolean isInRange(
            int number,
            int min,
            int max) {
        return number >= min && number <= max;
    }
    public static boolean isInRange(
            double number,
            double min,
            double max) {
        return number >= min && number <= max;
    }
    // Age Validation
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }
    // Phone Validation
    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        return phone.length() == 8;
    }
    // Allowed Values
    public static boolean isOneOf(
            String value,
            String[] allowedValues) {
        if (value == null || allowedValues == null) {
            return false;
        }
        for (String allowed : allowedValues) {
            if (allowed != null
                    && value.equalsIgnoreCase(allowed)) {
                return true;
            }
        }
        return false;
    }
}