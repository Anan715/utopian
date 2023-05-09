package com.utopian.tech.rbac.controller;


import com.utopian.tech.rbac.entity.Book;
import com.utopian.tech.rbac.service.BookService;
import com.utopian.tech.response.entity.UtopianResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/book/list/save")
    public UtopianResponse saveBookList() throws Exception {
        bookService.saveBookList();
        return UtopianResponse.successWithoutData();
    }

    @GetMapping("/book/list")
    public UtopianResponse<List<Book>> getBookList() throws Exception {
        return UtopianResponse.successWithData(bookService.getBoolList());
    }


}

