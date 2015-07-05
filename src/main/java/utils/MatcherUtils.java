package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {
    public static boolean match(String requestBody, String urlPattern) {
	Pattern regex = Pattern.compile(urlPattern);
	Matcher m = regex.matcher(requestBody);
	return m.matches();
    }
}
