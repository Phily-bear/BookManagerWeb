package com.book.servlet.pages;

import com.book.bean.User;
import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    BookService service;
    StudentService studentService;

    @Override
    public void init() throws ServletException {
        service=new BookServiceImpl();
        studentService=new StudentServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type","text/html;charset=utf-8");
        Context context=new Context();
        User user = (User)req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        context.setVariable("borrow_list",service.getBorrowList());
        context.setVariable("book_count",service.getBookList().size());
        context.setVariable("student_count",studentService.getStudentList().size());
        ThymeleafUtil.process("index.html",context, resp.getWriter());
    }
}
