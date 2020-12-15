package space.devport.wertik.builder.placeholders.operator;

import com.google.common.base.Strings;
import space.devport.utils.ConsoleOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Operators {

    private static final Map<String, SignOperator> operators = new HashMap<String, SignOperator>() {{
        put(" + ", (o1, o2) -> {
            if (o1 instanceof Number && o2 instanceof Number) {
                return String.valueOf(((Number) o1).floatValue() + ((Number) o2).floatValue());
            } else if (o1 instanceof String && o2 instanceof String) {
                return ((String) o1).concat(((String) o2));
            }
            return o1.toString();
        });

        put(" - ", (o1, o2) -> {
            if (o1 instanceof Number && o2 instanceof Number) {
                return String.valueOf(((Number) o1).floatValue() - ((Number) o2).floatValue());
            }
            return o1.toString();
        });
    }};

    public static String parse(String str) {
        return fromString(str).map(op -> {
            ConsoleOutput.getInstance().debug("OP: " + op.getSign());
            return op.apply(str);
        }).orElse(str);
    }

    public static Optional<OperatorWrapper> fromString(String str) {
        return Strings.isNullOrEmpty(str) ? Optional.empty() : operators.entrySet().stream()
                .filter(entry -> str.toLowerCase().contains(entry.getKey().toLowerCase()))
                .findAny()
                .map(entry -> new OperatorWrapper(entry.getKey(), entry.getValue()));
    }
}
