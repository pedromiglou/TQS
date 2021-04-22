package feature.book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tqsua.booksearchexample.Book;
import tqsua.booksearchexample.Library;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime iso8601Date(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
	}

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

	@Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addAnotherNewBook(final String title, final String author, LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

	@When("the customer searches for books published between {int} and {int}")
	public void setSearchParameters(final int from, final int to) {
		LocalDateTime t1 = LocalDateTime.of(from, 1, 1,0, 0);
		LocalDateTime t2 = LocalDateTime.of(to, 12, 31,23, 59);
		result = library.findBooks(t1, t2);
	}

	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertEquals(result.size(), booksFound);
	}

	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertTrue(result.get(position - 1).getTitle().equals(title));
	}

	@When("the customer searches for books written by {string}")
	public void setSearchAuthor(final String author) {
		result = library.findBooks(author);
	}
}
