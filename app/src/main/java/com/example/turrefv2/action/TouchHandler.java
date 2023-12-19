package com.example.turrefv2.action;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.turrefv2.R;

public class TouchHandler extends Activity implements View.OnTouchListener {

    Context context;
    public TouchHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean isSlight = false;
        String isDisplay = String.valueOf(view).substring(String.valueOf(view).indexOf("/") + 1, String.valueOf(view).indexOf("}"));
        if (isDisplay.equals("ButtonUpperDisplay") || isDisplay.equals("ButtonLowerDisplay")) {isSlight = true;}
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
                case MotionEvent.ACTION_CANCEL:
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