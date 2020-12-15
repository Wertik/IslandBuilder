import org.junit.Test;
import space.devport.wertik.builder.placeholders.operator.Operators;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public class SimpleMathTest {

    @Test
    public void operatorShouldActuallyWork() {
        Random random = new Random();
        Map<String, BiFunction<Double, Double, Double>> operators = new HashMap<String, BiFunction<Double, Double, Double>>() {{
            put(" + ", Double::sum);
            put(" - ", (num1, num2) -> num1 - num2);
        }};

        int testCount = 10;
        Map<String, String> samples = new HashMap<>();

        for (int n = 0; n < testCount; n++) {
            double num1 = random.nextDouble();
            double num2 = random.nextDouble();
            String operator = new ArrayList<>(operators.keySet()).get(random.nextInt(operators.size()));

            String expression = num1 + operator + num2;

            double res = operators.get(operator).apply(num1, num2);
            samples.put(expression, String.valueOf(((Number) res).floatValue()));
        }

        for (Map.Entry<String, String> entry : samples.entrySet()) {
            String res = Operators.parse(entry.getKey());
            assertEquals(Double.parseDouble(entry.getValue()), Double.parseDouble(res), .00001);
        }
    }
}
