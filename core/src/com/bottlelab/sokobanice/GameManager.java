package com.bottlelab.sokobanice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.utils.AudioManager;
import com.bottlelab.sokobanice.utils.PreferenceManager;
import com.bottlelab.sokobanice.utils.ResourcesManager;
import com.bottlelab.sokobanice.utils.ScreenManager;

public class GameManager extends Game {

	public AudioManager audioManager;
	public PreferenceManager prefManager;
	public ResourcesManager resManager;
	public ScreenManager screenManager;
	public AdsController adsController;
	
	public GameManager(AdsController _adsController) {
		adsController = _adsController;
	}
	
	
	@Override
	public void create () {
		resManager = new ResourcesManager();
		audioManager = new AudioManager(resManager, this);
		prefManager = new PreferenceManager();
		screenManager = new ScreenManager(this);
		
		screenManager.setSplashScreen();
		
	}
	
	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().toString(), "dispose()");
		super.dispose();
		prefManager.dispose();
	}

}
