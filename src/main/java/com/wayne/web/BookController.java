package com.wayne.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wayne.domain.Book;
import com.wayne.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	/**
	 * 取得書單清單
	 * @param model
	 * @return
	 */
	@GetMapping("/books")
	public String list(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books",books);
		return "books";
	}
	
	/**
	 * 取得單筆
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/books/{id}")
	public String detail(@PathVariable long id,Model model) {
		Book book = bookService.findOne(id);
		if(book==null) {
			 book = new Book();
		}
		model.addAttribute("book",book);

		return "book";
	}
}
