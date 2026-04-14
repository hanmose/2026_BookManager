package kr.ac.kopo.mose.bookmarket.repository;

import kr.ac.kopo.mose.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl(){
        Book book1 = new Book();
        book1.setBookId("isbn1001");
        book1.setName("스프링 부트 완전정복");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT전문서");
        book1.setUnitPrice(new BigDecimal(35000));

        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("오만과 편견");
        book2.setPublisher("제인오스틴");
        book2.setCategory("소설/시/희곡");
        book2.setUnitPrice(new BigDecimal(14400));

        Book book3 = new Book();
        book3.setBookId("isbn1003");
        book3.setName("데미안");
        book3.setPublisher("민음사");
        book3.setCategory("소설/시/희곡");
        book3.setUnitPrice(new BigDecimal(12000));

        Book book4 = new Book();
        book4.setBookId("isbn1004");
        book4.setName("위대한 개츠비");
        book4.setPublisher("문학동네");
        book4.setCategory("소설/시/희곡");
        book4.setUnitPrice(new BigDecimal(13000));

        Book book5 = new Book();
        book5.setBookId("isbn1005");
        book5.setName("노인과 바다");
        book5.setPublisher("열린책들");
        book5.setCategory("소설/시/희곡");
        book5.setUnitPrice(new BigDecimal(11000));

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);
        listOfBooks.add(book5);
    }

    @Override
    public List<Book> getAllBookList() { return listOfBooks; }

    @Override
    public Book getBookById(String bookId) {
        return listOfBooks.stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾을 수 없습니다."));
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : listOfBooks) {
            if (category.equalsIgnoreCase(b.getCategory())) result.add(b);
        }
        return result;
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> result = new HashSet<>();
        if (filter.containsKey("publisher")) {
            for (String pub : filter.get("publisher")) {
                for (Book b : listOfBooks) {
                    if (pub.equalsIgnoreCase(b.getPublisher())) result.add(b);
                }
            }
        }
        if (filter.containsKey("category")) {
            for (String cat : filter.get("category")) {
                result.addAll(getBookListByCategory(cat));
            }
        }
        return result;
    }
}