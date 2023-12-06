package cenario;

import com.jogamp.opengl.GL2;

public class Lighting {
    public boolean on = true;
    public int shading = GL2.GL_SMOOTH;

    public void ambientLighting(GL2 gl){

        float ambientLighting[] = {0.5f,0.5f,0.7f,1.0f};
        float lightingPosition[] = {-50.0f,50.0f,30.0f,0.0f};

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLighting, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightingPosition, 0);
    }

    public void lightsOn(GL2 gl){

        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        gl.glEnable(GL2.GL_LIGHTING);

        gl.glEnable(GL2.GL_LIGHT0);

        gl.glShadeModel(shading);
    }
}
