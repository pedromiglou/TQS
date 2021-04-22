package tqsua.booksearchexample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
	private final List<Book> store = new ArrayList<>();

	public void addBook(final Book book) {
		store.add(book);
	}

	public List<Book> findBooks(final LocalDateTime from, final LocalDateTime to) {
		return store.stream().filter(book -> from.compareTo(book.getPublished())<=0 && to.compareTo(book.getPublished())>=0)
				.sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}

	public List<Book> findBooks(String author) {
		return store.stream().filter(book -> author.compareTo(book.getAuthor())==0).collect(Collectors.toList());
	}
}
