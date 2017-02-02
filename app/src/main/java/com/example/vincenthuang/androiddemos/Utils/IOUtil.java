package com.example.vincenthuang.androiddemos.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 处理一些IO的工具类
 * Created by Vincent Huang on 2017/2/2.
 */
public class IOUtil {
    @SuppressWarnings("unused")
    private static final String TAG = "IOUtil";

    /**
     * private constructor. a Good pattern
     */
    private IOUtil(){}

    /**
     * 关闭操作
     * @param closeable
     */
    public static void safeClose(@Nullable Closeable closeable){
        if(closeable == null) return;
        try {
            closeable.close();
        } catch (IOException e) {
            HLog.e(TAG, "in safeClose, and exception: ", e);
        }
    }

    /**
     * 读取流并输出字符串
     * @param inputStream  输入流
     * @return  对应的字符串,
     * @throws IOException
     */
    public static String streamToString(@NonNull InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<String> buffer = new LinkedList<>();
        try {
            while ((line = bufferedReader.readLine()) != null){
                buffer.add(line);
            }
            return TextUtils.join("\n", buffer);
        }finally {
            safeClose(bufferedReader);
        }
    }
}
