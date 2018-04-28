package com.framework.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 防止短时间二次点击
 * Created by laulee on 2018/4/28.
 */

public class NoDoubleClickButton extends Button {
    //上一次点击的时间
    private long lastTime;

    public NoDoubleClickButton(Context context) {
        super(context);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTime < 1500) {
                    return true;
                }
                lastTime = currentTime;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
