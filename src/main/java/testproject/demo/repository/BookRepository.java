package testproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testproject.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {



}

