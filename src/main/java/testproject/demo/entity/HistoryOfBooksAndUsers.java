package testproject.demo.entity;

import java.sql.Date;

public class HistoryOfBooksAndUsers {

    private int borrowed_history_id;

    private int book_id;

    private String book_name;

    private int user_id;

    private String user_fname;

    private String user_lname;

    private String user_phone;

    private String date_borrowed;

    private String date_returned;

    public HistoryOfBooksAndUsers(int borrowed_history_id, int book_id, String book_name, int user_id, String user_fname, String user_lname, String user_phone, String date_borrowed, String date_returned) {
        this.borrowed_history_id = borrowed_history_id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.user_id = user_id;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_phone = user_phone;
        this.date_borrowed = date_borrowed;
        this.date_returned = date_returned;
    }

    public int getBorrowed_history_id() {
        return borrowed_history_id;
    }

    public void setBorrowed_history_id(int borrowed_history_id) {
        this.borrowed_history_id = borrowed_history_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getDate_borrowed() {
        return date_borrowed;
    }

    public void setDate_borrowed(String date_borrowed) {
        this.date_borrowed = date_borrowed;
    }

    public String getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(String date_returned) {
        this.date_returned = date_returned;
    }
}
