package Utills;

public class HelperUtills {
    private static int idCounter = 1;

    // Generate ID without prefix **

    public static String generateId() {

        String id = String.valueOf(idCounter);

        idCounter++;

        return id;
    }

    // Generate ID without prefix **

    public static String generateId(String prefix) {

        if (prefix == null) {
            prefix = "";
        }

        String id = prefix + idCounter;

        idCounter++;

        return id;
    }
}

