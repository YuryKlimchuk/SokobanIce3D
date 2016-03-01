package com.bottlelab.sokobanice.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.bottlelab.sokobanice.GameManager;

public class AudioManager implements Disposable{
	
	private ResourcesManager resManager;
	private GameManager gameManager;
	private Music sound1, bensoundCute;
	private Sound clickWooden;

	public AudioManager(ResourcesManager _resManager, GameManager _gameManager) {
		resManager = _resManager;
		gameManager = _gameManager;
	}
	
	public void playSound1() {
		Gdx.app.log(this.getClass().toString(), "playSound1()");
		if(!gameManager.prefManager.MUSIC_ENABLE) return;
    	if(sound1 == null) sound1 = resManager.getSound1();
    	sound1.play();
    	sound1.setLooping(true);
	}
	
	public void playSound2() { 
		if(!gameManager.prefManager.SOUND_ENABLE) return;
		if(clickWooden == null) clickWooden = resManager.getClickWooden();
		clickWooden.play();
	}
	

	
	public void stopSound1(){
    	if(sound1 != null && sound1.isPlaying()) sound1.stop();
    }

	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().toString(), "dispose()");
		
	}
	
	
	
	public void playBensoundCute() {
		if(!gameManager.prefManager.MUSIC_ENABLE) return;
    	if(bensoundCute == null) bensoundCute = resManager.getBensoundCute();
    	bensoundCute.play();
    	bensoundCute.setLooping(true);
	}
	
	public void stopBensoundCute(){
    	if(bensoundCute != null && bensoundCute.isPlaying()) bensoundCute.stop();
    }

}
