package bookstore;

import java.util.Scanner;

public class Console
{
	private static final Scanner sc = new Scanner(System.in);
	
	public static String getString(String prompt)
	{
		System.out.print(prompt);
		return sc.nextLine();
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
