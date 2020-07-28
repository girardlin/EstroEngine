package game;

import gameEngine.InputManager;
import gameEngine.Loader;
import gameEngine.MasterRenderer;

public interface IGameLogic
{
    void init(Loader loader);
    void input(InputManager inputManager, long windowID, float deltaTime);
    void update(float deltaTime);
    void render(MasterRenderer renderer);
    void cleanUp();
}
