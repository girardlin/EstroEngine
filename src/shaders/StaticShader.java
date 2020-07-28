package shaders;

public class StaticShader extends ShaderProgram
{
    private static final String VERTEX_FILE = "src/shaders/Vertex.shader";
    private static final String FRAGMENT_FILE = "src/shaders/Fragment.shader";

    public StaticShader()
    {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttribute()
    {
        super.bindAttribute(0, "v_Position");
    }
}
