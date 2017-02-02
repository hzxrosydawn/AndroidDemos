package com.example.vincenthuang.androiddemos.HXML;

import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个简单的XML解析工具
 * Created by Vincent Huang on 2017/2/2.
 */

public class HXMLParser {
    private HXMLParser(){}

    private static XmlPullParser makeSureParserExist() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newPullParser();
    }
    private static XmlSerializer makeSureSerializerExist() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newSerializer();
    }

    public static <T> T fromXML(String xml, Class<T> clazz) throws XmlPullParserException, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        if(TextUtils.isEmpty(xml)) return null;
        checkObject(clazz);

        XmlPullParser parser = makeSureParserExist();
        parser.setInput(new StringReader(xml));
        int eventType = parser.getEventType();
        T result = clazz.newInstance();
        String currentTagName = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    currentTagName = parser.getName();
                    break;
                case XmlPullParser.END_TAG:
                    currentTagName = null;
                    break;
                case XmlPullParser.TEXT:
                    if(currentTagName != null){
                        Field field = getFieldRecursive(clazz, currentTagName);
                        if(field != null){
                            field.setAccessible(true);
                            field.set(result, parser.getText());
                        }
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
            }
            eventType = parser.next();
        }
        return result;
    }

    private static Field getFieldRecursive(Class clazz, String name) throws NoSuchFieldException {
        Field result = null;
        for(Class<?> c= clazz; c != null; c = c.getSuperclass()){
            result = c.getDeclaredField(name);
            if(result != null) break;
        }
        return result;
    }

    public static String toXML(Object object) throws XmlPullParserException, IOException, IllegalAccessException {
        if(object == null) return null;
        XmlSerializer serializer = makeSureSerializerExist();

        Class entityType = object.getClass();
        checkObject(entityType);
        return toXMLInternal(serializer, getClassField(entityType), object, "msg");
    }

    private static void checkObject(Class clazz){
        Annotation lXmlEntity = clazz.getAnnotation(HXMLEntity.class);
        if(lXmlEntity == null) throw new IllegalArgumentException("argument object must annotation by HXMLEntity");
    }

    private static String toXMLInternal(XmlSerializer serializer, List<Field> fieldList, Object object, String firstTag) throws IOException, IllegalAccessException {
        Map<String, String> headMap = new HashMap<>();
        Map<String, String> bodyMap = new HashMap<>();
        for(Field field : fieldList){
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            if(annotations.length == 0){
                // // TODO: 2016/5/27 做一些处理
                continue;
            }
            if(annotations[0] instanceof Head){
                headMap.put(field.getName(), (String) field.get(object));
            }else if(annotations[0] instanceof Body){
                bodyMap.put(field.getName(), (String)field.get(object));
            }else{
                //// TODO: 2016/5/27 处理
            }
        }
        Writer writer = new StringWriter();
        serializer.setOutput(writer);
        serializer.startDocument("utf-8", true);
        serializer.startTag(null, firstTag);

        serializeMap(serializer, headMap, "head");
        serializeMap(serializer, bodyMap, "body");

        serializer.endTag(null, firstTag);
        serializer.endDocument();
        return writer.toString();
    }

    private static void serializeMap(XmlSerializer serializer, Map<String, String>map, String tagName) throws IOException {
        if(map.size() == 0) return;
        serializer.startTag(null, tagName);
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(TextUtils.isEmpty(entry.getValue())) continue;
            serializer.startTag(null, entry.getKey());
            serializer.text(entry.getValue());
            serializer.endTag(null, entry.getKey());
        }
        serializer.endTag(null, tagName);
    }

    private static List<Field> getClassField(Class clazz){
        List<Field> result = new ArrayList<>();
        Class currentClass = clazz;
        do{
            result.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }while (currentClass != null);
        return result;
    }
}
