package org.example.dao.impl;

import org.example.dao.BaseDao;
import org.example.dao.UserDao;
import org.example.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public int saveUser(User user) {
        return 0;
    }
}
