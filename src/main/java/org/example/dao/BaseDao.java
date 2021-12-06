package org.example.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.example.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao {

    //使用DbUtils 操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行: insert \ update \  delete 语句
     * @param sql 传入的sql执行语句
     * @param args sql中对应的参数值
     * @return 如果返回-1，表示执行失败<br/>返回其他表示影响的行数
     */
    public int update(String sql,Object...args){

        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询 --- 返回一个 javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return 返回一个javaBean对象
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return 返回多个javaBean的sql语句
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args ){
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的 sql语句
     * @param sql 将要执行的Sql语句
     * @param args sql对应的参数值
     * @return 返回一行一列的 sql语句
     */
    public Object queryForSingleValue(String sql,Object...args){

        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn);
        }
        return null;
    }

}
