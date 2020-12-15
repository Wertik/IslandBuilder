package space.devport.wertik.builder.placeholders.operator;

import com.google.common.base.Strings;
import lombok.Data;

@Data
public class OperatorWrapper {

    private final String sign;
    private final SignOperator signOperator;

    public String apply(String str) {
        String[] arr = str.split("\\Q" + sign + "\\E");

        if (arr.length < 2)
            return str;

        if (Strings.isNullOrEmpty(arr[0]) || Strings.isNullOrEmpty(arr[1]))
            return str;

        return signOperator.apply(ParseUtil.parse(arr[0]), ParseUtil.parse(arr[1]));
    }
}
