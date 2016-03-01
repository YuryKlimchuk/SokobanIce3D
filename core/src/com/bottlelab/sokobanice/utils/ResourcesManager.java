package com.bottlelab.sokobanice.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;


public class ResourcesManager implements Disposable{
	private AssetManager aManager = new AssetManager();
	
	Skin setLevelMenuSkin;
	
	
	public ResourcesManager() {
		startLoad();
	}
	
	private void startLoad() {
		aManager.load("models/scene_25.12.2015-16.20.g3dj", Model.class);
		aManager.load("models/scene1.g3dj", Model.class);
		aManager.load("models/scene2.g3dj", Model.class);
		aManager.load("models/hero.g3dj", Model.class);
		
		aManager.load("skins/main_menu/main_menu.json", Skin.class);
		aManager.load("skins/set_level_menu/set_level_menu.json", Skin.class);
		aManager.load("skins/play_menu/play_menu.json", Skin.class);
		aManager.load("skins/win_menu/win_menu.json", Skin.class);
		
		aManager.load("audio/sound1.mp3", Music.class);
		aManager.load("audio/sound2.mp3", Sound.class);
		aManager.load("audio/sound3.mp3", Sound.class);
		aManager.load("audio/click-wooden.wav", Sound.class);
		aManager.load("audio/bensound-cute.mp3", Music.class);
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
		aManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		aManager.setLoader(BitmapFont.class, ".TTF", new FreetypeFontLoader(resolver));
		
		FreeTypeFontLoaderParameter size1Params = new FreeTypeFontLoaderParameter();
		size1Params.fontFileName = "fonts/SHOWG.TTF";
		size1Params.fontParameters.size = 26;
		aManager.load("font26.TTF", BitmapFont.class, size1Params);

		FreeTypeFontLoaderParameter size2Params = new FreeTypeFontLoaderParameter();
		size2Params.fontFileName = "fonts/SHOWG.TTF";
		size2Params.fontParameters.size = 32;
		aManager.load("font32.TTF", BitmapFont.class, size2Params);
		
		
	}
	
	public Model getSceneModel(){
		return aManager.get("models/scene_25.12.2015-16.20.g3dj", Model.class);
	}
	
	public Model getScene1Model(){
		return aManager.get("models/scene1.g3dj", Model.class);
	}
	
	public Model getScene2Model(){
		return aManager.get("models/scene2.g3dj", Model.class);
	}
	
	public Model getHeroModel(){
		return aManager.get("models/hero.g3dj", Model.class);
	}
	
	
	//--------Skins-------
	public Skin getMainMenuSkin(){
		return aManager.get("skins/main_menu/main_menu.json", Skin.class);
	}
	
	public Skin getSetLevelMenuSkin(){
		return setLevelMenuSkin;
	}
	
	public Skin getPlayMenuSkin(){
		return aManager.get("skins/play_menu/play_menu.json", Skin.class);
	}
	
	public Skin getWinMenuSkin(){
		return aManager.get("skins/win_menu/win_menu.json", Skin.class);
	}
	// ------------------
	
	public Music getSound1(){
		return aManager.get("audio/sound1.mp3", Music.class);
	}
	
	public Sound getSound2(){
		return aManager.get("audio/sound2.mp3", Sound.class);
	}
	
	public Sound getSound3(){
		return aManager.get("audio/sound3.mp3", Sound.class);
	}
	
	public Sound getClickWooden(){
		return aManager.get("audio/click-wooden.wav", Sound.class);
	}
	
	public Music getBensoundCute(){
		return aManager.get("audio/bensound-cute.mp3", Music.class);
	}
	
	
	
	// ----- fonts -------
	public BitmapFont getFont26() {
		return aManager.get("font26.TTF", BitmapFont.class);
	}
	
	public BitmapFont getFont32() {
		return aManager.get("font32.TTF", BitmapFont.class);
	}
	// ------------------
	
	
	
	
	
	
	public boolean finishLoad() {
        return aManager.update();
    }

	
	public void initResources() {
		setLevelMenuSkin = aManager.get("skins/set_level_menu/set_level_menu.json", Skin.class);
		setLevelMenuSkin.remove("font32", BitmapFont.class);
		setLevelMenuSkin.remove("font26", BitmapFont.class);
		setLevelMenuSkin.get("boxBtn", TextButtonStyle.class).font = getFont26();
		setLevelMenuSkin.get("levelBtn", TextButtonStyle.class).font = getFont32();
		
		/*
		setLevelMenuSkin.add("font32", getFont32(), BitmapFont.class);
		setLevelMenuSkin.add("font26", getFont26(), BitmapFont.class);
        */
	}
	
	
	@Override
	public void dispose() {
		aManager.dispose();
	}
}
