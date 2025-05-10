package org.example;
/**
 * Provides utility methods for verifying asset data fields.
 */
public class Verification {
    /**
     * Checks whether all fields in the array are non-null and non-empty.
     *
     * @param fields An array of strings representing the fields to check.
     * @return true if all fields are non-null and not empty; false otherwise.
     */
    public static boolean checkFields(String[] fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
