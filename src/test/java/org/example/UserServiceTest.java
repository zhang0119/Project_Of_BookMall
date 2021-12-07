package org.example;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    private UserService userService=new UserServiceImpl();

    @Test
    public void registerUser(){
        User user1 = new User(null,"zhang", "12345", "zhang@qq.com");
        User user2 = new User(null, "WangW", "12345", "WangW@qq.com");
        userService.registerUser(user1);
        userService.registerUser(user2);
    }

    @Test
    public void loginTest(){
        User user = new User(null, "zhang", "12345", null);
        User user2 = userService.login(user);
        System.out.println(user2);
    }

    @Test
    public void existsUsernameTest(){
        boolean user = userService.existsUsername("admin1");
        System.out.println(user?"用户存在":"用户不存在");
    }
}
