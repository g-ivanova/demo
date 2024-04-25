package testproject.demo.service.impl;

import org.springframework.stereotype.Service;
import testproject.demo.entity.BorrowedHistory;
import testproject.demo.repository.BorrowedHistoryRepository;
import testproject.demo.service.BorrowedHistoryService;

import java.util.List;

@Service
public class BorrowedHistoryImpl implements BorrowedHistoryService {

    private BorrowedHistoryRepository borrowedHistoryRepository;

    public BorrowedHistoryImpl(BorrowedHistoryRepository borrowedHistoryRepository) {
        this.borrowedHistoryRepository = borrowedHistoryRepository;
    }

    @Override
    public List<BorrowedHistory> getAllBorrowedHistory() {
        return borrowedHistoryRepository.findAll();
    }

    @Override
    public BorrowedHistory saveBorrowedHistory(BorrowedHistory borrowedHistory) {
        return borrowedHistoryRepository.save(borrowedHistory);
    }

    @Override
    public BorrowedHistory getBorrowedHistoryById(int id) {
        return borrowedHistoryRepository.findById(Long.valueOf(id)).get();
    }

    @Override
    public BorrowedHistory updateBorrowedHistory(BorrowedHistory borrowedHistory) {
        return borrowedHistoryRepository.save(borrowedHistory);
    }

    @Override
    public void deleteBorrowedHistoryById(Long id) {
        borrowedHistoryRepository.deleteById(id);
    }

    @Override
    public List<BorrowedHistory> searchHistory(String searchText) {
        return borrowedHistoryRepository.findHistoryBySearchText(searchText);
    }
}
