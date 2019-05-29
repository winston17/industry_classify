package com.yzc.industry_classify.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname TextUtil
 * @Author lizonghuan
 * @Description 文本工具
 * @Date-Time 2019/4/18-17:16
 * @Version 1.0
 */

public class TextUtil {

    private static Pattern numPattern = Pattern.compile("\\d+(?:\\.\\d+)?");

    private static Pattern removedOrgPattern = Pattern.compile("^.{2,7}(?:公司|集团|局|办事处)");

    public static String removeOrgFromTitle(String title){
        if (title == null){
            return null;
        }
        String removedTitle = title.replace("^.{2,7}(?:公司|集团|局|办事处)", "");
        Matcher matcher = removedOrgPattern.matcher(title);
        if (matcher.find()){
            return title.replaceFirst(matcher.group(0), "");
        }else{
            return title;
        }
    }

    public static Double transDays(String days){
        if (days == null){
            return null;
        }
        Matcher matcher = numPattern.matcher(days);
        if (matcher.find()){
            if (days.contains("年")){
                return Double.parseDouble(matcher.group(0)) * 365;
            }
            if (days.contains("月")){
                return Double.parseDouble(matcher.group(0)) * 31;
            }
            if(days.contains("天")){
                return Double.parseDouble(matcher.group(0));
            }
            if(days.contains("日")){
                return Double.parseDouble(matcher.group(0));
            }
            return Double.parseDouble(matcher.group(0));
        }else{
            return null;
        }
    }

    public static void main(String[] args){
        String str1 = "中国建设集团陆军军官学院";
        System.out.println(removeOrgFromTitle(str1));
    }
}
