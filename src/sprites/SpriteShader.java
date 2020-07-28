package sprites;

import shaders.ShaderProgram;

public class SpriteShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/sprites/SpriteVertex.shader";
    private static final String FRAGMENT_FILE = "src/sprites/SpriteFragment.shader";

    public SpriteShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttribute()
    {
        super.bindAttribute(0, "v_Position");
    }
}
