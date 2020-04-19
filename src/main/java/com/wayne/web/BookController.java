package com.wayne.web;

import java.util.List;

import org.codehaus.groovy.util.Finalizable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wayne.domain.Book;
import com.wayne.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	/**
	 * 取得書單清單
	 * Pageable 自動綁定分頁的參數回傳封裝到Pageable的對象
	 * @param model
	 * @return
	 */
	@GetMapping("/books")
	public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,Model model) {
		Page<Book> page1 = bookService.findAllByPage(pageable);
		model.addAttribute("page", page1);
		return "books";
	}

	/**
	 * 取得單筆
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/books/{id}")
	public String detail(@PathVariable long id, Model model) {
		Book book = bookService.findOne(id);
		if (book == null) {
			book = new Book();
		}
		model.addAttribute("book", book);

		return "book";
	}

	/**
	 * 跳轉畫面到input.html
	 * as
	 * @return 
	 */
	@GetMapping("/books/input")
	public String inputPage(Model model) {
		model.addAttribute("book",new Book());
		return "input";
	}

	/**
	 * 新增書單
	 * 
	 * @return
	 */

	@PostMapping("/books")
	public String post(Book book,final RedirectAttributes attributes) {
		Book book1 = bookService.save(book);
		if(book1!=null) {
			attributes.addFlashAttribute("message","<"+book1.getName()+">訊息提交成功");
		}
		return "redirect:/books";
	}
	/**
	 * redirect
	 * POST--->redirect--->Get
	 * 所以原先這個傳送訊息的方式並不適用於redirect轉發 
	 * 	@PostMapping("/books")
		public String post(Book book,Model model) {
		Book book1 = bookService.save(book);
		if(book1!=null) {
			model.addAttribute("message","<"+book1.getName()+">訊息提交成功");
		}
		return "redirect:/books";
	}
	
	*RedirectAttributes 可以跨過redirect 直接從POST到GET 還可以拿到相對應的值
	 */

	
	/**
	 * 跳轉到更新該筆資料畫面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/books/{id}/input")
	public String inputEditPage(@PathVariable long id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "input";
	}
	
	@GetMapping("/books/{id}/delete")
	public String delete(@PathVariable long id,final RedirectAttributes attributes) {
		bookService.delete(id);
		attributes.addFlashAttribute("message","刪除成功");
		return "redirect:/books";
	}
}
