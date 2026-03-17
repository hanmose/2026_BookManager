package kr.ac.kopo.kim.bookmarket.repository;
import kr.ac.kopo.mose.bookmarket.domain.Book;
import kr.ac.kopo.mose.bookmarket.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl(){
        Book book1 = new Book();
        book1.setBookId("isbn1001");
        book1.setName("스프링 부트 완전정복");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다. 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다. 그리고 단계별 실습을 따라 하다 보면 도서 쇼핑몰 구축 프로젝트를 완성할 수 있다. 개념-실습-프로젝트의 3단계 학습으로 스프링 부트를 제대로 익힌다면 개발 시간을 단축하고 생산성을 높일 수 있는 개발자로 성장할 수 있다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT전문서");
        book1.setUnitPrice(new BigDecimal(35000));
        book1.setReleaseDate("2024/12/31");

        Book book2 = new Book();
        book2.setBookId("isbn1002");
        book2.setName("오만과 편견");
        book2.setDescription("제인 오스틴이 구사하는 재현의 기술은 셰익스피어에 비견할 만하다.\n" +
                "제인 오스틴은 풍자의 회초리를 들어 사정없이 인물들을 매질하는데, 이러한 풍자에는 늘 옳고 그름을 판별하는 그녀의 완벽하고 예리한 감각이 실려 있다.\n" +
                "햄릿이 영문학의 첫 아들이라면 엘리자베스 베넷은 가장 사랑스러운 딸이다.\n");
        book2.setPublisher("제인 오스틴");
        book2.setCategory("소설/시/희곡");
        book2.setUnitPrice(new BigDecimal(14400));
        book2.setReleaseDate("2003/09/20");

        listOfBooks.add(book1);
        listOfBooks.add(book2);

    }
    @Override
    public List<Book> getAllBookList(){
        return List.of();
    }
}
