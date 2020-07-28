package sprites;

import gameEngine.Loader;
import models.Mesh;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import toolbox.EstroMath;

import java.util.List;

public class SpriteRenderer
{
    private final Mesh quad;
    private SpriteShader shader;

    public SpriteRenderer(Loader loader)
    {
        float[] quadData = { -1.0f,  1.0f,  0.0f,
                             -1.0f, -1.0f,  0.0f,
                              1.0f, -1.0f,  0.0f,
                             -1.0f,  1.0f,  0.0f,
                              1.0f,  1.0f,  0.0f,
                              1.0f, -1.0f,  0.0f };

        this.quad = loader.loadToVAO(quadData);
        this.shader = new SpriteShader();
    }

    public void render(List<Sprite> sprites)
    {
        shader.bind();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);

        //enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        for(Sprite sprite:sprites)
        {
            prepareSprite(sprite);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }

        //turn off blending and re-enable depth testing
        GL11.glDisable(GL11.GL_BLEND);

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    public void render(Sprite sprite)
    {
        shader.bind();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);

        //enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        prepareSprite(sprite);
        GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());

        //turn off blending and re-enable depth testing
        GL11.glDisable(GL11.GL_BLEND);

        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    private void prepareSprite(Sprite sprite)
    {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, sprite.getSpriteTexture().getTextureID());

        shader.setUniformMat4f("u_ModelMatrix", sprite.getModelMatrix());
        shader.setUniform1f("u_NumberOfRows", sprite.getSpriteTexture().getNumberOfRows());
        shader.setUniform2f("u_Offset", sprite.getTextureXOffset(), sprite.getTextureYOffset());
    }

    public void cleanUp()
    {
        shader.cleanUp();
    }
}
