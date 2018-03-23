package com.zgh.weather.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuguohui on 2018/3/21.
 */

public class JSONObjectValueGetter {
    private static final Pattern arrayPatten = Pattern.compile("(\\w+)\\[(\\d+)\\]");
    private static final String VALUE_NULL = "[null]";
    private static final String VALUE_ERROR = "[error]";

    /**
     * @param object     需要获取的JSON对应的javabean
     * @param testFormat 占位符
     *                   <p>规则如下：
     *                   <p> 1.占位符最外层为{} </p>
     *                   <p> 2.如果要获取数组下面的某个元素则可通过 arryName[index] 方式获取
     *                   <p> 3.如果要获取多级则可通过 valueA.valueB 的方式获取
     *                   <p> 4.如果没有相关元素则显示[null]
     *                   <p> 5.如果元素出错，比如数组越界则显示[error]
     * @return
     */
    public static String getValueByFormatString(Object object, String testFormat) {
        Pattern pattern = Pattern.compile("\\{([^\\}]*)\\}");
        Matcher matcher = pattern.matcher(testFormat);
        List<ValueHolder> valueHolderList = new ArrayList<>();
        while (matcher.find()) {
            String holderStr = matcher.group();
            String filedStr = matcher.group(1);
            ValueHolder valueHolder = new ValueHolder(holderStr, getValueString(object, filedStr));
            valueHolderList.add(valueHolder);

        }
        //Pattern.LITERAL 表示要传入的要替换的内容是字符串，而不是正则表达式。
        for (ValueHolder valueHolder : valueHolderList) {
            testFormat = Pattern.compile(valueHolder.getKey(), Pattern.LITERAL).matcher(
                    testFormat).replaceFirst(Matcher.quoteReplacement(valueHolder.getValue()));
        }
        return testFormat;
    }

    private static String getValueString(Object object, String filedStr) {
        String valueStr = VALUE_NULL;
        if (object == null || filedStr == null || filedStr.trim().equals("")) {
            return valueStr;
        }
        //判断是否还有下一级
        String subFiledString = null;
        boolean haveSubFile = false;
        boolean isArray = false;
        int arrayIndex = -1;
        Object subObject;
        if (filedStr.contains(".")) {
            subFiledString = filedStr.substring(filedStr.indexOf(".") + 1);
            filedStr = filedStr.substring(0, filedStr.indexOf("."));
            haveSubFile = true;
        }
        //处理数组
        Matcher arrayMatcher = arrayPatten.matcher(filedStr);
        if (arrayMatcher.find()) {
            isArray = true;
            filedStr = arrayMatcher.group(1);
            arrayIndex = Integer.valueOf(arrayMatcher.group(2));
        }

        //获取这一级对应的对象
        try {
            Field field = object.getClass().getDeclaredField(filedStr);
            field.setAccessible(true);
            subObject = field.get(object);
            if (isArray) {
                List list = (List) subObject;
                subObject = list.get(arrayIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return VALUE_ERROR;
        }
        //如果没有下一级
        if (!haveSubFile) {
            return subObject == null ? VALUE_NULL : subObject.toString();
        }else{
            return getValueString(subObject, subFiledString);
        }

    }


    private static class ValueHolder {
        String key, value;

        public ValueHolder(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
