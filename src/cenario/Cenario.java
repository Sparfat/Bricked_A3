package cenario;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import textura.Texture;

import static com.jogamp.opengl.GL.GL_FRONT_AND_BACK;
import static com.jogamp.opengl.GL2GL3.GL_FILL;
import static com.jogamp.opengl.math.FloatUtil.TWO_PI;

public class Cenario {
    private final Lighting lighting = new Lighting();
    public float limit = 1;
    private Texture texture = new Texture(5);
    public static final String FACE1 = "src/image/menu.png";
    public static final String FACE2 = "src/image/fundo.png";
    public static final String FACE3 = "src/image/perdeu.png";
    public static final String FACE4 = "src/image/pause.png";
    public static final String FACE5 = "src/image/ganhou.png";
    private static int numVida = 5;
    public float[][] corBlocos = {
            {1, 0.95f, 0.5f},
            {1, 0.95f, 0.5f},
            {1, 0.95f, 0.5f}
    };


    public void player(GL2 gl, GLUT glut, float translation) {
        gl.glPushMatrix();
        gl.glTranslatef(translation, 0, 0);
        gl.glColor3f(0, 0, 0.8f);
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        gl.glTranslatef(0, -92, 0);
        gl.glScalef(0.5f, 0.15f, 1);
        glut.glutSolidCube(50);
        gl.glPopMatrix();
    }

    public void bolinha(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(0.87f, 0.9f, 1);
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        glut.glutSolidSphere(4, 20, 20);

        if (lighting.on) {
            lighting.ambientLighting(gl);
            lighting.lightsOn(gl);
        }
        gl.glPopMatrix();
    }

    public void vidas(GL2 gl) {
        double vidaWidth = 200.0; // Largura do player
        double vidaHeight = 100.0; // Altura do player
        gl.glPushMatrix();
        gl.glTranslatef(-95, 92, 0);
        gl.glScalef(0.02F, 0.027F, 0);
        gl.glColor3f(0, 1, 0);
        gl.glBegin(GL2.GL_QUADS);
        for (double a = 0; a < TWO_PI; a += 0.01F) {
            gl.glVertex2d(-vidaWidth / 2, -vidaHeight / 2);
            gl.glVertex2d(vidaWidth / 2, -vidaHeight / 2);
            gl.glVertex2d(vidaWidth / 2, vidaHeight / 2);
            gl.glVertex2d(-vidaWidth / 2, vidaHeight / 2);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void ListaVida(GL2 gl) {
        for (int i = 0; i < numVida; i++) {
            gl.glPushMatrix();
            gl.glTranslatef(i * 7, 0, 0);
            vidas(gl);
            gl.glPopMatrix();
        }
    }

    public void removeVida() {
        if (numVida > 0) {
            numVida--;
        }
    }

    public void desenhaBlocos2(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        gl.glTranslatef(-33.3f, 30, 0);
        gl.glScalef(0.6f, 0.14f, 1);
        glut.glutSolidCube(50);
        gl.glPopMatrix();

    }

    public void desenhaBlocos1(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        gl.glTranslatef(0, 30, 0);
        gl.glScalef(0.6f, 0.14f, 1);
        glut.glutSolidCube(50);
        gl.glPopMatrix();

    }

    public void listaBlocosFase2(GL2 gl, GLUT glut) {
        for (int i = 0; i < 2; i++) {
                gl.glPushMatrix();
                gl.glColor3f(corBlocos[i][0], corBlocos[i][1], corBlocos[i][2]);
                gl.glTranslatef(i * 66f, 10.6f, 0);
                desenhaBlocos2(gl, glut);
                gl.glPopMatrix();
        }
    }

    public void listaBlocosFase1(GL2 gl, GLUT glut) {
        for (int i = 0; i < 1; i++) {
            gl.glPushMatrix();
            gl.glColor3f(corBlocos[i][0], corBlocos[i][1], corBlocos[i][2]);
            gl.glTranslatef(i , 10.6f, 0);
            desenhaBlocos1(gl, glut);
            gl.glPopMatrix();
        }
    }

    public void menuFundo(GL2 gl) {
        gl.glPushMatrix();
        texture.setAutomatic(false);

        texture.createTexture(gl, FACE1, 0);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100.0f, -100.0f, -1);
        gl.glTexCoord2f(0.0f, limit);
        gl.glVertex3f(-100.0f, 100.0f, -1);
        gl.glTexCoord2f(limit, limit);
        gl.glVertex3f(100.0f, 100.0f, -1);
        gl.glTexCoord2f(limit, 0.0f);
        gl.glVertex3f(100.0f, -100.0f, -1);
        gl.glEnd();

        texture.disableTexture(gl, 0);
        gl.glPopMatrix();
    }

    public void imgFundo(GL2 gl) {
        gl.glPushMatrix();
        texture.setAutomatic(false);

        texture.createTexture(gl, FACE2, 1);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100.0f, -100.0f, -1);
        gl.glTexCoord2f(0.0f, limit);
        gl.glVertex3f(-100.0f, 100.0f, -1);
        gl.glTexCoord2f(limit, limit);
        gl.glVertex3f(100.0f, 100.0f, -1);
        gl.glTexCoord2f(limit, 0.0f);
        gl.glVertex3f(100.0f, -100.0f, -1);
        gl.glEnd();

        texture.disableTexture(gl, 1);
        gl.glPopMatrix();
    }

    public void imgPerdeu(GL2 gl) {
        gl.glPushMatrix();
        texture.setAutomatic(false);

        texture.createTexture(gl, FACE3, 2);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100.0f, -100.0f, 1);
        gl.glTexCoord2f(0.0f, limit);
        gl.glVertex3f(-100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, limit);
        gl.glVertex3f(100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, 0.0f);
        gl.glVertex3f(100.0f, -100.0f, 1);
        gl.glEnd();

        texture.disableTexture(gl, 2);
        gl.glPopMatrix();
    }

    public void imgPause(GL2 gl) {
        gl.glPushMatrix();
        texture.setAutomatic(false);

        texture.createTexture(gl, FACE4, 3);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100.0f, -100.0f, 1);
        gl.glTexCoord2f(0.0f, limit);
        gl.glVertex3f(-100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, limit);
        gl.glVertex3f(100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, 0.0f);
        gl.glVertex3f(100.0f, -100.0f, 1);
        gl.glEnd();

        texture.disableTexture(gl, 3);
        gl.glPopMatrix();
    }

    public void imgGanhou(GL2 gl) {
        gl.glPushMatrix();
        texture.setAutomatic(false);

        texture.createTexture(gl, FACE5, 4);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100.0f, -100.0f, 1);
        gl.glTexCoord2f(0.0f, limit);
        gl.glVertex3f(-100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, limit);
        gl.glVertex3f(100.0f, 100.0f, 1);
        gl.glTexCoord2f(limit, 0.0f);
        gl.glVertex3f(100.0f, -100.0f, 1);
        gl.glEnd();

        texture.disableTexture(gl, 4);
        gl.glPopMatrix();
    }

    public int getNumVidas() {
        return numVida;
    }

    public void setNumVidas(int numVida) {
        this.numVida = numVida;
    }
}

