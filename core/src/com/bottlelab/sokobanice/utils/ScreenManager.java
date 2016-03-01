package com.bottlelab.sokobanice.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.bottlelab.sokobanice.hintscreen.HintScreen;
import com.bottlelab.sokobanice.mainmenuscreen.MainMenuScreen;
import com.bottlelab.sokobanice.playscreen.PlayScreen;
import com.bottlelab.sokobanice.setlevelmenu.SetBoxCountScreen;
import com.bottlelab.sokobanice.setlevelmenu.SetLevelMenu;
import com.bottlelab.sokobanice.splash.SplashScreen;



public class ScreenManager implements Disposable{


	Game game;
	
	public ScreenManager(Game _game) {
		game = _game;
	}
	
	// разобраься когда его диспоузить надо
	public void setSplashScreen() {
		game.setScreen(new SplashScreen());
	}
	
	public void setPlayScreen(int _boxCount, int _level) {
		PlayScreen playScreen = new PlayScreen(_boxCount,_level);
		game.setScreen((Screen) playScreen);
	}
	
	public void setMainMenuScreen() {
		MainMenuScreen mainMenuScreen = new MainMenuScreen();
		game.setScreen((Screen) mainMenuScreen);
	}
	
	public void setSetBoxCountScreen() {
		SetBoxCountScreen setBoxCountScreen = new SetBoxCountScreen();
		game.setScreen((Screen) setBoxCountScreen);
	}
	
	public void setSetLevelMenu(int box) {
		SetLevelMenu setLevelMenu = new SetLevelMenu(box);
		game.setScreen((Screen) setLevelMenu);
	}
	
	public void setHintScren(int _boxCount, int _level) {
		game.setScreen(new HintScreen(_boxCount, _level));
	}
	
	

	@Override
	public void dispose() {}
	
}
