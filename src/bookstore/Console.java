package bookstore;

import java.util.Scanner;

public class Console
{
	private static final Scanner sc = new Scanner(System.in);
	
	public static String getString(String prompt)
	{
		System.out.print(prompt);
		String newString = sc.nextLine();
		System.out.println("DEBUG: This is what was entered: " + newString);
		return newString;
	}
	
	public static int getInt(String prompt)
	{
		while (true)
		{
			System.out.print(prompt);
			try
			{
				return Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error! Value must be an integer.");
			}
		}
	}
	
	public static int getInt(String prompt, int min)
	{
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
	
	public static int getInt(String prompt, int min, int max)
	{
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
	
	public static double getDouble(String prompt)
	{
		while (true)
		{
			System.out.print(prompt);
			try
			{
				return Double.parseDouble(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error! Value must be a doubvle value.");
			}
		}
	}
}
