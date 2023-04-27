package com.utopian.tech.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utopian.tech.rbac.entity.Book;

import java.util.List;

/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
public interface BookService extends IService<Book> {

    void updateBook();


    void updateBookWithLock();


    List<Book> getBoolList() throws Exception;

    void saveBookList();

}
