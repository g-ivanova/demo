package testproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testproject.demo.entity.Book;
import testproject.demo.service.BookService;

import javax.swing.*;


@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @GetMapping(value="/books")
    public String listBooks(Model model){ //used to transfer data between the view and controller
        model.addAttribute("books",bookService.getAllBooks());
        return "books";
    }

    @GetMapping(value="/books/add")
    public String createBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "add_books"; //html file
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book,@RequestParam String genre) throws Exception {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        if(book.getName()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "add_books";
        }
        if(book.getAuthor()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the author can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "add_books";
        }
        if(genre.equals("Genre:")) {
            JOptionPane.showMessageDialog(jf, "Genre of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "add_books";
        }

        if (!book.getYear().matches("\\d+")) {
            JOptionPane.showMessageDialog(jf, "Year of the book must contain only numbers!",
                    "Incorect field!", JOptionPane.ERROR_MESSAGE);
            return "add_books";
        }

        if(Integer.parseInt(book.getYear())>2024 || Integer.parseInt(book.getYear())<100){
            JOptionPane.showMessageDialog(jf, "Year of the book must be in the range of 100 and 2024!",
                    "Inccorect field!", JOptionPane.ERROR_MESSAGE);
            return "add_books";
        }

        if (!book.getTotal_quantity().matches("\\d+")) {
            JOptionPane.showMessageDialog(jf, "Quantity of the book must contain only numbers!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        if(Integer.parseInt(book.getTotal_quantity())<0){
            JOptionPane.showMessageDialog(jf, "Quantity of the book must be bigger than 0!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Added new book!", JOptionPane.INFORMATION_MESSAGE);
            bookService.saveBook(book);
            return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String updateBookForm(@PathVariable Long id, Model model){

        model.addAttribute("book",bookService.getBookById(id));
        return "edit_books"; //html file
    }

    @GetMapping("/books/borrow/{id}")
    public String borrowBook(@PathVariable Long id, @ModelAttribute("book") Book book, Model model){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        Book existingBook = bookService.getBookById(id);
        if(existingBook.getBorrowed_quantity()!=null && Integer.parseInt(existingBook.getBorrowed_quantity())==Integer.parseInt(existingBook.getTotal_quantity())){
            JOptionPane.showMessageDialog(jf, "Total quantity is equal to the borrowed books!",
                    "Not enough quantity!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (existingBook.getBorrowed_quantity() == null) {
                existingBook.setBorrowed_quantity("1");
                JOptionPane.showMessageDialog(jf, "Successfull!",
                        "Borrowed book!", JOptionPane.INFORMATION_MESSAGE);
                bookService.updateBook(existingBook);

            }
            else {
                existingBook.setBorrowed_quantity(String.valueOf(Integer.parseInt(existingBook.getBorrowed_quantity()) + 1));
                JOptionPane.showMessageDialog(jf, "Successfull!",
                        "Borrowed book!", JOptionPane.INFORMATION_MESSAGE);
                bookService.updateBook(existingBook);

            }
        }


        return "redirect:/books";
    }

    @GetMapping("/books/return/{id}")
    public String returnBook(@PathVariable Long id, @ModelAttribute("book") Book book, Model model){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        Book existingBook = bookService.getBookById(id);
        if(Integer.parseInt(existingBook.getBorrowed_quantity())<=0 || existingBook.getBorrowed_quantity()==null){
            JOptionPane.showMessageDialog(jf, "The borrowed books are 0!",
                    "Not enough quantity!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (Integer.parseInt(existingBook.getBorrowed_quantity()) >0) {
                existingBook.setBorrowed_quantity(String.valueOf(Integer.parseInt(existingBook.getBorrowed_quantity())-1));
                JOptionPane.showMessageDialog(jf, "Successfull!",
                        "Returned book!", JOptionPane.INFORMATION_MESSAGE);
                bookService.updateBook(existingBook);

            }
        }


        return "redirect:/books";
    }


    @PostMapping(value="/books/{id}")
    public String updateBooks(@PathVariable Long id, @ModelAttribute("book") Book book, Model model, @RequestParam String genre) {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        if(book.getName()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }
        if(book.getAuthor()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the author can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }
        if(genre.equals("Genre:")) {
            JOptionPane.showMessageDialog(jf, "Genre of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        if (!book.getYear().matches("\\d+")) {
            JOptionPane.showMessageDialog(jf, "Year of the book must contain only numbers!",
                    "Incorect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        if(Integer.parseInt(book.getYear())>2024 || Integer.parseInt(book.getYear())<100){
            JOptionPane.showMessageDialog(jf, "Year of the book must be in the range of 100 and 2024!",
                    "Inccorect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        if (!book.getTotal_quantity().matches("\\d+")) {
            JOptionPane.showMessageDialog(jf, "Quantity of the book must contain only numbers!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }

        if(Integer.parseInt(book.getTotal_quantity())<=0){
            JOptionPane.showMessageDialog(jf, "Quantity of the book must be bigger than 0!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }
        
        if(Integer.parseInt((book.getTotal_quantity()))<Integer.parseInt(book.getBorrowed_quantity())){
            JOptionPane.showMessageDialog(jf, "There are already more books borrowed!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            return "edit_books";
        }



        Book existingBook = bookService.getBookById(id);
        existingBook.setId(id);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setName(book.getName());
        existingBook.setYear(book.getYear());
        existingBook.setTotal_quantity(book.getTotal_quantity());
        existingBook.setGenre("Test");

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Updated existing book!", JOptionPane.INFORMATION_MESSAGE);

        bookService.updateBook(existingBook);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (jf, "Are you sure you want to delete that book?","Warning",dialogButton);
        if(dialogResult == 0){
            bookService.deleteBookById(id);
            return "redirect:/books";
        }
        return "redirect:/books";
    }

}
