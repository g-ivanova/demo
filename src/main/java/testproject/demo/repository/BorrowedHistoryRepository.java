package testproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testproject.demo.entity.Book;
import testproject.demo.entity.BorrowedHistory;

import java.util.List;

public interface BorrowedHistoryRepository extends JpaRepository<BorrowedHistory,Long> {



    @Query( nativeQuery = true,value
            = "SELECT * FROM borrowed_history bh WHERE " +
            "LOWER(bh.book_id) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(bh.user_id) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<BorrowedHistory> findHistoryBySearchText(@Param("searchText") String searchText);



    List<BorrowedHistory> findByUserId(int userId);

    List<BorrowedHistory> findByBookId(int bookId);
}
