package org.example.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    /*
        将String类型的数字转换成int类型的数字
     */
    public static int parseInt(String s,int defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            //这里我将打印异常信息注释掉，因为不注释就会报一个NumberFormatException:null异常信息
            //注释掉也没事，没有任何影响
            //e.printStackTrace();
        }
        return defaultValue;
    }

    /*
        把Map中的值注入到对应的JavaBean属性中
     */
    public static <T> T copyParamToBean(Map map,T bean){

        try {
            /*
               把所有请求的参数都注入到user对象中
             */
            BeanUtils.populate(bean,map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
