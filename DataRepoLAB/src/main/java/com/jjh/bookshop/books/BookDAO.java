package com.jjh.bookshop.books;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private EntityManager em;

    public void setup() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("BookshopJPA");
        em = emf.createEntityManager();
    }



    public Book getBookByISBN(int isbn) {
        System.out.printf("BookDAO.getBookByISBN(%s)\n", isbn);

        Book book = em.find(Book.class, isbn);
        return book;
    }

    public List<Book> getAllBooks() {

        System.out.println("BookDAO.getAllBooks()");
        List<Book> books = new ArrayList<>();

        String sql = "SELECT b FROM Book b";
        System.out.println(sql);

        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        List<Book> results = query.getResultList();

        return results;
    }

    public void saveBook(Book book) {
        System.out.printf("BookDAO.saveBook(%s)\n", book);

        String sql = String.format(
                "INSERT INTO books (isbn, title, category, author) VALUES(%d, '%s', '%s', '%s')",
                book.getIsbn(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor());

        System.out.println(sql);
        em.getTransaction().begin(); // Note use of a transaction
        Book new_book = new Book(book.getIsbn(), book.getTitle(), book.getCategory(), book.getAuthor());
        em.persist(new_book);
        em.getTransaction().commit();
    }

    public void deleteBook(Book book) {
        System.out.printf("BookDAO.deleteBook(%s)\n", book);
        String sql = "DELETE FROM books WHERE isbn = '" + book.getIsbn() + "'";
        System.out.println(sql);
        Book b3 = em.find(Book.class, book.getIsbn());
        em.getTransaction().begin();
        em.remove(b3);
        em.getTransaction().commit();
    }


}
