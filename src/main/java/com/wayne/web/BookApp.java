package com.wayne.web;

import com.wayne.domain.Book;
import com.wayne.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Wayne on 2020/04/10.
 */
@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    /**
     * 查詢所有
     * @return
     */
    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    /**
     * 新增一筆book
     * @return
     */
    @PostMapping("/books")
    public Book post(Book book) {
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);

        return bookService.save(book);
    }


    /**
     * 取得單一筆資料
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    /**
     * 更新資料
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);

        return bookService.save(book);
    }

    /**
     * 刪除資料
     * @param id
     */
    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id) {

        bookService.delete(id);
    }

}
