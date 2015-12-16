package bookshelf.controller;

import bookshelf.model.Book;
import bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/getBook", method = RequestMethod.GET)
    public @ResponseBody Book getBook(@RequestParam(value = "isbn") String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @RequestMapping(value = "/removeBook", method = RequestMethod.GET)
    public @ResponseBody Iterable<Book> removeBook(@RequestParam(value = "isbn") String isbn) {
        bookService.removeBook(isbn);
        return bookService.findAll();
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public @ResponseBody Iterable<Book> removeBook(@RequestParam(value = "isbn") String isbn,
                                                   @RequestParam(value = "name") String name,
                                                   @RequestParam(value = "author") String author,
                                                   @RequestParam(value = "price") double price) {
        bookService.addBook(isbn, name, author, price);
        return bookService.findAll();
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.GET)
    public @ResponseBody Iterable<Book> updateBook(@RequestParam(value = "isbn") String isbn,
                                                   @RequestParam(value = "name") String name,
                                                   @RequestParam(value = "author") String author,
                                                   @RequestParam(value = "price") double price) {
        bookService.updateBook(isbn, name, author, price);
        return bookService.findAll();
    }

}
