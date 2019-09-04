package com.example.alex.opengles2test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class ShapeTester {

    private ArrayList<FloatBuffer> vertexBuffer;
    private FloatBuffer vertexBuffer2;
    private ShortBuffer indexBuffer;
    private ShortBuffer indexBuffer2;
    private FloatBuffer textureBuffer;

    private float vertices[][] = new float[][]{
       new float[]{
            128f,  256f+128f, 0.0f,
            128f,  192f+128f, 0.0f,
            192f,  192f+128f, 0.0f,
            192f,  256f+128f, 0.0f
    }, new float[]{
            256f,  256f+128f, 0.0f,
            256f,  192f+128f, 0.0f,
            320f,  192f+128f, 0.0f,
            320f,  256f+128f, 0.0f
    },new float[]{
            384f,  256f+128f, 0.0f,
            384f,  192f+128f, 0.0f,
            448f,  192f+128f, 0.0f,
            448f,  256f+128f, 0.0f
    },new float[]{
            512f,  256f+128f, 0.0f,
            512f,  192f+128f, 0.0f,
            576f+64f,  192f+128f, 0.0f,
            576f+64f,  256f+128f, 0.0f
    }};

    private float vertices2[] = {
            128f,  256f, 0.0f,
            128f,  192f, 0.0f,
            192f,  192f, 0.0f,
            192f,  256f, 0.0f,
            256f,  256f, 0.0f,
            256f,  192f, 0.0f,
            320f,  192f, 0.0f,
            320f,  256f, 0.0f,
            384f,  256f, 0.0f,
            384f,  192f, 0.0f,
            448f,  192f, 0.0f,
            448f,  256f, 0.0f,
            512f,  256f, 0.0f,
            512f,  192f, 0.0f,
            576f,  192f, 0.0f,
            576f,  256f, 0.0f
    };


    private int[] textures;
    private float textureCoordinates[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    Bitmap tileset;
    Bitmap[][] textur;

    private short[] indices = new short[] {0,1,2,0,2,3};
    private short[] indices2 = new short[] {
            0,1,2,0,2,3,
            4,5,6,4,6,7,
            8,9,10,8,10,11,
            12,13,14,12,14,15};

    public ShapeTester(GL10 gl) {
        vertexBuffer = new ArrayList<FloatBuffer>();
        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        for (int i = 0; i < vertices.length; i++) {
            ByteBuffer vbb = ByteBuffer.allocateDirect(vertices[i].length * 4);
            vbb.order(ByteOrder.nativeOrder());
            vertexBuffer.add(vbb.asFloatBuffer());
            vertexBuffer.get(vertexBuffer.size()-1).put(vertices[i]);
            vertexBuffer.get(vertexBuffer.size()-1).position(0);
        }

        ByteBuffer vbb2 = ByteBuffer.allocateDirect(vertices2.length * 4);
        vbb2.order(ByteOrder.nativeOrder());
        vertexBuffer2 = vbb2.asFloatBuffer();
        vertexBuffer2.put(vertices2);
        vertexBuffer2.position(0);

        // short is 2 bytes, therefore we multiply the number if
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        ByteBuffer ibb2 = ByteBuffer.allocateDirect(indices2.length * 2);
        ibb2.order(ByteOrder.nativeOrder());
        indexBuffer2 = ibb2.asShortBuffer();
        indexBuffer2.put(indices2);
        indexBuffer2.position(0);

        textures = new int[4];

        gl.glGenTextures(4, textures, 0);
        /*
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        */

        tileset = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources() ,R.drawable.tileset);
        textur = new Bitmap[2][2];
        for (int i = 0; i < 4; i++) {

            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

            if (i==0) {
                textur[0][0] = Bitmap.createBitmap(tileset, 0, 0, 32, 32);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textur[0][0], 0);
            }
            if (i==1) {
                textur[0][1] = Bitmap.createBitmap(tileset, 32, 0, 32, 32);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textur[0][1], 0);
            }
            if (i==2) {
                textur[1][0] = Bitmap.createBitmap(tileset,0,32,32,32);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textur[1][0], 0);
            }
            if (i==3) {
                textur[1][1] = Bitmap.createBitmap(tileset,32,32,32,32);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textur[1][1], 0);
            }
        }

        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        textureBuffer = tbb.asFloatBuffer();
        textureBuffer.put(textureCoordinates);
        textureBuffer.position(0);

    }

    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // Enabled the vertices buffer for writing and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of an array of vertex
        // coordinates to use when rendering.
        // go in loop gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);



        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer2);
        gl.glColor4f(0f, 0f, 1.0f, 1.0f);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices2.length, GL10.GL_UNSIGNED_SHORT, indexBuffer2);




        gl.glEnable(GL10.GL_TEXTURE_2D);
        // Enable the texture state
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Enable Texture Alpha
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL10.GL_ALPHA_BITS);

        // Point to our buffers
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        // go in loop     gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        //gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);



        for (int i = 0; i < vertices.length; i++) {
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer.get(i));
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]);
            gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
            indexBuffer.position(0);
        }




        // Enable the color array buffer to be used during rendering.
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);





        // Point out the where the color buffer is.
        //gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_ALPHA_BITS);


        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
