package testproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testproject.demo.entity.Book;
import testproject.demo.entity.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> { //interface extends JpaRepository

    @Query(
            nativeQuery = true,
            value
                    = "SELECT * FROM genres g where g.genre_name =?1")
    Genre findGenreByName(String genre_name);

    @Query( nativeQuery = true,value
            = "SELECT * FROM books b WHERE " +
            "LOWER(b.book_name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(b.book_author) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Book> findBooksBySearchText(@Param("searchText") String searchText);



    List<Book> findByAuthor(String author); //jpa supports find,read,query,count and get

    List<Book> findByName(String name);




}

