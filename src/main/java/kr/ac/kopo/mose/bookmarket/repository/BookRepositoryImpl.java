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
        book1.setName("스프링부트 완전정복");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다. 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT전문서");
        book1.setAuthor("송미영");
        book1.setUnitPrice(new BigDecimal(35000));
        book1.setReleaseDate("2024/12/31");
        book1.setFileName("isbn1001.jpg"); // 이미지 파일 정보 유지

        // 도서 2
        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("데이터베이스 개론");
        book2.setDescription("『데이터베이스 개론』은 데이터베이스로 첫 항해를 떠나는 이들에게 지도와 돛이 되어주는 책이다. 마인드맵으로 생소한 개념 간의 관계를 한눈에 보여주고 기본기를 탄탄히 다져준다.");
        book2.setPublisher("한빛아카데미");
        book2.setCategory("IT교육교재");
        book2.setAuthor("이연희");
        book2.setUnitPrice(new BigDecimal(29000));
        book2.setReleaseDate("2022/01/15");
        book2.setFileName("isbn1002.jpg");

        // 도서 3
        Book book3 = new Book();
        book3.setBookId("isbn1003");
        book3.setName("안드로이드 프로그래밍");
        book3.setDescription("이 책은 대학교나 IT 전문학원의 안드로이드 프로그래밍 과목 수강생을 대상으로 한다. Java 기초부터 시작하여 안드로이드 앱 개발까지 한번에 학습할 수 있다.");
        book3.setPublisher("한빛아카데미");
        book3.setCategory("IT교육교재");
        book3.setAuthor("우재남");
        book3.setUnitPrice(new BigDecimal(34000));
        book3.setReleaseDate("2024/01/19");
        book3.setFileName("isbn1003.jpg");

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