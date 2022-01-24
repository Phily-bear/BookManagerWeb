package com.book.service.impl;

import com.book.bean.Student;
import com.book.dao.StudentMapper;
import com.book.service.StudentService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> getStudentList() {
        try (SqlSession session = MybatisUtil.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            return mapper.getStudentList();
        }
    }
}
