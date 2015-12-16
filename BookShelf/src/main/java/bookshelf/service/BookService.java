package bookshelf.service;

import bookshelf.model.Book;
import bookshelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findOne(isbn);
    }

    public void addBook(String isbn, String name, String author, Double price) {
        bookRepository.addBook(new Book(isbn, name, author, price));
    }

    public Book updateBook(String isbn, String name, String author, Double price) {
        return bookRepository.updateOne(new Book(isbn, name, author, price));
    }

    public Book removeBook(String isbn) {
        return bookRepository.removeBook(isbn);
    }

}
