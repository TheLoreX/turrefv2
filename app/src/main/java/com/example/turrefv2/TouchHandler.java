package com.example.turrefv2;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class TouchHandler implements View.OnTouchListener {

    Context context;
    public TouchHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean isSlight = false;
        String isDisplay = String.valueOf(view).substring(String.valueOf(view).indexOf("/") + 1, String.valueOf(view).indexOf("}"));
        if (isDisplay.equals("upperDisplay") || isDisplay.equals("lowerDisplay")) isSlight = true;
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    if(!isSlight) {
                        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pressin));
                    }
                    else {
                        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slightpressin));
                    }
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if(!isSlight) {
                        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pressout));
                    }
                    else {
                        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slightpressout));
                    }
                    view.invalidate();
                    break;
                }
            }
        return false;
    }
}