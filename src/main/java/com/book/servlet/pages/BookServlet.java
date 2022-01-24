package com.book.servlet.pages;

import com.book.bean.User;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service=new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type","text/html;charset=utf-8");
        Context context=new Context();
        User user = (User)req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        context.setVariable("book_list",service.getBookList().keySet());
        context.setVariable("book_list_status",new ArrayList<>(service.getBookList().values()));
        ThymeleafUtil.process("books.html",context, resp.getWriter());
    }
}
