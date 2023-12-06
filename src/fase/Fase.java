package fase;

import cenario.Cenario;
import cenario.Menu;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import movimentacao.MovimentBola;

public class Fase {

    private final Cenario cenario = new Cenario();
    private final Menu menu = new Menu();
    private final MovimentBola mov = new MovimentBola();
    private static int fase;
    private static boolean pause;

    private void initGame(GL2 gl) {
        menu.inicial(gl);
        cenario.menuFundo(gl);
    }

    private void primeiraFase(GL2 gl, GLUT glut, float translation) {
        if (cenario.getNumVidas() > 0 && !isPause()) {
            buildScenario(gl, glut, translation);
        } else if (cenario.getNumVidas() <= 0) {
            perdeu(gl);
        } else {
            pause(gl);
        }
        if (mov.getPontos() >= 200) {
            fase = 2;
            mov.setVelBolaY(6.5f);
            mov.setVelBolaX(5.5f);
        }

        if (mov.getBolaY() < -100f) {
            mov.setBolaX(0);
            mov.setBolaY(-80f);
            cenario.removeVida();
            mov.setVelBolaY(7f);
            mov.setVelBolaX(-6f);
        }
    }

    private void segundaFase(GL2 gl, GLUT glut, float translation) {
        if (mov.getPontos() >= 400) {
            veceu(gl);
        } else if (cenario.getNumVidas() > 0 && !isPause()) {
            buildScenario(gl, glut, translation);
        } else if (cenario.getNumVidas() <= 0) {
            perdeu(gl);
        } else {
            pause(gl);
        }

        if (mov.getBolaY() < -100f) {
            mov.setBolaX(0);
            mov.setBolaY(-80f);
            mov.setVelBolaX(5.5f);
            mov.setVelBolaY(7.5f);
            cenario.removeVida();
        }
    }


    private void pause(GL2 gl) {
        menu.inicial(gl);
        cenario.imgPause(gl);
    }

    private void perdeu(GL2 gl) {
        menu.inicial(gl);
        cenario.imgPerdeu(gl);
    }

    private void veceu(GL2 gl) {
        menu.inicial(gl);
        cenario.imgGanhou(gl);
    }

    public void reset() {
        setLevel(0);
        setPause(false);
        mov.setPontos(0);
        mov.setBolaX(0);
        mov.setBolaY(-80f);
        mov.setVelBolaX(-6f);
        mov.setVelBolaY(5f);
        cenario.setNumVidas(5);

    }

    public void fase(GL2 gl, GLUT glut, float translation) {
        switch (fase) {
            case 0:
                initGame(gl);
                break;
            case 1:
                primeiraFase(gl, glut, translation);
                break;
            case 2:
                segundaFase(gl, glut, translation);
                break;
        }

    }

    private void buildScenario(GL2 gl, GLUT glut, float translation) {
        cenario.player(gl, glut, translation);
        mov.movBola(gl, glut, translation);
        menu.pontos(gl);
        cenario.ListaVida(gl);
        cenario.imgFundo(gl);
        if(getLevel() == 1){
            cenario.listaBlocosFase1(gl, glut);
            mov.checaColisaoBlocos1();
        }
        if(getLevel() == 2){
            cenario.listaBlocosFase2(gl, glut);
            mov.checaColisaoBlocos2();
        }
    }

    public static int getLevel() {
        return fase;
    }

    public static void setLevel(int fase) {
        Fase.fase = fase;
    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        Fase.pause = pause;
    }
}
