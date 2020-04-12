package com.wayne.web;

import com.wayne.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wayne on 2020/04/10.
 */
//@Controller
@RestController
@RequestMapping("/api/v2")
public class HelloController {




//    @RequestMapping(value = "/say", method = RequestMethod.POST)
    @PostMapping("/say")
    public String hello() {

        return "Hello Spring Boot";
    }

    @GetMapping("/books")
//    @ResponseBody
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size",defaultValue = "10") int size) {

        Map<String, Object> book = new HashMap<>();
        book.put("name", "物聯網");
        book.put("isbn", "9877234623432");
        book.put("author", "潘識文");
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "coding練習");
        book2.put("isbn", "12312312");
        book2.put("author", "auhi");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("content", contents);

        return pagemap;
    }


    /**
     * 正規表示式：{參數名稱:正規表示式}
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {

        return null;
    }



    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam("author") String author,
                       @RequestParam("isbn") String isbn) {
        Map<String, Object> book = new HashMap<String, Object>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);
        return book;
    }
}
