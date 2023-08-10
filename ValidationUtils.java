
public class ValidationUtils {

	public static boolean isValidNumber(String input) {
		return input.matches("-?\\d+(\\.\\d+)?");
	}

	public static double parseDouble(String input) {
		return Double.parseDouble(input);
	}

}
