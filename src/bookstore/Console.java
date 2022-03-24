package bookstore;

import java.util.Scanner;

/**
 * @author Lawrence Splaver
 * @version 2
 *
 */
public class Console
{
	// Create constant variable for the scanner object
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Method to get the String value of the user input following the prompt
	 * @param prompt String value of message to user for user input
	 * @return newString - String value of the user input
	 */
	public static String getString(String prompt)
	{
		System.out.print(prompt);
		String newString = sc.nextLine();
		return newString;
	}

	/**
	 * Method to get the Integer value of the user input following the prompt
	 * @param prompt String value of message to user for user input
	 * @return Integer.parseInt(sc.nextLine()) - Integer value of the user input
	 */
	public static int getInt(String prompt)
	{
		// While loop to keep re-asking for the input if the input is not an Integer
		while (true) 
		{
			System.out.print(prompt);
			// Try block to verify that the input is an Integer
			try
			{
				return Integer.parseInt(sc.nextLine());
			}
			// Catch block to catch NumberFormatException error
			catch (NumberFormatException e)
			{
				System.out.println("Error! Value must be an integer.");
			}
		}
	}

	/**
	 * Method to get the Integer value of the user input following the prompt that is greater than or equal to the minimum value 
	 * @param prompt String value of message to user for user input
	 * @param min Integer value that the entered value must be greater than or equal to
	 * @return value - Integer value of the user input
	 */
	public static int getInt(String prompt, int min)
	{
		// While loop to keep re-asking for the input if the input is less than the minimum value
		while(true)
		{
			int value = getInt(prompt);
			if (value >= min)
			{
				return value;
			}
			else
			{
				System.out.println("Error! Number must be greater than or equal to " + min);
			}
		}
	}
	
	/**
	 * Method to get the Integer value of the user input following the prompt that is greater than or equal to the minimum value and less than or equal to the maximum value
	 * @param prompt String value of the message to user for user input
	 * @param min Integer value that the entered value must be greater than or equal to
	 * @param max Integer value that the entered value must be less than or equal to
	 * @return value - Integer value of the user input
	 */
	public static int getInt(String prompt, int min, int max)
	{
		// While loop to keep re-asking for the input if the input is less than the minimum value or greater than the maximum value
		while (true)
		{
			int value = getInt(prompt);
			if ((value >= min) && (value <= max))
			{
				return value;
			}
			else
			{
				System.out.println("Error! Number must be greater than or equal to " + min + " and less than or equal to " + max + " .");
			}
		}
	}

	/**
	 * Method to get the Double value of the user input following the prompt
	 * @param prompt String value of the message to user for user input
	 * @return Double.parseDouble(sc.nextLine()) - Double value of the user input
	 */
	public static double getDouble(String prompt)
	{
		// While loop to keep re-asking for the input if the input is not a Double
		while (true)
		{
			System.out.print(prompt);
			// Try block to verify that the input is a Double
			try
			{
				return Double.parseDouble(sc.nextLine());
			}
			// Catch block to catch NumberFormatException error
			catch (NumberFormatException e)
			{
				System.out.println("Error! Value must be a doubvle value.");
			}
		}
	}
}
