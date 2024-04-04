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
    private Long id;
    @Column(name="book_name")
    private String name;
    @Column(name="book_author")
    private String author;
    @Column(name="book_year")
    private String year;
    @Column(name="book_genre")
    private String genre;
    @Column(name="total_quantity")
    private String total_quantity;
    @Column(name="borrowed_quantity")
    private String borrowed_quantity;


    public Book(){}

    public Book(Long id, String name, String author, String year, String genre, String total_quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity = total_quantity;
    }

    public Book(String name, String author, String year, String genre, String total_quantity, String borrowed_quantity) {
        super();
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.total_quantity=total_quantity;
        this.borrowed_quantity=borrowed_quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getBorrowed_quantity() {
        return borrowed_quantity;
    }

    public void setBorrowed_quantity(String borrowed_quantity) {
        this.borrowed_quantity = borrowed_quantity;
    }
}
