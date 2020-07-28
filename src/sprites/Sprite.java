package sprites;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import toolbox.EstroMath;

public class Sprite
{
    private Vector3f position;
    private Vector2f scale;

    private Matrix4f modelMatrix;

    private SpriteTexture spriteTexture;
    private int textureIndex = 0;

    public Sprite(SpriteTexture spriteTexture, Vector3f position, Vector2f scale)
    {
        this.spriteTexture = spriteTexture;
        this.position = position;
        this.scale = scale;
        updateModelMatrix();
    }

    public Sprite(SpriteTexture spriteTexture, Vector3f position)
    {
        this.spriteTexture = spriteTexture;
        this.position = position;
        this.scale = new Vector2f(0.0f);
        updateModelMatrix();
    }

    public Sprite(SpriteTexture spriteTexture, int index, Vector3f position, Vector2f scale)
    {
        this(spriteTexture, position, scale);
        this.textureIndex = index;
        updateModelMatrix();
    }

    public Sprite(SpriteTexture spriteTexture, int index, Vector3f position)
    {
        this(spriteTexture, position);
        this.textureIndex = index;
        updateModelMatrix();
    }

    private void updateModelMatrix()
    {
        modelMatrix = EstroMath.createModelMatrix(position, scale);
    }

    public void changePosition(Vector3f delta)
    {
        position.add(delta);
        updateModelMatrix();
    }

    public void changeXPosition(float delta)
    {
        position.x += delta;
        updateModelMatrix();
    }

    public void changeYPosition(float delta)
    {
        position.y += delta;
        updateModelMatrix();
    }

    public void setPosition(Vector3f newPosition)
    {
        position = newPosition;
        updateModelMatrix();
    }

    public float getTextureXOffset()
    {
        int row = textureIndex % spriteTexture.getNumberOfRows();
        return (float) row / (float) spriteTexture.getNumberOfRows();
    }

    public float getTextureYOffset()
    {
        int column = textureIndex / spriteTexture.getNumberOfRows();
        return (float) column / (float) spriteTexture.getNumberOfRows();
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public Vector2f getScale()
    {
        return scale;
    }

    public Matrix4f getModelMatrix()
    {
        return modelMatrix;
    }

    public SpriteTexture getSpriteTexture()
    {
        return spriteTexture;
    }
}
