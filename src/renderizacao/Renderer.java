package renderizacao;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import movimentacao.KeyBoard;

import java.awt.*;

public class Renderer {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int screenWidth = 1920;
    public static final int screenHeight = 1080;


    public static void init(){
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        GLWindow window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);

        Cena cena = new Cena();

        window.addGLEventListener(cena);

        window.addKeyListener(new KeyBoard(cena));
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });

        window.setFullscreen(true);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        init();
    }
}