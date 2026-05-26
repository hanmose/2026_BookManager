package kr.ac.kopo.mose.bookmarket.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class Book {
    @Pattern(regexp = "isbn[1-9]+")
    private String bookId;//도서 ID
    @Size(min = 4, max = 50)
    private String name;//도서제목
    @Min(value = 0)
    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal unitPrice;//가격
    private String author;//저자
    private String description;//설명
    private String publisher;//출판사
    private String category;//분류
    private long unitsInStock;//재고수
    private String releaseDate;//출판일
    private String condition;//신규도서, 중고도서, E-Book
    private String fileName; //도서이미지파일이름
    private MultipartFile bookImage; //도서이미지파일정보객체
}
