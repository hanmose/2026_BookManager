package kr.ac.kopo.mose.bookmarket.service;

import kr.ac.kopo.mose.bookmarket.domain.Book;
import kr.ac.kopo.mose.bookmarket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceomple implements BookService{
    @Autowired
    private BookRepository BookRepository;

    @Override
    public List<Book> getAllBookList() {
        return BookRepository.getAllBookList();
    }
}