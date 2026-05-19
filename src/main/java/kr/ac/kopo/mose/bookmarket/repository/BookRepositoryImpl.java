package kr.ac.kopo.mose.bookmarket.repository;

import kr.ac.kopo.mose.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        // 도서 1
        Book book1 = new Book();
        book1.setBookId("isbn1001");
        book1.setName("오만과 편견");
        book1.setDescription("셰익스피어의 뒤를 이어 ‘지난 천 년간 최고의 문학가’로 꼽힌 제인 오스틴");
        book1.setPublisher("민음사");
        book1.setCategory("희곡");
        book1.setAuthor("제인오스본");
        book1.setUnitPrice(new BigDecimal(35000));
        book1.setReleaseDate("2021/02/05");
        book1.setFileName("isbn1001.png"); // 이미지 파일 정보 유지

        // 도서 2
        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("마션");
        book2.setDescription("SF계를 뒤흔든 대담한 데뷔작");
        book2.setPublisher(" 알에이치코리아(RHK)");
        book2.setCategory("소설");
        book2.setAuthor("앤디 위어");
        book2.setUnitPrice(new BigDecimal(29000));
        book2.setReleaseDate("2022/01/15");
        book2.setFileName("isbn1002.png");

        // 도서 3
        Book book3 = new Book();
        book3.setBookId("isbn1003");
        book3.setName("돈키호테");
        book3.setDescription("새롭고도 젊은 한국어판 완역본 『돈키호테』");
        book3.setPublisher("열린책들");
        book3.setCategory("소설");
        book3.setAuthor("미겔 데 세르반테스");
        book3.setUnitPrice(new BigDecimal(34000));
        book3.setReleaseDate("2014/11/12");
        book3.setFileName("isbn1003.png");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    @Override
    public Book getBookById(String bookId) {
        Book bookById = null;
        for (Book book : listOfBooks) {
            if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                bookById = book;
                break;
            }
        }
        if (bookById == null) {
            throw new IllegalArgumentException("도서ID가 " + bookId + "인 도서는 찾을 수가 없습니다.");
        }
        return bookById;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (category.equalsIgnoreCase(book.getCategory())) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByCategory = new HashSet<>();
        Set<Book> booksByPublisher = new HashSet<>();
        Set<String> criteria = filter.keySet();

        if (criteria.contains("publisher")) {
            for (String publisherName : filter.get("publisher")) {
                for (Book book : listOfBooks) {
                    if (publisherName.equalsIgnoreCase(book.getPublisher())) {
                        booksByPublisher.add(book);
                    }
                }
            }
        }

        if (criteria.contains("category")) {
            for (String categoryName : filter.get("category")) {
                booksByCategory.addAll(getBookListByCategory(categoryName));
            }
        }

        // 카테고리 필터와 출판사 필터의 교집합 처리
        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    @Override
    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }
}