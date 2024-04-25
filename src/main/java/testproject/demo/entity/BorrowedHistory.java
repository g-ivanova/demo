package testproject.demo.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="borrowed_history")
public class BorrowedHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrowed_history_id")
    private int id;
    @Column(name="book_id")
    private int bookId;
    @Column(name="user_id")
    private int userId;
    @Column(name="date_borrowed")
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private String dateBorrowed;
    @Column(name="date_returned")
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private String dateReturned;

    public BorrowedHistory(){}

    public BorrowedHistory(int id, int bookId, int userId, String dateBorrowed, String dateReturned) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public BorrowedHistory(int bookId, int userId, String dateBorrowed, String dateReturned) {
        this.bookId = bookId;
        this.userId = userId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
}
