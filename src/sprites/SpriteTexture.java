package sprites;

public class SpriteTexture
{
    private int textureID;
    private int numberOfRows = 1;

    public SpriteTexture(int textureID)
    {
        this.textureID = textureID;
    }

    public SpriteTexture(int textureID, int numberOfRows)
    {
        this.textureID = textureID;
        this.numberOfRows = numberOfRows;
    }

    public int getTextureID()
    {
        return textureID;
    }

    public int getNumberOfRows()
    {
        return numberOfRows;
    }
}
