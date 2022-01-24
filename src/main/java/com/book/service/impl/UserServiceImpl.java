package com.book.service.impl;

import com.book.bean.User;
import com.book.dao.UserMapper;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean auth(String username, String password, HttpSession httpSession) {
        try(SqlSession session = MybatisUtil.getSession()){
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            if (user==null)   return false;
            httpSession.setAttribute("user",user);
            return true;
        }
    }
}
