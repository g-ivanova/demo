package testproject.demo.entity;

import jakarta.persistence.*;


public class BookAndGenre {


    private int book_id;

    private String name;

    private String author;

    private int year;

    private String genre;

    private int total_quantity;

    private int borrowed_quantity;

    public BookAndGenre(){}
    public BookAndGenre(String name, String author, int year, String genre, int total_quantity, int borrowed_quantity) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity = total_quantity;
        this.borrowed_quantity = borrowed_quantity;
    }

    public BookAndGenre(int book_id, String name, String author, int year, String genre, int total_quantity, int borrowed_quantity) {
        super();
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity = total_quantity;
        this.borrowed_quantity = borrowed_quantity;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getBorrowed_quantity() {
        return borrowed_quantity;
    }

    public void setBorrowed_quantity(int borrowed_quantity) {
        this.borrowed_quantity = borrowed_quantity;
    }
}
