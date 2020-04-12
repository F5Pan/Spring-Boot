package com.wayne.service;

import com.wayne.domain.Book;
import com.wayne.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 
	 * @return
	 */
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	/**
	 * 儲存書籍的訊息
	 * 
	 * @param book
	 * @return
	 */
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * 取得單一筆資料
	 * 
	 * @param id
	 * @return
	 */
	public Book findOne(long id) {
		return bookRepository.findOne(id);
	}

	/**
	 * 刪除訊息
	 * 
	 * @param id
	 */
	public void delete(long id) {
		bookRepository.delete(id);
	}

	/**
	 * @param author
	 * @return 根據author查詢
	 */
	public List<Book> findByAuthor(String author) {

		return bookRepository.findByAuthor(author);
	}

	/**
	 * @param author
	 * @param status
	 * @return 雙條件查詢
	 */
	public List<Book> findByAuthorAndStatus(String author, int status) {

		return bookRepository.findByAuthorAndStatus(author, status);
	}

	/**
	 * @param description
	 * @return 就是sql 中的 like語句 endsWith 就是以...結尾 select * from book where
	 *         description like '%工具'
	 */
	public List<Book> findByDescriptionEndsWith(String des) {
		return bookRepository.findByDescriptionEndsWith(des);
	}

	/**
	 * @param description
	 * @return 就是sql 中的 like語句 Contains 就是包含 select * from book where description
	 *         like '%工具%'
	 */
	public List<Book> findByDescriptionContains(String des) {
		return bookRepository.findByDescriptionContains(des);
	}

	public List<Book> findByJPQL(int len){
		return bookRepository.findByJPQL(len);
	}
	
	//刪除或是更新 需添加進事務管理
	@Transactional
	public int updateByJPQL(int status,long id){
		return bookRepository.updateByJPQL(status,id);
	}
	@Transactional
	public int deleteByJPQL(long id){
		return bookRepository.deleteByJPQL(id);
	}
	
	/**
	 * @param status
	 * @param id
	 * @param uid
	 * @return
	 * 測試JPA中事務操作方法
	 * 類似rollback效果 兩個都要完成 才會完成  其中一個未成功全部都操作都不會做動
	 */
	@Transactional
	public int deleteAndUpdate(int status,long id ,long uid) {
		int dcount = bookRepository.deleteByJPQL(id);
		int ucount = bookRepository.updateByJPQL(status,uid);
		
		return dcount+ucount;
		
	}
}
