package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class ScannerUtils {
	public static final String MATCH_ANY_REGEX = ".+";


	public static Scanner scanner = new Scanner(System.in);

	public static String inputString(String inputPrompt, String errorPrompt, String regex) {
		System.out.print(inputPrompt);
		String str = scanner.nextLine().trim();

		if (!str.matches(regex)) {
			System.out.println(errorPrompt);
			return inputString(inputPrompt, errorPrompt, regex);
		}

		return str;
	}


	public static int inputNumber() {
		while (true) {
			try {
				String s = scanner.nextLine();
				int a = Integer.parseInt(s);
				return a;
			} catch (Exception e) {
				System.out.print("Wrong inputing! Please input a number as int! Input again:");
			}
		}
	}

	public static double inputDouble(String inputPrompt, double min, double max) {
		System.out.print(inputPrompt);
		try {
			double d = Double.parseDouble(scanner.nextLine());
			if (d < min || d > max) {
				System.out.println("Please enter a number between " + min + " and " + max);
				return inputDouble(inputPrompt, min, max);
			}

			return d;
		} catch (Exception e) {
			System.out.println("Please enter a double number");
			return inputDouble(inputPrompt, min, max);
		}
	}

	public static int inputInteger(String inputPrompt, int min, int max) {
		System.out.print(inputPrompt);
		try {
			String rawNumber = scanner.nextLine().trim();

			int number = Integer.parseInt(rawNumber);
			if (number < min || number > max) {
				System.out.println("Please enter a number between " + min + " and " + max);
				return inputInteger(inputPrompt, min, max);
			}

			return number;
		} catch (Exception e) {
			System.out.println("Please enter an integer number!");
			return inputInteger(inputPrompt, min, max);
		}
	}

	public static int inputNumber(int min, int max) {
		while (true) {
			int a = inputNumber();
			if (a < min || a > max) {
				System.out.printf("Please input a number from %d to %d! Input again: ", min, max);
				continue;
			}
			return a;
		}
	}

	public static int inputPositiveNumber() {
		while (true) {
			int a = inputNumber();
			if (a < 0) {
				System.out.print("Please input positive number! Input again: ");
				continue;
			}
			return a;
		}
	}

	public static int inputId() {
		while (true) {
			int a = inputNumber();
			if (a < 1) {
				System.out.print("Please input a id which is greater than or equal 1! Input again: ");
				continue;
			}
			return a;
		}
	}

	public static Date inputDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		System.out.print("Enter the date (format: dd/MM/yyyy: ");
		while (true) {
			String input = scanner.nextLine();
			try {

				Date date = dateFormat.parse(input);
				Date curDate = Calendar.getInstance().getTime();
				if (curDate.compareTo(date) < 0) {
					System.out.print("Please input date that before current date: ");
					continue;
				}
				return date;
			} catch (Exception e) {
				System.out.print("Please input valid date (dd/MM/yyyy): ");
				return null;
			}
		}
	}

	public static Date inputDate1() {
		System.out.print("Enter the date (format: yyyy-MM-dd): ");
		String userInput = scanner.nextLine();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(userInput);
		} catch (ParseException e) {
			System.out.println("Error parsing date. Please enter the date in the correct format.");
			return null;
		}
	}

	public static String inputString() {
		return scanner.nextLine();
	}

	public static String inputCustom(String custom){
		System.out.println("Input " + custom + ": ");
		return inputString();
	}

	public static int inputIntCustom(String custom){
		System.out.println("Input " + custom + ": ");
		return inputNumber();
	}


	public static String inputUsername() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 6) {
				System.out.print("Your username is too short! Input again: ");
				continue;
			}

			if (s.length() > 50) {
				System.out.print("Your username is too long! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static String inputPassword() {
		return inputString();
	}

	public static String inputKeyword() {
		String s = scanner.nextLine();
		s = removeSpaces(s);
		return s;
	}

	private static String removeSpaces(String s) {
		// remove before & after spaces
		s = s.trim();
		// remove middle spaces
		while (s.contains("  ")) {
			s = s.replace("  ", " ");
		}
		return s;
	}

	public static String inputRole() {
		while (true) {
			String s = scanner.nextLine();

			if (!s.equalsIgnoreCase("Admin") && !s.equalsIgnoreCase("Employee")) {
				System.out.print("Wrong format! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static String inputEmail() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 5) {
				System.out.print("Your email is too short! Input again: ");
				continue;
			}

			if (s.length() > 20) {
				System.out.print("Your email is too long! Input again: ");
				continue;
			}

			if (!s.contains("@") || !s.contains(".com")) {
				System.out.println("Wrong format! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static boolean inputYesOrNo() {
		while (true) {
			String s = scanner.nextLine();

			if (s.equalsIgnoreCase("Y")) {
				return true;
			}

			if (s.equalsIgnoreCase("N")) {
				return false;
			}

			System.out.println("Please input Y or N !Input again: ");
		}
	}
}
