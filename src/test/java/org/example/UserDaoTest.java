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

    @Test
    public void queryUserByUsernameAndPasswordTest(){
        User user = userDao.queryUserByUsernameAndPassword("admin", "12345");
        System.out.println("user:"+user);
    }

    @Test
    public void saveUserTest(){
        User user = new User(null, "maskim", "12345", "maskim@163.com");
        int result = userDao.saveUser(user);
        System.out.println(result);
    }
}
