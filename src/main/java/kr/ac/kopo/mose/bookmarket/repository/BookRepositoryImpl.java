package kr.ac.kopo.mose.bookmarket.repository;

import kr.ac.kopo.mose.bookmarket.domain.Book;
import kr.ac.kopo.mose.bookmarket.exception.BookIdException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        book1.setBookId("isbn1001");
        book1.setName("오만과 편견");
        book1.setDescription("19세기 영국 시골 마을을 배경으로 사랑과 결혼을 둘러싼 청춘들의 오해와 성장을 그린 제인 오스틴의 대표작입니다. 첫인상에 대한 경솔한 판단과 신분차에서 비롯된 편견을 극복하며 진정한 이해에 이르는 과정을 위트 있고 섬세한 필치로 담아냈습니다.");
        book1.setPublisher("민음사");
        book1.setCategory("세계문학");
        book1.setAuthor("제인 오스틴");
        book1.setUnitPrice(new BigDecimal(13000));
        book1.setReleaseDate("2014/03/20");
        book1.setFileName("isbn1001.jpg");

        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("위대한 개츠비");
        book2.setDescription("1920년대 재즈 시대의 화려함 뒤에 숨은 아메리칸드림의 환멸과 인간 고독을 그려낸 피츠제럴드의 최고작입니다. 건너편 둔치의 초록 불빛을 향해 한없이 열망했던 개츠비의 비극적인 사랑을 통해 시대의 물질주의와 인간의 서글픈 열망을 밀도 있게 조명합니다.");
        book2.setPublisher("문학동네");
        book2.setCategory("세계문학");
        book2.setAuthor("F. 스콧 피츠제럴드");
        book2.setUnitPrice(new BigDecimal(11000));
        book2.setReleaseDate("2010/05/17");
        book2.setFileName("isbn1002.jpg");

        Book book3 = new Book();
        book3.setBookId("isbn1003");
        book3.setName("1984");
        book3.setDescription("빅브라더라는 절대적 권력이 개인의 정보와 사상까지 통제하는 디스토피아 사회를 그린 조지 오웰의 현대 고전입니다. 언어 왜곡과 역사 조작을 통해 인간성을 파괴하는 통제 체제의 비극을 날카롭게 파헤치며 현대 사회를 살아가는 이들에게 강렬한 경종을 울립니다.");
        book3.setPublisher("민음사");
        book3.setCategory("세계문학");
        book3.setAuthor("조지 오웰");
        book3.setUnitPrice(new BigDecimal(12000));
        book3.setReleaseDate("2007/03/30");
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
        for (Book searchBook : listOfBooks) {
            if (searchBook != null && searchBook.getBookId() != null && searchBook.getBookId().equals(bookId)) {
                book = searchBook;
                break;
            }
        }

        if (book == null) {
            throw new BookIdException(bookId);
        }

        return book;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        for (Book searchBook : listOfBooks) {
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