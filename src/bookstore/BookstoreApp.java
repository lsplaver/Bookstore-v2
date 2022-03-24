package bookstore;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * @author Lawrence Splaver
 * @version 2
 */
public class BookstoreApp {

	// Create total variable of type Double 
	private static double total = 0.0;
	
	/**
	 * Main method
	 * @param args Main method arguments
	 */
	public static void main(String[] args) {
		System.out.println("welcome to the Bookstore \n");
		// Calls displayMenu method
		displayMenu();
		
		// Create books ArrayList type Book object
		ArrayList<Book> books;
		// Try block to attempt to list all book objects
		try
		{
			// Set books to the returned list of book objects
			books = BookDB.getAll();
			
			String option = "";
			// While loop to keep the app running until the user enters "exit"
			while (!option.equalsIgnoreCase("exit"))
			{
				option = Console.getString("Please choose a command: ");
				System.out.println();
				
				switch (option)
				{
				case "list" -> displayAll(books); // Calls displayAll with books as parameter to list all books
				case "buy" -> buyBook(books); // Calls buyBook with books parameter to buy book from customer
				case "sell" -> sellBook(books); // Calls sellBook with books parameter to sell book to customer
				case "update" -> updateBook(books); // Calls updateBook with books parameter to update book's record
				case "total" -> displayTotal(); // Calls displayTotoal to show the total customer balance
				case "help", "menu" -> displayMenu(); // Calls displayMenu to display the menu
				case "exit" -> System.out.println("Thank you for using the Bookstore, bye. \n");
				default -> System.out.println("Error! Chosen option is not valid. \n");
				}
			}
		}
		// Catch block to catch NullPointerExceptions
		catch (NullPointerException e)
		{
			System.out.println("No list to display.");
			return;
		}
	}

	/**
	 * Method to update a book's record
	 * @param books ArrayList of type Book
	 */
	private static void updateBook(ArrayList<Book> books) {
		System.out.println("UPDATE BOOK");
		String title = Console.getString("Please enter the title to update: ");
		
		// Enhance for loop to iterate through all elements of books
		for (Book book : books)
		{
			// Checks if entered title is equal to an existing title
			if (book.getTitle().equals(title))
			{
				System.out.println("Updatable fields are: ");
				System.out.println("title    - Book's title.");
				System.out.println("first    - Author's first name.");
				System.out.println("last     - Author's last name.");
				System.out.println("genre    - Book's genre.");
				System.out.println("price    - Books price.");
				System.out.println("quantity - Quantity of book in stock.");
				System.out.println("done     - Done updating the specified book. Will return to main menu");
				
				String option = "";
				// While loop to keep updateBook running until user enters "done"
				while (!option.equalsIgnoreCase("done"))
				{
					option = Console.getString("Please choose a field to update: ");
					System.out.println();
					
					switch (option.toLowerCase())
					{
						case "title" -> book.setTitle(Console.getString("Please enter a new title: "));
						case "first" -> book.setAuthorFirstName(Console.getString("Please enter a new first name: "));
						case "last" -> book.setAuthorLastName(Console.getString("Please enter a new last name: "));
						case "genre" -> book.setGenre(Console.getString("Pleas enter a new genre: "));
						case "price" -> book.setPrice(Console.getDouble("Please enter a new price: "));
						case "quantity" -> book.setQuantity(Console.getInt("Please enter a new quantity: "));
						case "done" -> System.out.println("Done updating, returning to main menu.");
						default -> System.out.println("Error! Chosen option is not valid. \n");
					}
				}
				// Saves the updated books to the file
				BookDB.saveAll(books);
				return;
			}
		}
		
		System.out.println("No book matched that title. \n");
	}

	// Method to display the current total
	private static void displayTotal() {
		System.out.println("DISPLAY CURRENT TOTAL");
		System.out.println("The current total is: " + getTotal());
		// If-Else to determine how much the customer owes or the bookstore owes 
		if (getTotal() > 0)
		{
			System.out.println("The customer owes the bookstore " + getTotalPriceFormatted(getTotal()));
		}
		else if (getTotal() == 0)
		{
			System.out.println("The customer owes the bookstore " + getTotalPriceFormatted(getTotal()));
		}
		else 
		{
			System.out.println("The bookstore owes the customer " + getTotalPriceFormatted(getTotal() * -1));
		}
	}

