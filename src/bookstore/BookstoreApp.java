package bookstore;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BookstoreApp {

	private static double total = 0.0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("welcome to the Bookstore \n");
		displayMenu();
		
		// read list of books from file
		ArrayList<Book> books;
		try
		{
			books = BookDB.getAll();
			
			String option = "";
			while (!option.equalsIgnoreCase("exit"))
			{
				option = Console.getString("Please choose a command: ");
				System.out.println();
				
				switch (option)
				{
				case "list" -> displayAll(books);
				case "buy" -> buyBook(books);
				case "sell" -> sellBook(books);
				case "update" -> updateBook(books);
				case "total" -> displayTotal();
				case "help", "menu" -> displayMenu();
				case "exit" -> System.out.println("Thank you for using the Bookstore, bye. \n");
				default -> System.out.println("Error! Chosen option is not valid. \n");
				}
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("No list to display.");
			return;
		}
	}

	private static void updateBook(ArrayList<Book> books) {
		// TODO Auto-generated method stub
		System.out.println("UPDATE BOOK");
		String title = Console.getString("Please enter the title to update: ");
		
		
		for (Book book : books)
		{
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
				BookDB.saveAll(books);
				return;
			}
		}
		
		System.out.println("No book matched that title. \n");
	}

	private static void displayTotal() {
		// TODO Auto-generated method stub
		System.out.println("DISPLAY CURRENT TOTAL");
		System.out.println("The current total is: " + getTotal());
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

	private static String getTotalPriceFormatted(double total2) {
		// TODO Auto-generated method stub
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(total2);
	}

	// bookstore selling book to customer
	private static void sellBook(ArrayList<Book> books) {
		// TODO Auto-generated method stub
		String title = Console.getString("Please enter the title: ");
		
		for (Book book : books)
		{
			if (book.getTitle().equals(title))
			{
				int quantity = Console.getInt("Please enter the quantity being sold to the customer: ", 1, book.getQuantity());
				setTotal(getTotal() + (book.getPrice() * quantity));
				if ((book.getQuantity() - quantity) == 0)
				{
					books.remove(book);
				}
				else
				{
					book.setQuantity(book.getQuantity() - quantity);
				}
				BookDB.saveAll(books);
				System.out.println(quantity + " of " + book.getTitle() + " has been sold to the customer.");
				return;
			}
		}
		
		System.out.println("No book matches that title. \n");
	}

	// bookstore buying book from customer
	private static void buyBook(ArrayList<Book> books) {
		// TODO Auto-generated method stub
		String title = Console.getString("Please enter the title: ");
		String authorFirstName = "", authorLastName = "", genre = "";
		double price = 0.0;
		int quantity = 0;
		boolean bool = false;
		int x = 0, y = 0;
		for (Book book : books)
		{
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

		if (bool)
		{
			int tempQuantity = Console.getInt("Please enter the quantity of the book being sold to the bookstore: ", 1);
			quantity += tempQuantity;
			setTotal(getTotal() - (price * tempQuantity));
			Book book = new Book(title, authorFirstName, authorLastName, genre, price, quantity);
			books.set(y, book);
			System.out.println(tempQuantity + " of " + title + " has been sold to the bookstore.");
		}
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

	private static void displayAll(ArrayList<Book> books) {
		// TODO Auto-generated method stub
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

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("COMMANDS MENU");
		System.out.println("list   - List all books available to sell");
		System.out.println("buy    - Buy book from customer");
		System.out.println("sell   - Sell book to customer");
		System.out.println("update - Update book fields");
		System.out.println("total  - Customer's total balance");
		System.out.println("help   - Show this menu");
		System.out.println("exit   - Exit this application");
	}

	public static double getTotal() {
		return total;
	}

	public static void setTotal(double total) {
		BookstoreApp.total = total;
	}
}
