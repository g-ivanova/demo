package testproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testproject.demo.entity.Book;
import testproject.demo.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query( nativeQuery = true,value
            = "SELECT * FROM users u WHERE " +
            "LOWER(u.user_fname) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(u.user_fname) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(u.user_phone) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<User> findBooksBySearchText(@Param("searchText") String searchText);

    List<User> findByFname(String fname);
   List<User> findByLname(String lname);
    List<User> findByPhone(String phone);
}
