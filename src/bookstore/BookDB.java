package bookstore;

import java.io.*;
import java.util.ArrayList;

public class BookDB
{
	private static final String FILENAME = "books.txt";
	private static final String DELIMETER = "\t";
	
	public static ArrayList<Book> getAll()
	{
		var books = new ArrayList<Book>();
		try (BufferedReader in = new BufferedReader(new FileReader(FILENAME)))
		{
			String line = in.readLine();
			while (line != null)
			{
				String[] fields = line.split(DELIMETER);
				String title = fields[0];
				String authorFirstName = fields[1];
				String authorLastName = fields[2];
				String genre = fields[3];
				String price = fields[4];
				
				Book book = new Book(title, authorFirstName, authorLastName, genre, Double.parseDouble(price));
				books.add(book);
				
				line = in.readLine();
			}
			return books;
		}
		catch (FileNotFoundException e)
		{
			System.out.println(FILENAME + " not found.");
			return null;
		}
		catch (IOException e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	public static void saveAll(ArrayList<Book> books)
	{
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME))))
		{
			for (Book book : books)
			{
				out.print(book.getTitle() + DELIMETER);
				out.print(book.getAuthorFirstName() + DELIMETER);
				out.print(book.getAuthorLastName() + DELIMETER);
				out.print(book.getGenre() + DELIMETER);
				out.println(book.getPrice());
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
