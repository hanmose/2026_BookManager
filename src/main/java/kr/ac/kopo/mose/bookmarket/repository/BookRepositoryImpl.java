package kr.ac.kopo.mose.bookmarket.repository;


import kr.ac.kopo.mose.bookmarket.domain.Book;
import kr.ac.kopo.mose.bookmarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository{
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        book1.setBookId("isbn1001");
        book1.setName("스프링부트 완전정복");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다. " +
                "이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다. 그리고 단계별 실습을 따라 하다 보면 도서 쇼핑몰 구축 프로젝트를 완성할 수 있다. 개념-실습-프로젝트의 3단계 학습으로 스프링 부트를 제대로 익힌다면 개발 시간을 단축하고 생산성을 높일 수 있는 개발자로 성장할 수 있다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT전문서");
        book1.setAuthor("송미영");
        book1.setUnitPrice(new BigDecimal(35000));
        book1.setReleaseDate("2024/12/31");
        book1.setFileName("isbn1001.jpg");

        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("데이터베이스 개론");
        book2.setDescription("『데이터베이스 개론』은 데이터베이스로 첫 항해를 떠나는 이들에게 지도와 돛이 되어주는 책이다. 마인드맵으로 생소한 개념 간의 관계를 한눈에 보여주고, 친근한 예와 비유를 들어 명료하게 풀어내, 데이터베이스 기본기를 탄탄히 다져준다. " +
                "3판에서는 빅데이터 표현 기술이 더 추가되었고 다양한 시험의 최신 기술 경향에 맞게 연습문제를 보강하였다. 또한 부록을 통해 최신 버전의 오라클뿐만 아니라 설치하지 않아도 사용할 수 있는 Live SQL을 활용해 데이터베이스를 직접 구축해볼 수 있게 안내하였다.");
        book2.setPublisher("한빛아카데미");
        book2.setCategory("IT교육교재");
        book2.setAuthor("이연희");
        book2.setUnitPrice(new BigDecimal(29000));
        book2.setReleaseDate("2022/01/15");
        book2.setFileName("isbn1002.jpg");

        Book book3 = new Book();
        book3.setBookId("isbn1003");
        book3.setName("안드로이드 프로그래밍");
        book3.setDescription("이 책은 대학교나 IT 전문학원의 안드로이드 프로그래밍 과목 수강생을 대상으로 한다. 기본적인 프로그래밍을 접해본 독자라면 Java 기초부터 시작하여 안드로이드 앱 개발까지 한번에 학습할 수 있도록 구성되어 있다. " +
                "특별히 이번 9판에서는 버전업을 적용하여 JDK 17, Android 14.0(U), Android Studio Hedgehog에서 실습할 수 있다. 또한 연습문제 일부를 변경하였으며, 13장에 ‘경기도 맛집 찾기 앱 만들기’ 프로젝트를 추가하였다. 마지막으로 안드로이드 프로그래밍을 하면서 접할 수 있는 다양한 오류나 실수까지 친절하게 안내하여 시행착오 없이 안드로이드를 빠르게 정복할 수 있을 것이다.");
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
        Book book = null;
        for (Book searchBook: listOfBooks){
            if (searchBook != null && searchBook.getBookId() != null && searchBook.getBookId().equals(bookId)){
                book = searchBook;
                break;
            }
        }

//        if (book == null){
//            throw new IllegalArgumentException("도서ID가 " + bookId + "인 도서는 찾을 수가 없습니다.");
//        }

        if (book == null){
            throw new BookIdException(bookId);
        }

        return book;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for (Book searchBook : listOfBooks){
            if (category.equalsIgnoreCase(searchBook.getCategory()))
                booksByCategory.add(searchBook);
        }

        return booksByCategory;
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<String> booksByFilter = filter.keySet();

        if (booksByFilter.contains("publisher")) {
            for (String publisherName : filter.get("publisher")) {
                for (Book searchBook : listOfBooks) {
                    if (publisherName.equalsIgnoreCase(searchBook.getPublisher()))
                        booksByPublisher.add(searchBook);
                }
            }
        }

        if (booksByFilter.contains("category")) {
            for (String category : filter.get("category")) {
                List<Book> list = getBookListByCategory(category);
                booksByCategory.addAll(list);
            }
        }

        booksByCategory.retainAll(booksByPublisher);

        return booksByCategory;
    }

    @Override
    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }
}
