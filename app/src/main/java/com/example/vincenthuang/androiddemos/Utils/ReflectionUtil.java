package com.example.vincenthuang.androiddemos.Utils;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射的工具类
 * Created by Vincent Huang on 2017/2/2.
 */

public class ReflectionUtil {
    @SuppressWarnings("unused")
    private static final String TAG = "ReflectionUtil";
    private ReflectionUtil(){}

    /*
    * 更改静态final型数据的值
    * */
    public static boolean changeStaticFinalFiled(@NonNull Class clazz,
                                                 @NonNull String fileName,
                                                 @NonNull Object value){
        try {
            Field field = clazz.getDeclaredField(fileName);
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("accessFlags");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            if (value instanceof Boolean){
                field.setBoolean(null, (boolean)value);
            }else if(value instanceof Integer){
                field.setInt(null, (int)value);
            }else if(value instanceof Long){
                field.setLong(null, (long)value);
            }else{
                field.set(null, value);
            }
            return true;
        } catch (Exception e) {
            HLog.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
