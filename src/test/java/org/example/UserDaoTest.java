package org.example;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.pojo.User;
import org.junit.Test;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsernameTest(){
        User user = userDao.queryUserByUsername("admin");
        System.out.println("[user]:"+user);
    }
}
