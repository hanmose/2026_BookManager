package kr.ac.kopo.mose.bookmarket.controller;

import kr.ac.kopo.mose.bookmarket.domain.Book;
import kr.ac.kopo.mose.bookmarket.service.BookService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse; // Spring Boot 3.x 기준 (2.x 이하인 경우 javax.servlet.http.HttpServletResponse)
import org.springframework.util.FileCopyUtils;     // 파일 복사용 유틸리티 추가

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Value("${file.uploadDir}")
    String fileDir;

    @RequestMapping(method = RequestMethod.GET)
    public String requestBookList(Model model){
        List<Book> listOfBooks = bookService.getAllBookList();
        model.addAttribute("bookList", listOfBooks);
        return "books";
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/{category}")
    public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model){
        List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model){
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }

    @GetMapping("/add")
    public String requestAddBookForm(){
        return "addBook";
    }

    @PostMapping("/add")
    public String submitAddNewBook(@ModelAttribute Book book){
        MultipartFile bookImage = book.getBookImage();
        System.out.println("파일사이즈" + bookImage.getSize());
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir, saveName);
        if (bookImage != null && !bookImage.isEmpty()){
            try {
                bookImage.transferTo(saveFile);
            } catch (IOException e) {
                throw new RuntimeException("이미지가 업로드 되지 않았습니다.");
            }
        }
        book.setFileName(saveName);
        bookService.setNewBook(book);
        return "redirect:/books";
    }

    @ModelAttribute
    public void addAddtributes(Model model){
        model.addAttribute("addTitle", "신규 도서 등록");
    }

    // ----------------------------------------
    // [추가된 이미지 다운로드 메서드]
    // ----------------------------------------
    @GetMapping("/download")
    public void downloadBookImage(@RequestParam("file") String paramKey, HttpServletResponse response) {
        File imgFile = new File(fileDir + File.separator + paramKey);

        response.setContentType("application/download");
        response.setContentLength((int) imgFile.length());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + paramKey + "\"");

        // try-with-resources 구문을 사용하여 스트림 자원이 자동으로 닫히도록 개선 (에러 방지)
        try (InputStream fileIn = new FileInputStream(imgFile);
             OutputStream out = response.getOutputStream()) {

            FileCopyUtils.copy(fileIn, out);
            out.flush(); // 데이터를 완전히 밀어내기

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ModelAndView requestAllBooks(){
        ModelAndView modelAndView = new ModelAndView();
        List<Book> list = bookService.getAllBookList();
        modelAndView.addObject("bookList", list);
        modelAndView.setViewName("books");
        return modelAndView;
    }
}