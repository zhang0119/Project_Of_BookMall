package org.example.service;

import org.example.pojo.User;

public interface UserService {
    /*
        注册用户
     */
    void registerUser(User user);

    /*
        用户登录
     */
    User login(User user);

    /*
        检查用户名是否可用
     */
    boolean existsUsername(String name);
}
