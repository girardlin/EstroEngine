package gameEngine;

import org.lwjgl.glfw.GLFW;

public class InputManager
{
    private final long windowID;

    InputManager(long windowID)
    {
        this.windowID = windowID;
    }

    public boolean isKeyPressed(int key)
    {
        return GLFW.glfwGetKey(windowID, key) == GLFW.GLFW_PRESS;
    }
}
