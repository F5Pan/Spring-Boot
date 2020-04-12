package com.wayne.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wayne on 2020/04/10.
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}
