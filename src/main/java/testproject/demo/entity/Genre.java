package testproject.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="genres")
public class Genre {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;

    @Column(name="genre_name")
    private String genre_name;

    public Genre(){}

    public Genre(int genre_id, String genre_name) {
        super();
        this.genre_id = genre_id;
        this.genre_name = genre_name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
