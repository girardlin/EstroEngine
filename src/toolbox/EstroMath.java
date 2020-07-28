package toolbox;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class EstroMath
{
    public static Matrix4f createModelMatrix(Vector2f translation, Vector2f scale)
    {
        Matrix4f matrix = new Matrix4f().identity();
        matrix.translate(new Vector3f(translation, 0.0f));
        matrix.scale(new Vector3f(scale.x, scale.y, 1.0f));
        return matrix;
    }

    public static Matrix4f createModelMatrix(Vector3f translation, Vector2f scale)
    {
        Matrix4f matrix = new Matrix4f().identity();
        matrix.translate(new Vector3f(translation));
        matrix.scale(new Vector3f(scale.x, scale.y, 1.0f));
        return matrix;
    }

    public static Matrix4f createModelMatrix(Vector3f translation, Vector3f rotation, Vector3f scale)
    {
        Matrix4f matrix = new Matrix4f().identity();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rotation.x), 1.0f, 0.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.y), 0.0f, 1.0f, 0.0f);
        matrix.rotate((float) Math.toRadians(rotation.z), 0.0f, 0.0f, 1.0f);
        matrix.scale(new Vector3f(scale.x, scale.y, scale.z));
        return matrix;
    }
}
