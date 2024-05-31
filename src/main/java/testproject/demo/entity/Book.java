package testproject.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;


@Entity
@Table(name="books") //if we have db
public class Book {

    //test changes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate the primary key value by the database itself using the auto-increment column
    private int book_id;
    @Column(name="book_name")
    private String name;
    @Column(name="book_author")
    private String author;
    @Column(name="book_year")
    private int year;
    @Column(name="genre_id")
    private int genre;
    @Column(name="total_quantity")
    private int total_quantity;
    @Column(name="borrowed_quantity")
    private int borrowed_quantity=0;


    public Book(){}

    public Book(int id, String name, String author, int year, int genre, int total_quantity) {
        this.book_id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity = total_quantity;
    }

    public Book(String name, String author, int year, int genre, int total_quantity, int borrowed_quantity) {
        super();
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity=total_quantity;
        this.borrowed_quantity=borrowed_quantity;
    }

    public int getId() {
        return book_id;
    }

    public void setId(int id) {
        this.book_id = id;
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

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
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
