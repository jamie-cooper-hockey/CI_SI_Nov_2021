package com.jjh.bookshop.books;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Bookshop {
	private Book getBookByISBN;
	private List<Book> books = new ArrayList<>();

	BookDAO bookDAO = new BookDAO();

	public Bookshop() {
		books.add(new Book(121, "Java", "Technical", "John Smith"));
		books.add(new Book(345, "Death in the Spring", "Detective", "Denise Jones"));
		books.add(new Book(987, "Henry VI", "Historical", "Phoebe Davies"));
	}

	public List<Book> getBooks() throws SQLException {
		System.out.println("BookDao.getBooks");
		bookDAO.setup();
		return bookDAO.getAllBooks();
	}

	public Book getBookByISBN(int isbn) throws SQLException {
		return bookDAO.getBookByISBN(isbn);
	}

	public int saveBook(Book book) throws SQLException {
		return bookDAO.saveBook(book);
	}

	public int deleteBook(Book book) throws SQLException {
		return bookDAO.deleteBook(book);
	}

	public void setup() {
		bookDAO.setup();
	}

	public void shutdown() {
		bookDAO.shutdown();
	}

	public List<Book> getAllBooks() {
		return books;
	}

}
