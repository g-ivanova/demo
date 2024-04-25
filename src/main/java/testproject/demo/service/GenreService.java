package testproject.demo.service;

import org.springframework.data.jpa.repository.Query;
import testproject.demo.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getAllGenres();

    Genre saveGenre(Genre genre);

    Genre getGenreById(int id);

    Genre updateGenre(Genre genre);

    void deleteGenreById(Long id);


}
