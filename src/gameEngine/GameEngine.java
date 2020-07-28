package gameEngine;

import game.IGameLogic;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameEngine implements Runnable
{
    private WindowManager windowManager;

    private Loader loader;

    private InputManager inputManager;

    private MasterRenderer masterRenderer;

    private final Thread gameLoopThread;
    private IGameLogic gameLogic;

    public GameEngine(IGameLogic gameLogic)
    {
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        this.gameLogic = gameLogic;
    }

    private void init()
    {
        windowManager = new WindowManager(600, 600);

        loader = new Loader();

        inputManager = new InputManager(windowManager.getWindow());

        masterRenderer = new MasterRenderer(loader);

        gameLogic.init(loader);
    }

    private void gameLoop()
    {
        while(!glfwWindowShouldClose(windowManager.getWindow()))
        {
            gameLogic.input(inputManager, windowManager.getWindow(), windowManager.getDeltaTime());
            gameLogic.update(windowManager.getDeltaTime());

            masterRenderer.frameBegin();

            gameLogic.render(masterRenderer);

            windowManager.frameEnd();
        }
    }

    public void start()
    {
        gameLoopThread.start();
    }

    @Override
    public void run()
    {
        try
        {
            init();
            gameLoop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        cleanUp();
    }

    private void cleanUp()
    {
        gameLogic.cleanUp();

        masterRenderer.cleanUp();
        loader.cleanUp();

        windowManager.close();
    }
}
