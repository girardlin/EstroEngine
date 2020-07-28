package gameEngine;

import sprites.Sprite;
import org.lwjgl.opengl.GL11;
import sprites.SpriteRenderer;

public class MasterRenderer
{
    private final float RED = 0.3f;
    private final float GREEN = 0.8f;
    private final float BLUE = 0.3f;

    private SpriteRenderer spriteRenderer;

    MasterRenderer(Loader loader)
    {
        this.spriteRenderer = new SpriteRenderer(loader);
    }

    public void frameBegin()
    {
        GL11.glClearColor(RED, GREEN, BLUE, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(Sprite sprite)
    {
        spriteRenderer.render(sprite);
    }

    public void cleanUp()
    {
        spriteRenderer.cleanUp();
    }
}
