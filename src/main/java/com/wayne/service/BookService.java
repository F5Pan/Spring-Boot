package com.wayne.service;

import com.wayne.domain.Book;
import com.wayne.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wayne on 2020/04/10.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    /**
     * 查詢所有
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 儲存書籍的訊息
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 取得單一筆資料
     * @param id
     * @return
     */
    public Book findOne(long id) {
        return bookRepository.findOne(id);
    }

    /**
     * 刪除訊息
     * @param id
     */
    public void delete(long id) {
        bookRepository.delete(id);
    }
}
