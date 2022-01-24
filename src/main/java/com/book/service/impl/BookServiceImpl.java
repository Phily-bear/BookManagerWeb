package com.book.service.impl;

import com.book.bean.Book;
import com.book.bean.Borrow;
import com.book.dao.BookMapper;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowList() {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.returnBook(id);
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set=new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookList()
                    .stream()
                    .filter(book -> !set.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.addBorrow(sid,bid);
        }
    }

    @Override
    public Map<Book,Boolean> getBookList() {
        Set<Integer> set=new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try(SqlSession session = MybatisUtil.getSession()){
            Map<Book,Boolean> map=new LinkedHashMap<>();
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.getBookList().forEach(book -> {
                map.put(book,set.contains(book.getBid()));
            });
            return map;
        }
    }

    @Override
    public void deleteBook(int bid) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBook(String title, String desc, double price) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }
}
