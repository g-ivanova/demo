package testproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testproject.demo.entity.*;
import testproject.demo.service.BookService;
import testproject.demo.service.BorrowedHistoryService;
import testproject.demo.service.UserService;

import javax.swing.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    private BorrowedHistoryService borrowedHistoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @GetMapping(value="/history")
    public String listHistory(Model model){ //used to transfer data between the view and controller
        List<HistoryOfBooksAndUsers> historyList= new ArrayList<HistoryOfBooksAndUsers>();

        int borrowed_id;
        int book_id;
        int user_id;
        String date_borrowed;
        String date_returned;
        String userfname;
        String userlname;
        String userphone;
        String bookname;

        model.addAttribute("searchText");
        for(int i=0;i<borrowedHistoryService.getAllBorrowedHistory().size();i++) {
            borrowed_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getId();
            book_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getBookId();
            bookname=bookService.getBookById(borrowedHistoryService.getAllBorrowedHistory().get(i).getBookId()).getName();
            user_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId();
            userfname=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getFname();
            userlname=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getLname();
            userphone=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getPhone();
            date_borrowed=borrowedHistoryService.getAllBorrowedHistory().get(i).getDateBorrowed();
            date_returned=borrowedHistoryService.getAllBorrowedHistory().get(i).getDateReturned();

            HistoryOfBooksAndUsers historyOfBooksAndUsers=new HistoryOfBooksAndUsers(borrowed_id,book_id,bookname,user_id,userfname,userlname,userphone,date_borrowed,date_returned);
            historyList.add(historyOfBooksAndUsers);
            Collections.sort(historyList, Comparator.comparingInt(HistoryOfBooksAndUsers::getBorrowed_history_id).reversed());
        }
        //model.addAttribute(")
        model.addAttribute("historyList",historyList);
        return "history";
    }

    @GetMapping("/history/return/{id}")
    public String updateDateReturned(@PathVariable int id, Model model){

        model.addAttribute("history",borrowedHistoryService.getBorrowedHistoryById(id));
        model.addAttribute("userfname",userService.getUserById(borrowedHistoryService.getBorrowedHistoryById(id).getUserId()).getFname());
       model.addAttribute("userlname",userService.getUserById(borrowedHistoryService.getBorrowedHistoryById(id).getUserId()).getLname());
       model.addAttribute("bookname",bookService.getBookById(borrowedHistoryService.getBorrowedHistoryById(id).getBookId()).getName());
        model.addAttribute("userphone",userService.getUserById(borrowedHistoryService.getBorrowedHistoryById(id).getUserId()).getPhone());

        return "return_book"; //html file
    }

    @PostMapping(value="/history/{id}")
    public String returnBook(@PathVariable int id, @ModelAttribute("history") BorrowedHistory borrowedHistory, Model mode) throws ParseException {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date pickedDate=simpleDateFormat.parse(String.valueOf(borrowedHistory.getDateReturned()));
        java.util.Date today=new java.util.Date();
        java.util.Date borrowedDate=simpleDateFormat.parse( borrowedHistoryService.getBorrowedHistoryById(id).getDateBorrowed());

        if(pickedDate.after(today) || pickedDate.before(borrowedDate)){
            JOptionPane.showMessageDialog(jf, "Date can not be after today and before the borrowing date!",
                    "Empty field!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            BorrowedHistory existingBorrowedHistory = borrowedHistoryService.getBorrowedHistoryById(id);
            existingBorrowedHistory.setDateReturned(String.valueOf(borrowedHistory.getDateReturned()));
            bookService.getBookById(borrowedHistoryService.getBorrowedHistoryById(id).getBookId()).setBorrowed_quantity(bookService.getBookById(borrowedHistoryService.getBorrowedHistoryById(id).getBookId()).getBorrowed_quantity()-1);

            JOptionPane.showMessageDialog(jf, "Successfull!",
                    "Succesfull return!", JOptionPane.INFORMATION_MESSAGE);
            borrowedHistoryService.updateBorrowedHistory(existingBorrowedHistory);
      }
            return "redirect:/history";

    }



    @PostMapping(value="/history/search")
    public String forwardSearch(@RequestParam String searchText) {
        return "redirect:/history/search" + searchText;
    }
    @GetMapping("/history/search/{searchText}")
    public String searchArticles(Model model,@PathVariable String searchText) {
        JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
        List<Book> foundArticles =bookService.searchBooks(searchText);
        System.out.println(searchText);

        if (!foundArticles.isEmpty()) {
            List<HistoryOfBooksAndUsers> historyList= new ArrayList<HistoryOfBooksAndUsers>();

            int borrowed_id;
            int book_id;
            int user_id;
            String date_borrowed;
            String date_returned;
            String userfname;
            String userlname;
            String userphone;
            String bookname;

            model.addAttribute("searchText");
            for(int i=0;i<borrowedHistoryService.getAllBorrowedHistory().size();i++) {
                borrowed_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getId();
                book_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getBookId();
                bookname=bookService.getBookById(borrowedHistoryService.getAllBorrowedHistory().get(i).getBookId()).getName();
                user_id=borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId();
                userfname=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getFname();
                userlname=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getLname();
                userphone=userService.getUserById(borrowedHistoryService.getAllBorrowedHistory().get(i).getUserId()).getPhone();
                date_borrowed=borrowedHistoryService.getAllBorrowedHistory().get(i).getDateBorrowed();
                date_returned=borrowedHistoryService.getAllBorrowedHistory().get(i).getDateReturned();

                HistoryOfBooksAndUsers historyOfBooksAndUsers=new HistoryOfBooksAndUsers(borrowed_id,book_id,bookname,user_id,userfname,userlname,userphone,date_borrowed,date_returned);
                historyList.add(historyOfBooksAndUsers);
            }
            model.addAttribute("historyList",historyList);
            return "history";
        } else {
            JOptionPane.showMessageDialog(jf, "Nothing found!",
                    "Incorrect field!", JOptionPane.ERROR_MESSAGE);
            System.out.println("False"+searchText);
        }
        return "history";
    }


}
