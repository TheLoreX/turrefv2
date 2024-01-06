package com.example.turrefv2.action;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
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

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (view.getId() == R.id.ButtonUpperDisplay || view.getId() == R.id.ButtonLowerDisplay)
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slightpressin));
                else
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pressin));

                view.invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (view.getId() == R.id.ButtonUpperDisplay || view.getId() == R.id.ButtonLowerDisplay)
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slightpressout));
                else
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pressout));

                view.invalidate();
                break;
            }

        return false;
    }
}