package com.bottlelab.sokobanice.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Disposable;

public class PreferenceManager implements Disposable{

	Preferences prefs;
	
	public boolean
    	SOUND_ENABLE,
    	MUSIC_ENABLE;
	
	public int 
		MAX_BOX_COUNT_ENABLE,
		MAX_LEVEL_2BOX_ENABLE,
		MAX_LEVEL_3BOX_ENABLE,
		MAX_LEVEL_4BOX_ENABLE;
	
	public PreferenceManager() {
		prefs = Gdx.app.getPreferences("PREFS_SOKOBAN_ICE_3D_V1");

        SOUND_ENABLE = prefs.getBoolean(Constants.SOUND_ENABLE, true);
        MUSIC_ENABLE = prefs.getBoolean(Constants.MUSIC_ENABLE, true);
        
        MAX_BOX_COUNT_ENABLE = prefs.getInteger(Constants.MAX_BOX_COUNT_ENABLE, 2);
        
        MAX_LEVEL_2BOX_ENABLE = prefs.getInteger(Constants.MAX_LEVEL_2BOX_ENABLE, 30);
        MAX_LEVEL_3BOX_ENABLE = prefs.getInteger(Constants.MAX_LEVEL_3BOX_ENABLE, 1);
        MAX_LEVEL_4BOX_ENABLE = prefs.getInteger(Constants.MAX_LEVEL_4BOX_ENABLE, 1);
	}
	
	@Override
    public void dispose() {
        Gdx.app.log(this.getClass().toString(), "dispose()");
        
        prefs.putBoolean(Constants.SOUND_ENABLE, SOUND_ENABLE);
        prefs.putBoolean(Constants.MUSIC_ENABLE, MUSIC_ENABLE);
        
        prefs.putInteger(Constants.MAX_BOX_COUNT_ENABLE, MAX_BOX_COUNT_ENABLE);
        
        prefs.putInteger(Constants.MAX_LEVEL_2BOX_ENABLE, MAX_LEVEL_2BOX_ENABLE);
        prefs.putInteger(Constants.MAX_LEVEL_3BOX_ENABLE, MAX_LEVEL_3BOX_ENABLE);
        prefs.putInteger(Constants.MAX_LEVEL_4BOX_ENABLE, MAX_LEVEL_4BOX_ENABLE);

        prefs.flush();

    }
	
}
