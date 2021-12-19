package org.example.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static{
        try {
            Properties properties = new Properties();

            //读取 jdbc.properties 属性配置文件
            InputStream inputStream =
                    JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            //从流中加载数据
            properties.load(inputStream);

            //创建 数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        获取数据库中的连接对象conn
     */
    public static Connection getConnection(){

        /*这个地方不能这么写了,我们把创建出来的conn交给ThreadLocal管理了，现在从它那里拿conn连接对象*/
        //Connection conn=null;
        Connection conn = threadLocal.get();

        if(conn == null){
            try {
                //如果threadLocal没有连接对象，那我们就从数据库连接池中获取，并把它保存到threadLocal中
                conn = dataSource.getConnection();
                //供后面的jdbc操作使用
                threadLocal.set(conn);
                //设置为手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 前面我们把自动提交关闭了，这里我们要手动关闭
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = threadLocal.get();

        if(conn!=null){
            try {
                //提交事务
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                //关闭连接资源
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。(因为Tomcat服务器底层使用过了线程池技术)
        threadLocal.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection conn = threadLocal.get();
        //如果连接对象不为空，说明之前使用过这个连接，操作过数据库
        if(conn!=null){
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove()操作，否则就会报错
        threadLocal.remove();
    }

    /*
        关闭连接对象conn，放回数据库连接池
     */
    /*public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
