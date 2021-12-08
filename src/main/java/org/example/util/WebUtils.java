package org.example.util;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class WebUtils {

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
