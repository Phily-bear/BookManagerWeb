package com.book.service;

import com.book.bean.Book;
import com.book.bean.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();
    void returnBook(String id);
    List<Book> getActiveBookList();
    void addBorrow(int sid,int bid);
    Map<Book,Boolean> getBookList();
    void deleteBook(int bid);
    void addBook(String title,String desc,double price);
}
