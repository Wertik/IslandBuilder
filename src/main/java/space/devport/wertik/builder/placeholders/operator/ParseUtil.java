package space.devport.wertik.builder.placeholders.operator;

public class ParseUtil {

    /**
     * Attempt to parse object into a higher class.
     */
    public static Object parse(String input) {

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
        }

        return input;
    }
}
