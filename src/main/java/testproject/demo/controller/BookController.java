package testproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testproject.demo.entity.*;
import testproject.demo.repository.GenreRepository;
import testproject.demo.repository.UserRepository;
import testproject.demo.service.BookService;
import testproject.demo.service.BorrowedHistoryService;
import testproject.demo.service.GenreService;
import testproject.demo.service.UserService;


import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;



@Controller
public class BookController {

@Autowired
    private BookService bookService;

    @Autowired //used to inject instance of a class into another class. Dependency injection is a programming technique that makes a class independent of its dependencies.
    BorrowedHistoryService historyService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private UserService userService;
    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }
    @GetMapping(value="/books") //shortcut for RequestMapping(method=RequestMethod.GET)
    public String listBooks(Model model){ //Model - used to transfer data between the view and controller
        List<BookAndGenre> genresName= new ArrayList<BookAndGenre>();
        String genreName="";
       model.addAttribute("books",bookService.getAllBooks());
       model.addAttribute("searchText");
       for(int i=0;i<bookService.getAllBooks().size();i++) {
           genreName = genreService.getGenreById(bookService.getAllBooks().get(i).getGenre()).getGenre_name();
           BookAndGenre bookandgenre = new BookAndGenre(bookService.getAllBooks().get(i).getId(),bookService.getAllBooks().get(i).getName(), bookService.getAllBooks().get(i).getAuthor(), bookService.getAllBooks().get(i).getYear(), genreName, bookService.getAllBooks().get(i).getTotal_quantity(), bookService.getAllBooks().get(i).getBorrowed_quantity());
           genresName.add(bookandgenre);
       }
       model.addAttribute("genresName",genresName);
        return "books";
    }

    @GetMapping(value="/books/add")
    public String createBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        List<Genre> genresList=(List<Genre>) genreService.getAllGenres();
        Map<Integer,String> genres=new HashMap<Integer,String>();
        for (Genre genree:genresList){
            genres.put(genree.getGenre_id(),genree.getGenre_name());
        }
        model.addAttribute("genresList",genresList);
        model.addAttribute("genres",genres);
        return "add_books";
    }


    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book) throws Exception {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        if(book.getName()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }
        if(book.getAuthor()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the author can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }
        for(char chars: String.valueOf(book.getYear()).toCharArray()){
            if(!Character.isDigit(chars)){
                JOptionPane.showMessageDialog(jf, "Year of the book must contain only numbers!",
                        "Incorect field!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(book.getYear()>2024 || book.getYear()<100){
            JOptionPane.showMessageDialog(jf, "Year of the book must be in the range of 100 and 2024!",
                    "Inccorect field!", JOptionPane.ERROR_MESSAGE);
        }
        if (!String.valueOf(book.getTotal_quantity()).matches("\\d+")) {
            JOptionPane.showMessageDialog(jf, "Quantity of the book must contain only numbers!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
        }
        if(book.getTotal_quantity()<0){
            JOptionPane.showMessageDialog(jf, "Quantity of the book must be bigger than 0!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Added new book!", JOptionPane.INFORMATION_MESSAGE);
            book.setBorrowed_quantity(0);
            bookService.saveBook(book);
            return "redirect:/books";

    }

    @GetMapping("/books/edit/{id}")
    public String updateBookForm(@PathVariable int id, Model model){
        List<Genre> genresList=(List<Genre>) genreService.getAllGenres();
        Map<Integer,String> genres=new HashMap<Integer,String>();
        for (Genre genree:genresList){
            genres.put(genree.getGenre_id(),genree.getGenre_name());
        }

        model.addAttribute("genresList",genresList);
        model.addAttribute("genres",genres);


        model.addAttribute("book",bookService.getBookById(id));
        return "edit_books";
    }



    @GetMapping("/books/return/{id}")
    public String returnBook(@PathVariable int id, @ModelAttribute("book") Book book, Model model){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);

        Book existingBook = bookService.getBookById(id);
        if(existingBook.getBorrowed_quantity()<=0 || existingBook.getBorrowed_quantity()==0){
            JOptionPane.showMessageDialog(jf, "The borrowed books are 0!",
                    "Not enough quantity!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (existingBook.getBorrowed_quantity() >0) {
                existingBook.setBorrowed_quantity(existingBook.getBorrowed_quantity()-1);
                JOptionPane.showMessageDialog(jf, "Successfull!",
                        "Returned book!", JOptionPane.INFORMATION_MESSAGE);
                bookService.updateBook(existingBook);

            }
        }
        return "redirect:/books";
    }


    @PostMapping(value="/books/{id}")
    public String updateBooks(@PathVariable int id, @ModelAttribute("book") Book book, Model model, @RequestParam String genre)  {
        List<Genre> genresList=(List<Genre>) genreService.getAllGenres();
        Map<Integer,String> genres=new HashMap<Integer,String>();
        for (Genre genree:genresList){
            genres.put(genree.getGenre_id(),genree.getGenre_name());
        }
        model.addAttribute("genresList",genresList);
        model.addAttribute("genres",genres);
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        if(book.getName()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }
        if(book.getAuthor()=="") {
            JOptionPane.showMessageDialog(jf, "Name of the author can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }
        if(genre.equals("Genre:")) {
            JOptionPane.showMessageDialog(jf, "Genre of the book can not be empty!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }

            if (book.getYear() > 2024 || book.getYear() < 100) {
                JOptionPane.showMessageDialog(jf, "Year of the book must be in the range of 100 and 2024!",
                        "Inccorect field!", JOptionPane.ERROR_MESSAGE);
            }
            if (book.getTotal_quantity()<1) {
            JOptionPane.showMessageDialog(jf, "Quantity of the book must cbe at least 1!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            }
            if(book.getTotal_quantity()<bookService.getBookById(id).getBorrowed_quantity()){
            JOptionPane.showMessageDialog(jf, "There are already more books borrowed!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            }

        if(book.getName()!="" && book.getAuthor()!="" && book.getGenre()!=0 && book.getYear()<2024 && book.getYear()>100 && String.valueOf(book.getYear()).matches("\\d+") && book.getTotal_quantity()>0 && book.getTotal_quantity()>bookService.getBookById(id).getBorrowed_quantity()){
        Book existingBook = bookService.getBookById(id);
        existingBook.setId(id);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setName(book.getName());
        existingBook.setYear(book.getYear());
        existingBook.setTotal_quantity(book.getTotal_quantity());
        existingBook.setGenre(book.getGenre());

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Updated existing book!", JOptionPane.INFORMATION_MESSAGE);

        bookService.updateBook(existingBook);
            return "redirect:/books";
        }
        return "edit_books";
    }

    @GetMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (jf, "Are you sure you want to delete that book and all of its history?","Warning",dialogButton);
        if(dialogResult == 0){
            bookService.deleteBookById(id);
            return "redirect:/books";
        }
        return "redirect:/books";
    }

    @GetMapping("/books/search/{searchText}")
    public String searchArticles(Model model,@PathVariable String searchText) {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        List<Book> foundArticles =bookService.searchBooks(searchText);

        if (!foundArticles.isEmpty()) {
            List<BookAndGenre> genresName= new ArrayList<BookAndGenre>();
            String genreName="";
            model.addAttribute("books",foundArticles);
            for(int i=0;i<foundArticles.size();i++) {
                genreName = genreService.getGenreById(foundArticles.get(i).getGenre()).getGenre_name();
                BookAndGenre bookandgenre = new BookAndGenre(foundArticles.get(i).getId(),foundArticles.get(i).getName(), foundArticles.get(i).getAuthor(),foundArticles.get(i).getYear(), genreName, foundArticles.get(i).getTotal_quantity(), foundArticles.get(i).getBorrowed_quantity());
                genresName.add(bookandgenre);
            }
            model.addAttribute("genresName",genresName);
            return "books";

        } else {
            JOptionPane.showMessageDialog(jf, "Nothing found!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);


        }
        return "redirect:/books";
    }

    @GetMapping("/books/borrow/{id}")
    public String borrowBookForm(@PathVariable int id, Model model){
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        Book selectedBook= bookService.getBookById(id);
        if(selectedBook.getTotal_quantity()<=selectedBook.getBorrowed_quantity()){
            JOptionPane.showMessageDialog(jf, "Not enough quantity!!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        else{
        List<User> genresName= new ArrayList<User>();
        String genreName="";
        model.addAttribute("usersList",userService.getAllUsers());
        model.addAttribute("bookID",id);
        return "borrow_books";}
        return "redirect:/books";
    }
    @PostMapping(value="/books/borrow/search")
    public String forwardSearchUsers(@RequestParam String searchText,@RequestParam String bookID) {
        return "redirect:/books/borrow/" +bookID+"/search/"+searchText;
    }
    @GetMapping("/books/borrow/{bookID}/search/{searchText}")
    public String searchArticlesUser(Model model, @PathVariable String bookID,@PathVariable String searchText) {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        List<User> foundArticles=userService.searchUsers(searchText);
        System.out.println(bookID);

        if (!foundArticles.isEmpty()) {
            model.addAttribute("usersList",foundArticles);
            return "borrow_books";


        } else {
            JOptionPane.showMessageDialog(jf, "Nothing found!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
        }
        //return "borrow_books";
        return "borrow_books";
    }
    @PostMapping("/books/borrow/save")
    public String saveBorrowedBook(@ModelAttribute("user") User user, Model model,@RequestParam String bookID,@RequestParam String userID) throws Exception {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        Book selectedBook=bookService.getBookById(Integer.parseInt(bookID));


        LocalDate currentDate = LocalDate.now();

        String today = currentDate.toString();



        BorrowedHistory borrowedHistory=new BorrowedHistory(Integer.parseInt(bookID),Integer.parseInt(userID),today,"");
        System.out.println("userID:"+userID);
        System.out.println("bookId:"+bookID);
        System.out.println("date:"+today);

        selectedBook.setBorrowed_quantity(selectedBook.getBorrowed_quantity()+1);

        JOptionPane.showMessageDialog(jf, "Successfull!",
                "Added new user!", JOptionPane.INFORMATION_MESSAGE);
        historyService.saveBorrowedHistory(borrowedHistory);
        return "redirect:/books";
    }




}
