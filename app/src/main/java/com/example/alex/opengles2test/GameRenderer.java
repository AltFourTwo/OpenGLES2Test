package com.example.alex.opengles2test;

import android.graphics.drawable.shapes.Shape;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer implements GLSurfaceView.Renderer {

    Triangle triangle;
    Square square;
    ShapeTester st;

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.8f,0.0f,0.0f,1.0f);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);



        triangle.draw(gl);
        square.draw(gl);
        st.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // Redo the Viewport, making it fullscreen.
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluOrtho2D(gl , 0, width, 0, height);
        GLU.gluPerspective(gl, 45.0f, 16f/9f, -1f, 1f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();





        triangle = new Triangle();
        square = new Square();
        st = new ShapeTester(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        triangle = new Triangle();
        square = new Square();
        st = new ShapeTester(gl);
    }

}
