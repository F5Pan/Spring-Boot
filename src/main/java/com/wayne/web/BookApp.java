package com.wayne.web;

import com.wayne.domain.Book;
import com.wayne.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
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
    public Page<Book> getAll(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable) {

    	return bookService.findAllByPage(pageable);
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
    
    /**
     * @param author
     * @return
     * 根據author查詢
     */
    @PostMapping("/books/findByAuthor")
    public List<Book> findByAuthor(@RequestParam String author){
    	return bookService.findByAuthor(author);
    }
    
    /**
     * @param author
     * @param status
     * @return
     * 雙條件查詢
     */
    @PostMapping("/books/findByAuthorAndStatus")
    public List<Book> findByAuthorAndStatus(@RequestParam String author,@RequestParam int status){
    	return bookService.findByAuthorAndStatus(author, status);
    }
    
    /**
     * @param description
     * @return
     * 就是sql 中的 like語句 endsWith 就是以...結尾 
     * select * from book where description like '%工具'
     */
    @PostMapping("/books/findByDescriptionEndsWith")
    public List<Book> findByDescriptionEndsWith(@RequestParam String description){
    	return bookService.findByDescriptionEndsWith(description);
    }
    /**
     * @param description
     * @return
     * 就是sql 中的 like語句 Contains 就是包含
     * select * from book where description like '%工具%'
     */
    @PostMapping("/books/findByDescriptionContains")
    public List<Book> findByDescriptionContains(@RequestParam String description){
    	return bookService.findByDescriptionContains(description);
    }

    @PostMapping("/books/findByJPQL")
    public List<Book> findByJPQL(@RequestParam int len){
    	return bookService.findByJPQL(len);
    }
    
    @PostMapping("/books/updateByJPQL")
    public int updateByJPQL(@RequestParam int status,@RequestParam long id){
    	return bookService.updateByJPQL(status,id);
    }
    
    @PostMapping("/books/deleteByJPQL")
    public int deleteByJPQL(@RequestParam long id){
    	return bookService.deleteByJPQL(id);
    }
    
    @PostMapping("/books/deleteAndUpdate")
    public int deleteAndUpdate(@RequestParam int status,@RequestParam long id ,@RequestParam long uid){
    	return bookService.deleteAndUpdate(status, id, uid);
    }
}
