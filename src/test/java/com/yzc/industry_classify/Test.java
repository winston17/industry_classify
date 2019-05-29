package com.yzc.industry_classify;

/**
 * @Classname Test
 * @Author lizonghuan
 * @Description sss
 * @Date-Time 2019/5/24-9:18
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args){
        String a = "271106-行李装卸机械";
        System.out.println(a.replaceAll("\\d+\\-", ""));
    }

}
