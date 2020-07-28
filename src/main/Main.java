package main;

import game.IGameLogic;
import game.TestGame;
import gameEngine.GameEngine;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            IGameLogic game = new TestGame();
            GameEngine gameEngine = new GameEngine(game);
            gameEngine.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
