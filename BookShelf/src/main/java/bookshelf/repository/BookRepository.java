package bookshelf.repository;

import bookshelf.model.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {

    public static final Map<String, Book> BOOKS_MAP =
            new HashMap<String, Book>() {
                {
                    put("9780201485677", new Book("9780201485677", "Refactoring", "Martin Fowler", 64.99));
                    put("9780132350884", new Book("9780132350884", "Clean Code", "Robert C. Martin", 35.44));
                    put("9780131429017", new Book("9780131429017", "The Art of UNIX Programming", "Eric S. Raymond", 39.99));
                }
            };

    public Iterable<Book> findAll() {
        return BOOKS_MAP.values();
    }

    public Book findOne(String isbn) {
        return BOOKS_MAP.get(isbn);
    }

    public Book updateOne(Book book) {
        return BOOKS_MAP.put(book.getIsbn(), book);
    }

    public void addBook(Book book) {
        BOOKS_MAP.put(book.getIsbn(), book);
    }

    public Book removeBook(String isbn) {
        return BOOKS_MAP.remove(isbn);
    }

    protected static void reset() {
        BOOKS_MAP.clear();
        BOOKS_MAP.put("9780201485677", new Book("9780201485677", "Refactoring", "Martin Fowler", 64.99));
        BOOKS_MAP.put("9780132350884", new Book("9780132350884", "Clean Code", "Robert C. Martin", 35.44));
        BOOKS_MAP.put("9780131429017", new Book("9780131429017", "The Art of UNIX Programming", "Eric S. Raymond", 39.99));
    }

}

