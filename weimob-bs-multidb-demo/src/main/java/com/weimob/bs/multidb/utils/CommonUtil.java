package com.weimob.bs.multidb.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adam on 2016/2/18.
 */
public class CommonUtil {

    public static String objectToStr(Object object, String def) {
        if (object == null) {
            return def;
        } else {
            return object.toString();
        }
    }

    public static Integer strToInteger(String str, Integer def) {
        if (isEmpty(str)) {
            return def;
        } else {
            return Integer.parseInt(str);
        }
    }

    public static Long strToLong(String str, Long def) {
        if (isEmpty(str)) {
            return def;
        } else {
            return Long.parseLong(str);
        }
    }

    public static Short strToShort(String str, Short def) {
        if (isEmpty(str)) {
            return def;
        } else {
            return Short.parseShort(str);
        }
    }

    public static String DateToStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    public static Date StrToDate(String str, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        date = format.parse(str);
        return date;
    }

    public static boolean isEmpty(String str) {
        if (str != null && !str.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static String toJsonString(Object obj) {
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.QuoteFieldNames, true);
        serializer.setDateFormat("yyyy-MM-dd HH:mm:ss");
        serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
        serializer.config(SerializerFeature.PrettyFormat, true);
        serializer.write(obj);
        String jsonString = out.toString();
        out.close();
        return jsonString;
    }
}
