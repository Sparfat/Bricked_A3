package textura;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

public class Texture {
    private com.jogamp.opengl.util.texture.Texture vetTextures[];
    private float width;
    private float height;
    private int filter;
    private int mode;
    private int wrap;
    private boolean automatic;

    public Texture(int totalTexture) {
        vetTextures = new com.jogamp.opengl.util.texture.Texture[totalTexture];
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public float getFilter() {
        return filter;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public float getMode() {
        return mode;
    }

    public void setWrap(int wrap) {
        this.wrap = wrap;
    }

    public float getWrap() {
        return wrap;
    }

    public void setAutomatic(boolean automatic){
        this.automatic = automatic;
    }
    public boolean getAutomatic(){
        return automatic;
    }

    public void createTexture(GL2 gl, String fileName, int index) {
        loadTexture(fileName, index);
        com.jogamp.opengl.util.texture.Texture tex = vetTextures[index];
        tex.bind(gl);
        tex.enable(gl);

        tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, filter);
        tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, filter);

        tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, wrap);
        tex.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, wrap);

        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_DECAL);
        if(this.automatic)
            enableAutomaticTexture(gl);
    }

    private void loadTexture(String fileName, int index) {
        com.jogamp.opengl.util.texture.Texture tex = null;

        try {
            tex = TextureIO.newTexture(new File(fileName), true);
        } catch (IOException e) {
            System.out.println("\n***************\nErro: Arquivo não encontrado "
                    + fileName + "\n***************\n");
        }

        this.width = tex.getWidth();
        this.height = tex.getHeight();

        vetTextures[index] = tex;
    }

    public void enableAutomaticTexture(GL2 gl) {
        int genMode = GL2.GL_OBJECT_LINEAR;

        float planoS[] = {1.0f, 0.0f, 0.0f, 0.0f};
        float planoT[] = {0.0f, 0.0f, 1.0f, 0.0f};

        gl.glEnable(GL2.GL_TEXTURE_GEN_S);
        gl.glEnable(GL2.GL_TEXTURE_GEN_T);

        gl.glTexGeni(GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, genMode);
        gl.glTexGeni(GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, genMode);


        gl.glTexGenfv(GL2.GL_S, GL2.GL_OBJECT_PLANE, planoS, 0);
        gl.glTexGenfv(GL2.GL_T, GL2.GL_OBJECT_PLANE, planoT, 0);
    }

    public void disableAutomaticTexture(GL2 gl) {
        gl.glDisable(GL2.GL_TEXTURE_GEN_S);
        gl.glDisable(GL2.GL_TEXTURE_GEN_T);
    }

    public void disableTexture(GL2 gl, int index) {
        com.jogamp.opengl.util.texture.Texture tex = vetTextures[index];
        tex.disable(gl);
        tex.destroy(gl);
        if(this.automatic)
            disableAutomaticTexture(gl);
    }
}
