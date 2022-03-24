package bookstore;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Lawrence Splaver
 * @version 2
 */
public class BookDB
{
	// Constant variables for file name and text delimiter
	private static final String FILENAME = "books.txt";
	private static final String DELIMITER = "\t";
	
	/**
	 * Method to get all book objects stored in the file and output them into an ArrayList of type Book
	 * @return books - ArrayList books of type Book or null if exception was caught
	 */
	public static ArrayList<Book> getAll()
	{
		 // Create new books object of type ArrayList<Book>
		var books = new ArrayList<Book>();
		 
		// Try block to attempt to read from the file
		// returns populated books object
		try (BufferedReader in = new BufferedReader(new FileReader(FILENAME)))
		{
			String line = in.readLine();

			// While loop to populate the books ArrayList<Book> with each element being a line of the file
			while (line != null)
			{
				// Splits each line of the file by the delimiter and stores each section in an element in the String array
				String[] fields = line.split(DELIMITER);
				String title = fields[0];
				String authorFirstName = fields[1];
				String authorLastName = fields[2];
				String genre = fields[3];
				String price = fields[4];
				String quantity = fields[5];
				
				// Creates a new book object using the values stored in the String array
				Book book = new Book(title, authorFirstName, authorLastName, genre, Double.parseDouble(price), Integer.parseInt(quantity));
				// Book object to be added to ArrayList<Book> books
				books.add(book);
				
				line = in.readLine();
			}
			return books;
		}
		// Catch block in case the file isn't found
		catch (FileNotFoundException e)
		{
			System.out.println(FILENAME + " not found.");
			return null;
		}
		// Catch block for any other exceptions and prints the exception
		catch (IOException e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Method to save the current version of the ArrayList of type Book books to the file, creating or overwriting as needed
	 * @param books ArrayList of type Book that is the current list of books
	 */
	public static void saveAll(ArrayList<Book> books)
	{
		// Try block to attempt to write to the file
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME))))
		{
			// Enhanced for loop to write each element of the books ArrayList to the file
			for (Book book : books)
			{
				out.print(book.getTitle() + DELIMITER);
				out.print(book.getAuthorFirstName() + DELIMITER);
				out.print(book.getAuthorLastName() + DELIMITER);
				out.print(book.getGenre() + DELIMITER);
				out.print(book.getPrice() + DELIMITER);
				out.println(book.getQuantity());
			}
		}
		// Catch block for any exceptions and prints the exception
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
