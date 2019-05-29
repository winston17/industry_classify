package com.yzc.industry_classify.util.baseUtil;

import java.util.UUID;

public class UuidUtil {

	/**
     * 获取UUID主键
     * 
     * @return String
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }
    
}
