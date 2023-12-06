package movimentacao;

import cenario.Cenario;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import fase.Fase;

public class MovimentBola {

    private static float bolaX = 0.0f;
    private static float bolaY = -80f;
    private static float velBolaX = -6f;
    private static float velBolaY = 7f;
    private static int score;
    private final Cenario cenario = new Cenario();


    public void movBola(GL2 gl, GLUT glut, float translate) {
        gl.glPushMatrix();
        gl.glTranslatef(bolaX, bolaY, 0);
        cenario.bolinha(gl, glut);
        gl.glPopMatrix();

        bolaX += velBolaX;
        bolaY += velBolaY;

        if (bolaX < -90f || bolaX > 90f) {
            velBolaX = velBolaX * -1;
        }

        if (bolaY > 90f) {
            velBolaY = velBolaY * -1;
        }
        checaColisao(translate);
    }

    private void checaColisao(float translate) {
        if (bolaY <= -85 && bolaX >= translate - 15) {
            velBolaY = -velBolaY;
            ganharPontos();
        }else if (bolaY <= -85 && bolaX >= translate + 15){
            velBolaY = -velBolaY;
            velBolaX = -velBolaX;
            ganharPontos();
        }

        if (bolaX <= -200 || bolaX >= 200) {
            velBolaY = -Math.abs(velBolaY);
        }
    }

    private void ganharPontos() {
        if(Fase.getLevel() == 1){
            score += 40;
        }else if(Fase.getLevel() == 2){
            score += 20;
        }
    }

    public void checaColisaoBlocos2(){
        float obstacleWidth = 30;
        float obstacleHeight = 20;
        float obstacleX1 = 30f;
        float obstacleY1 = 40;

        if (checaColisaoBolaBlocos(bolaX, bolaY, obstacleX1, obstacleY1, obstacleWidth, obstacleHeight)) {
            velBolaY = -velBolaY;
        }

        float obstacleX2 = -30;
        float obstacleY2 = 40;

        if (checaColisaoBolaBlocos(bolaX, bolaY, obstacleX2, obstacleY2, obstacleWidth, obstacleHeight)) {
            velBolaY = -velBolaY;
        }


    }

    public void checaColisaoBlocos1(){
        float obstacleWidth = 30.0f;
        float obstacleHeight = 20;
        float obstacleX = 0;
        float obstacleY = 40;

        if (checaColisaoBolaBlocos(bolaX, bolaY, obstacleX, obstacleY, obstacleWidth, obstacleHeight)) {
            velBolaY = -velBolaY;
        }

    }

    private boolean checaColisaoBolaBlocos(float bolaX, float bolaY, float obstacleX, float obstacleY, float obstacleWidth, float obstacleHeight) {
        float obstacleLeft = obstacleX - obstacleWidth / 2;
        float obstacleRight = obstacleX + obstacleWidth / 2;
        float obstacleTop = obstacleY + obstacleHeight / 2;
        float obstacleBottom = obstacleY - obstacleHeight / 2;

        return bolaX >= obstacleLeft && bolaX <= obstacleRight && bolaY >= obstacleBottom && bolaY <= obstacleTop;
    }


    public int getPontos() {
        return score;
    }

    public void setPontos(int score) {
        this.score = score;
    }

    public void setVelBolaX(float velBolaX) {
        this.velBolaX = velBolaX;
    }

    public void setVelBolaY(float velBolaY) {
        this.velBolaY = velBolaY;
    }
    public float getVelBolaX() {
        return velBolaX;
    }

    public float getBallSpeedY() {
        return velBolaY;
    }

    public float getBolaY() {
        return bolaY;
    }
    public void setBolaY(float bolaY) {
        this.bolaY = bolaY;
    }

    public float getBolaX() {
        return bolaX;
    }

    public void setBolaX(float bolaX) {
        this.bolaX = bolaX;
    }
}
