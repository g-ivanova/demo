package testproject.demo.service;

import testproject.demo.entity.Book;
import testproject.demo.entity.BorrowedHistory;

import java.util.List;

public interface BorrowedHistoryService {

    List<BorrowedHistory> getAllBorrowedHistory();

    BorrowedHistory saveBorrowedHistory(BorrowedHistory borrowedHistory);

    BorrowedHistory getBorrowedHistoryById(int id);

    BorrowedHistory updateBorrowedHistory(BorrowedHistory borrowedHistory);

    void deleteBorrowedHistoryById(Long id);

    List<BorrowedHistory> searchHistory(String searchText);
}
