package testproject.demo.service.impl;

import org.springframework.stereotype.Service;
import testproject.demo.entity.Book;
import testproject.demo.repository.BookRepository;
import testproject.demo.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {  //methods inherited from interface

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
