package movimentacao;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import fase.Fase;
import renderizacao.Cena;

public class KeyBoard implements KeyListener {

    private final Cena cena;

    private final Fase fase = new Fase();

    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            System.exit(0);

        if (e.getKeyCode() == KeyEvent.VK_D && cena.translate < 90)
            cena.translate += 10;

        if (e.getKeyCode() == KeyEvent.VK_A && cena.translate > -90)
            cena.translate -= 10;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && cena.translate < 90)
            cena.translate += 10;

        if (e.getKeyCode() == KeyEvent.VK_LEFT && cena.translate > -90)
            cena.translate -= 10;

        if (e.getKeyCode() == KeyEvent.VK_P) {
            fase.setPause(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            fase.setPause(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fase.setLevel(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            fase.setLevel(0);
            fase.reset();
        }

    }
}
