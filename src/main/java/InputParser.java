import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    public ParsedInput parse(String input) {
        Pattern pattern = Pattern.compile("(\\w+)\\s(->)\\s(.*)");
        Matcher matcher = pattern.matcher(input);
        matcher.matches();

        return new ParsedInput(matcher.group(1), matcher.group(2), matcher.group(3));
    }
}
