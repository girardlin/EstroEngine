package gameEngine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowManager
{
    private long window;
    private int windowWidth;
    private int windowHeight;

    private float deltaTime = 0.0f;
    private float currentFrame = 0.0f;
    private float lastFrame = 0.0f;

    WindowManager(int width, int height)
    {
        windowWidth = width;
        windowHeight = height;

        //initial setup, error callback creation
        System.out.println("EstroEngine - LWJGL v." + Version.getVersion());
        GLFWErrorCallback.createPrint(System.err).set();

        //glfw initialization
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        //window creation
        window = glfwCreateWindow(width, height, "EstroEngine - LWJGL v." + Version.getVersion(), NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        //resize callback
        glfwSetFramebufferSizeCallback(window, (window, w_width, w_height) -> {
            windowWidth = w_width;
            windowHeight = w_height;

            GL11.glViewport(0, 0, w_width, w_height);
        });

        GL.createCapabilities();
    }

    public void frameEnd() //update function
    {
        currentFrame = (float) glfwGetTime();
        deltaTime = currentFrame - lastFrame;
        lastFrame = currentFrame;

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void close()
    {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public long getWindow() { return window; }
    public int getScreenWidth() { return windowWidth; }
    public int getScreenHeight() { return windowHeight; }
    public float getScreenAspectRatio() { return (float)( windowWidth / windowHeight); }
    public float getDeltaTime() { return deltaTime; }
}