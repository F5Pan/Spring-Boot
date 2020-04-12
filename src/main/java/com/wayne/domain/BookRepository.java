package com.wayne.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Wayne on 2020/04/10. 命名是有規則的 才會對應到相對應的SQL語句 可以參考 Spring官網文件
 * https://docs.spring.io/spring-data/jpa/docs/2.2.6.RELEASE/reference/html/#jpa.query-methods
 */

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthor(String author);

	List<Book> findByAuthorAndStatus(String author, int status);

	List<Book> findByDescriptionEndsWith(String des);

	List<Book> findByDescriptionContains(String des);

	/**
	 * @param len
	 * @return
	 * @Query 就可以自定義sql語句
	 */
	// 後面1 表示一個參數 若是還要加參數 可以寫成 and b.author = ?2
	// @Query("select b from Book b where length(b.name) > ?1")
	// ↑別名 ↑實體對象類

	// 加了nativeQuert=true 就可以直接用sql語句查詢，from也直接指定table
	@Query(value = "select * from book where LENGTH(name)>?1", nativeQuery = true)
	List<Book> findByJPQL(int len);

	@Transactional
	@Modifying // 更新語句要多加這個註解
	@Query("update Book b set b.status=?1 where id=?2")
	int updateByJPQL(int status, long id);

	@Transactional
	@Modifying // 更新語句要多加這個註解
	@Query("delete from Book b where b.id=?1")
	int deleteByJPQL(long id);
	
	
	
}
