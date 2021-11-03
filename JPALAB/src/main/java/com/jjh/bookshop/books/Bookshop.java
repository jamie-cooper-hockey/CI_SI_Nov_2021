package com.jjh.bookshop.books;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookshop {
	private Book getBookByISBN;
	private List<Book> books = new ArrayList<>();

	BookDAO bookDAO = new BookDAO();

	public Bookshop() {
		bookDAO.setup();
	}

	public List<Book> getBooks() {
		System.out.println("BookDao.getBooks");
		bookDAO.setup();
		return bookDAO.getAllBooks();
	}

	public Book getBookByISBN(int isbn) {
		return bookDAO.getBookByISBN(isbn);
	}

	public void saveBook(Book book) {
		bookDAO.saveBook(book);
	}

	public void deleteBook(Book book) {
		bookDAO.deleteBook(book);
	}

	public List<Book> getAllBooks() {
		return books;
	}

}
