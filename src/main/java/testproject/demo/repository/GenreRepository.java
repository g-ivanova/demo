package testproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import testproject.demo.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends
        JpaRepository<Genre,Long>,
        CrudRepository<Genre,Long> {

    @Query(
            nativeQuery = true,
            value
                    = "SELECT * FROM genres g where g.genre_name =?1")
    Genre findGenreByName(String genre_name);
}
