package cenario;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import movimentacao.MovimentBola;


public class Menu {

    private MovimentBola mov = new MovimentBola();
    public void inicial(GL2 gl){
        gl.glPushMatrix();
        gl.glColor3f(0,0,0);
        bigText(gl,0, 90,"");
        gl.glPopMatrix();
    }

    public void pontos(GL2 gl) {
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        bigText(gl,-95, 81, "PONTOS: ");
        bigText(gl, -80, 81, Integer.toString(mov.getPontos()));
    }


    public void bigText(GL2 gl, int x, int y, String phrase) {
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        GLUT glut = new GLUT();
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, phrase);

    }
    public void mediumText(GL2 gl, int x, int y, String phrase) {
        GLUT glut = new GLUT();
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, phrase);
    }

    public void smallText(GL2 gl, int x, int y, String phrase) {
        GLUT glut = new GLUT();
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_9_BY_15, phrase);
    }
}
