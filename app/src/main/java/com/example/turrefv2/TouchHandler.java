package com.example.turrefv2;

import android.content.Context;
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
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.pressin));
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.pressout));
                view.invalidate();
                break;
            }
        }
        return false;
    }
}
