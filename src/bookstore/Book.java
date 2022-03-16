package bookstore;

import java.text.NumberFormat;

public class Book
{
	private String title;
	private String authorFirstName;
	private String authorLastName;
	private String genre;
	private double price;
	
	public Book()
	{
		this("", "", "", "", 0.0);
	}

	public Book(String title, String authorFirstName, String authorLastName, String genre, double price) {
		// TODO Auto-generated constructor stub
		this.setTitle(title);
		this.setAuthorFirstName(authorFirstName);
		this.setAuthorLastName(authorLastName);
		this.setGenre(genre);
		this.setPrice(price);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}
