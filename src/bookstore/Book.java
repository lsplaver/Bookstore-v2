package bookstore;

import java.text.NumberFormat;

public class Book
{
	/**
	 * Declare instance variables for book object
	 */
	private String title;
	private String authorFirstName;
	private String authorLastName;
	private String genre;
	private double price;
	private int quantity;
	
	/**
	 * Default constructor for Book initializes blank book
	 */
	public Book()
	{
		this("", "", "", "", 0.0, 0);
	}

	/**
	 * Constructor for creating a book object with specified parameters 
	 * @param title - String value of the title
	 * @param authorFirstName - String value of the author's first name
	 * @param authorLastName - String value of the authror's last name
	 * @param genre - String value of the book's genre
	 * @param price - double value of the book's price
	 * @param quantity - integer value of the book's on hand quantity
	 */
	public Book(String title, String authorFirstName, String authorLastName, String genre, double price, int quantity) {
		// TODO Auto-generated constructor stub
		this.setTitle(title);
		this.setAuthorFirstName(authorFirstName);
		this.setAuthorLastName(authorLastName);
		this.setGenre(genre);
		this.setPrice(price);
		this.setQuantity(quantity);
	}

	/**
	 * Method to get the title of a book object
	 * @return - String value of the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method to set the title of a book object
	 * @param title - String value of the book's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method to get the author's first name
	 * @return - String value of the author's first name
	 */
	public String getAuthorFirstName() {
		return authorFirstName;
	}

	/**
	 * Method to set the author's first name
	 * @param authorFirstName - String value of the author's first name
	 */
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	/**
	 * Method to get the author's last name
	 * @return - String value of the author's last name
	 */
	public String getAuthorLastName() {
		return authorLastName;
	}

	/**
	 * Method to set the author's last name
	 * @param authorLastName - String value of the author's last name
	 */
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	/**
	 * Method to get the genre of a book object
	 * @return - String value of the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Method to set the genre of a book object
	 * @param genre - String value of the book's genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Method to get the price of a book object
	 * @return - Double value of the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method to set the price of a book object
	 * @param price - Double value of the book's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Method to get the price value as a formatted string
	 * @return - String formatted value of the book's price
	 */
	public String getPriceFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

	/**
	 * Method to get the quantity of a book object
	 * @return - Integer value of the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Method to set the quantity of a book object
	 * @param quantity - Integer value of the book's quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
