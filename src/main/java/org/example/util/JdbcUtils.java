package org.example.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;

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

        Connection conn=null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /*
        关闭连接对象conn，放回数据库连接池
     */
    public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
