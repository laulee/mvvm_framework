package com.framework.core.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.framework.core.R;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by laulee on 2018/4/28.
 */

public class UiManager {

    /**
     * Activity不带数据的跳转
     *
     * @param context
     * @param cls
     */
    public static void switcher(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * Activity带有数据跳转
     *
     * @param context
     * @param map
     * @param cls
     */
    public static void switcher(Context context, Map<String, Object> map, Class<?> cls) {
        Intent intent = new Intent();
        putExtraData(map,intent);
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * 添加数据
     * @param map
     * @param intent
     */
    private static void putExtraData(Map<String, Object> map,Intent intent){
        if(map != null){
            Iterator<String> keys = map.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object value = map.get(key);
                if (value instanceof Integer) {
                    intent.putExtra(key, (Integer) value);
                } else if (value instanceof String) {
                    intent.putExtra(key, (String) value);
                } else if (value instanceof Double) {
                    intent.putExtra(key, (Double) value);
                } else if (value instanceof Float) {
                    intent.putExtra(key, (Float) value);
                } else if (value instanceof Long) {
                    intent.putExtra(key, (Long) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, (Boolean) value);
                }else if(value instanceof Object){
                    intent.putExtra(key, (Serializable) value);
                }
            }
        }
    }
}
