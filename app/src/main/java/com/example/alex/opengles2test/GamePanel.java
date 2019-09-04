package com.example.alex.opengles2test;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GamePanel extends GLSurfaceView {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    //private MainThread thread;

    public GamePanel(Context context) {
        super(context);

    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }
}
