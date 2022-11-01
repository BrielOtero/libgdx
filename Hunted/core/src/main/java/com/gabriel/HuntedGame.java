package com.gabriel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.gabriel.manager.*;
import com.gabriel.screen.EntryScreen;
import com.gabriel.screen.GameScreen;
import com.gabriel.screen.LooseScreen;
import com.gabriel.world.WorldGen;

public class HuntedGame extends Game {

  private GameScreen theGameScreen;
  private LooseScreen theLooseScreen;
  private EntryScreen theEntryScreen;

  public static boolean debug = true;
  public static boolean lights = true;
  public static boolean floodlight = true;
  public static boolean particles = true;
  public static boolean world = true;
  public static boolean physicsChests = false;
  public static boolean sounds = true;
  public static boolean zoomOut = true;
  public static boolean fps = true;

  @Override
	public void create () {
    Textures.getInstance().create();
    Sprites.getInstance().create();
    Physics.getInstance().create();
    Sounds.getInstance().create();
    WorldGen.getInstance().create();
    GameState.getInstance().create();

    GameState.getInstance().game = this;

    theGameScreen = new GameScreen();
    theLooseScreen = new LooseScreen();
    theEntryScreen = new EntryScreen();
    setToEntry();
  }

  public void setToGame() {
    setScreen(theGameScreen);
  }

  public void setToEntry() {
    setScreen(theEntryScreen);
  }

  public void setToLoose() {
    setScreen(theLooseScreen);
  }

	@Override
	public void dispose () {
    theGameScreen.dispose();
    theEntryScreen.dispose();
    theLooseScreen.dispose();
    Textures.getInstance().dispose();
    Sprites.getInstance().dispose();
    Physics.getInstance().dispose();
    Sounds.getInstance().dispose();
    WorldGen.getInstance().dispose();
    GameState.getInstance().dispose();
  }
}
