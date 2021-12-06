package org.example;

import org.example.util.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilTest {

    @Test
    public void testJdbcUtil(){
        for(int i=0;i<10;i++){
            Connection conn = JdbcUtils.getConnection();
            System.out.println("conn--->"+conn);
            JdbcUtils.close(conn);
        }

    }
}
