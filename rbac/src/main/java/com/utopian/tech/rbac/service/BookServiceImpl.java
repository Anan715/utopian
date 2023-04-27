package com.utopian.tech.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utopian.tech.rbac.entity.Book;
import com.utopian.tech.rbac.mapper.BookMapper;
import com.utopian.tech.util.MybatisParameterUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    public void test() {
        List<Integer> list = new ArrayList<>();
        list.subList(0, Math.min(list.size(), 10));
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, 1);
        wrapper.last("for update");
    }

    @Override
    @Transactional
    public void updateBook() {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, 1);
        Book one = this.getOne(wrapper);
        one.setName("前端指南");
        this.updateById(one);
    }

    @Override
    @Transactional
    public void updateBookWithLock() {
        List<Long> ids = new ArrayList();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);
        ids.add(4l);
        List<Book> books = baseMapper.listForUpdate(ids);
        books.stream().forEach(i -> i.setName("JAVA思想"));
        this.updateBatchById(books);

    }

    @Override
    public List<Book> getBoolList() throws Exception {
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= 2000; i++) {
            ids.add(i);
        }
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper();
        MybatisParameterUtils.cutInParameter(wrapper, Book::getId, ids);
        return this.list(wrapper);
    }

    @Override
    public void saveBookList() {
        List<String> strings = Arrays.asList("JAVA", "C#", "C", "Python", "Go");
        List<Book> saveList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Book book = new Book();
            book.setName(strings.get((int) Math.floor((Math.random() * strings.size()))));
            book.setNumber(i);
            saveList.add(book);
        }
        this.saveBatch(saveList);
    }


    public void m1() {
        List<Book> list = new ArrayList<>();
        Map<String, Book> stringBookMap =
                list.stream().collect(Collectors.toMap(Book::getName, i -> i));
        stringBookMap.forEach((s, book) -> System.out.println(s + book));

        Optional<Integer> reduce = list.stream().map(Book::getNumber).reduce(Integer::sum);
        OptionalInt max = list.stream().mapToInt(Book::getNumber).max();
        list.stream().max((o1, o2) -> o1.getNumber() > o2.getNumber() ? o1.getNumber() : o2.getNumber());
        list.stream().anyMatch(book -> book.getId() > 10);
        list.stream().anyMatch(i -> i.getId()> 10);
        list.stream().filter(book -> book.getId() == 5);
        list.stream().filter(book -> book.getId() > 5);


        list.stream().sorted(Comparator.comparingInt(Book::getNumber));

    }
}
