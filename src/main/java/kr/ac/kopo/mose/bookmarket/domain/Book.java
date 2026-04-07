package kr.ac.kopo.mose.bookmarket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
public class Book {
    private String bookId;//도서 ID
    private String name;//도서제목
    private BigDecimal unitPrice;//가격
    private String author;//저자
    private String description;//설명
    private String publisher;//출판사
    private String category;//분류
    private long unitsInStock;//재고수
    private String releaseDate;//출판일
    private String condition;//신규도서, 중고도서, E-Book
}
