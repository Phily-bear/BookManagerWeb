package com.book.servlet.manager;

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

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {

    BookService service;
    StudentService studentService;
    @Override
    public void init() throws ServletException {
        service=new BookServiceImpl();
        studentService=new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        resp.setHeader("Content-type","text/html;charset=utf-8");
        context.setVariable("book_list",service.getActiveBookList());
        context.setVariable("student_list",studentService.getStudentList());
        ThymeleafUtil.process("add-borrow.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int student =Integer.parseInt(req.getParameter("student")) ;
        int book =Integer.parseInt(req.getParameter("book")) ;
        service.addBorrow(student,book);
        resp.sendRedirect("index");
    }
}
