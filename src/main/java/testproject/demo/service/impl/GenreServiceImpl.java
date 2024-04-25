package testproject.demo.service.impl;

import org.springframework.stereotype.Service;
import testproject.demo.entity.Genre;
import testproject.demo.repository.GenreRepository;
import testproject.demo.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;


    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(int id) {
        return genreRepository.findById((long) id).get();
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }




}
