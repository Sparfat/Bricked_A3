package renderizacao;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import fase.Fase;
import textura.Texture;

public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    GLU glu;
    public float translate;
    private final Fase fase = new Fase();
    public float limit;


    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        GL2 gl = drawable.getGL().getGL2();

        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        gl.glEnable(GL2.GL_DEPTH_TEST);
        translate = 0.0f;
        limit = 1;

        int totalTexture = 4;
        new Texture(totalTexture);

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();


        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);

        fase.fase(gl,glut, translate);

        gl.glFlush();

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        if (height == 0)
            height = 1;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

}