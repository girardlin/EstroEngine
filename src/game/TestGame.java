package game;

import gameEngine.InputManager;
import gameEngine.Loader;
import gameEngine.MasterRenderer;
import org.joml.Vector2f;
import org.joml.Vector3f;
import sprites.Sprite;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class TestGame implements IGameLogic
{
    //instantiations
    Sprite sprite;

    @Override
    public void init(Loader loader)
    {
        sprite = loader.loadSprite("grass.png", new Vector3f(0.0f), new Vector2f(0.5f));
    }

    @Override
    public void input(InputManager inputManager, long windowID, float deltaTime)
    {
        float speedMultiplier = 2.0f;

        if (inputManager.isKeyPressed(GLFW_KEY_ESCAPE))
        {
            GLFW.glfwSetWindowShouldClose(windowID, true);
        }
        if (inputManager.isKeyPressed(GLFW_KEY_UP))
        {
            sprite.changeYPosition(speedMultiplier * deltaTime);
        }
        if (inputManager.isKeyPressed(GLFW_KEY_DOWN))
        {
            sprite.changeYPosition(-speedMultiplier * deltaTime);
        }
        if (inputManager.isKeyPressed(GLFW_KEY_LEFT))
        {
            sprite.changeXPosition(-speedMultiplier * deltaTime);
        }
        if (inputManager.isKeyPressed(GLFW_KEY_RIGHT))
        {
            sprite.changeXPosition(speedMultiplier * deltaTime);
        }
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(MasterRenderer renderer)
    {
        renderer.render(sprite);
    }

    @Override
    public void cleanUp()
    {

    }
}
