package testproject.demo.service;

import testproject.demo.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book getBookById(int id);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    List<Book> searchBooks(String searchText);
}