	/**
	 * Method to return the String formatted version of the current total
	 * @param total2 Current total value
	 * @return currency.format(total2) - String currency format for the current total
	 */
	private static String getTotalPriceFormatted(double total2) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(total2);
	}

	/**
	 * Method for the bookstore selling a book to the customer
	 * @param books ArrayList books of type Book
	 */
	private static void sellBook(ArrayList<Book> books) {
		String title = Console.getString("Please enter the title: ");

		// Enhanced for loop to iterate through all elements of books
		for (Book book : books)
		{
			// Checks if entered title is equal to an existing title
			if (book.getTitle().equals(title))
			{
				int quantity = Console.getInt("Please enter the quantity being sold to the customer: ", 1, book.getQuantity());
				// Sets the current total equal to previous total plus book's price * quantity
				setTotal(getTotal() + (book.getPrice() * quantity));
				// Checks how many copies of the book remain and if none than removes it from the list otherwise it updates the quantity field
				if ((book.getQuantity() - quantity) == 0)
				{
					books.remove(book);
				}
				else
				{
					book.setQuantity(book.getQuantity() - quantity);
				}
				// Saves the updated books to the file
				BookDB.saveAll(books);
				System.out.println(quantity + " of " + book.getTitle() + " has been sold to the customer.");
				return;
			}
		}
		
		System.out.println("No book matches that title. \n");
	}

	/**
	 * Method for the customer to sell a book to the bookstore
	 * @param books ArrayList books of type Book
	 */
	private static void buyBook(ArrayList<Book> books) {
		String title = Console.getString("Please enter the title: ");
		// Create and initialize the instance variables for each book field
		String authorFirstName = "", authorLastName = "", genre = "";
		double price = 0.0;
		int quantity = 0;

		// Create and initialize the instance variables for locating existing book 
		boolean bool = false;
		int x = 0, y = 0;
		// Enhanced for loop to iterate through all elements of books
		for (Book book : books)
		{
			// Checks if entered title is equal to an existing title if true sets all variables to located book
			if (book.getTitle().equals(title))
			{
				bool = true;
				authorFirstName = book.getAuthorFirstName();
				authorLastName = book.getAuthorLastName();
				genre = book.getGenre();
				price = book.getPrice();
				quantity = book.getQuantity();
				y = x;
			}
			x++;
		}

		// Checks if book found and if so how many more copies to sell to bookstore
		if (bool)
		{
			// tempQuantity equals entered quantity value which must be at least 1
			int tempQuantity = Console.getInt("Please enter the quantity of the book being sold to the bookstore: ", 1);
			// Adds tempQuantity to quantity
			quantity += tempQuantity;
			// Sets total equal to current total - (price * tempQuantity)
			setTotal(getTotal() - (price * tempQuantity));
			// Creates a new book object
			Book book = new Book(title, authorFirstName, authorLastName, genre, price, quantity);
			// Overwrites located book in ArrayList with the newly created book
			books.set(y, book);
			System.out.println(tempQuantity + " of " + title + " has been sold to the bookstore.");
		}
		// Prompts user to enter values for each book field and then adds it as a new book to ArrayList
		else
		{
			authorFirstName = Console.getString("Please enter the authror's first name: ");
			authorLastName = Console.getString("Please enter the author's last name: ");
			genre = Console.getString("Please enter the book's genre: ");
			price = Console.getDouble("Please enter the book's price: ");
			quantity = Console.getInt("Please enter the quantity of the book being sold to the bookstore: ", 1);
			Book book = new Book(title, authorFirstName, authorLastName, genre, price, quantity);
			books.add(book);
			setTotal(getTotal() - (price * quantity));
			System.out.println(quantity + " of " + title + " has been sold to the bookstore.");
		}
		BookDB.saveAll(books);
	}

	// Method to list all of the books
	private static void displayAll(ArrayList<Book> books) {
		System.out.println("BOOK LIST");
		
		String format = "%-60s%-20s%-20s%-20s%10s%10s%n";
		for (Book book : books)
		{
			System.out.printf(format, 
							  book.getTitle(),
							  book.getAuthorFirstName(),
							  book.getAuthorLastName(),
							  book.getGenre(),
							  book.getPriceFormatted(),
							  book.getQuantity());
		}
		System.out.println();
	}

	// Method to display the main menu
	private static void displayMenu() {
		System.out.println("COMMANDS MENU");
		System.out.println("list   - List all books available to sell");
		System.out.println("buy    - Buy book from customer");
		System.out.println("sell   - Sell book to customer");
		System.out.println("update - Update book fields");
		System.out.println("total  - Customer's total balance");
		System.out.println("help   - Show this menu");
		System.out.println("exit   - Exit this application");
	}

	/**
	 * Method to return the current total
	 * @return total - Double value of the current total
	 */
	public static double getTotal() {
		return total;
	}

	/**
	 * Method to set the current total to a Double value
	 * @param total Double value to set the total value to
	 */
	public static void setTotal(double total) {
		BookstoreApp.total = total;
	}
}
