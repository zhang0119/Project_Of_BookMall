package org.example.dao;

import org.example.pojo.User;

public interface UserDao {

    /*
        根据用户名查询用户信息
     */
    User queryUserByUsername(String username);

    /*
        根据用户名和密码查询用户信息
     */
    User queryUserByUsernameAndPassword(String username,String password);

    /*
        保存用户信息
     */
    int saveUser(User user);
}
