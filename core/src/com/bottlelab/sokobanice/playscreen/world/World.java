package com.bottlelab.sokobanice.playscreen.world;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Graph;
import com.bottlelab.sokobanice.playscreen.controller.Tween.ModelTweenAccessor;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.WorldData;
import com.bottlelab.sokobanice.playscreen.world.models.box.Box;
import com.bottlelab.sokobanice.playscreen.world.models.box.BoxState;
import com.bottlelab.sokobanice.playscreen.world.models.clickers.BoxClicker;
import com.bottlelab.sokobanice.playscreen.world.models.clickers.IceClicker;
import com.bottlelab.sokobanice.playscreen.world.models.hero.Hero;

public class World {
	
	public int boxCount, level;
	public WorldData worldData;
	
	public Array<BaseModel> ice;
	public Array<Box> box;
	public BaseModel iceberg;
	public Hero hero;
	public TweenManager tweenManager;
	public SaverPositions saver;
	
	private int[][] defaultMap;
	
	public World(int _boxCount, int _level) {
		
		Gdx.app.log(this.getClass().toString(), "boxCount = " + _boxCount + "; _level = " + _level);
		
		boxCount = _boxCount;
		level = _level;
		
		Tween.registerAccessor(BaseModel.class, new ModelTweenAccessor());
        tweenManager = new TweenManager();
		
		Json json = new Json();
		String path = "levels/" + boxCount + "box/" + level + ".json"; 
		Gdx.app.log(null, path);
		worldData = json.fromJson(WorldData.class, Gdx.files.internal(path));
		
		WorldBuilder worldBuilder = new WorldBuilder(worldData, this, tweenManager);
		ice = worldBuilder.buildIce();
		ice.addAll(worldBuilder.buildIceMark());
		hero = worldBuilder.buildHero();
		box = worldBuilder.buildBox();
		//iceberg = worldBuilder.buildIceberg();
		
		
		
		setClickers();
		createDefaultMap();
		
		saver = new SaverPositions(this);
		
		
	}
	
	
	private void setClickers(){
		
		IceClicker iceClicker = new IceClicker(this);
		for (BaseModel model : ice) model.addClicker(iceClicker);
		
		BoxClicker boxClicker = new BoxClicker(this);
		for (BaseModel model : box) model.addClicker(boxClicker);
			
		
	}
	
	private void createDefaultMap(){
		// Gdx.app.log(this.getClass().toString(), "createDefaultMap()");
		defaultMap = new int[(int) worldData.maxX][(int) worldData.maxZ];
		// заполняем карту всеми не проходимыми клетками
		for (int i = 0; i < (int) worldData.maxX; i++) 
	    	for (int j = 0; j < (int) worldData.maxZ; j++) defaultMap[i][j] = 0;
	}
			
	public int[][] createMap() {
	        //Gdx.app.log(this.getClass().toString(), "createMap()");
	        int[][] map = defaultMap;
	        
	        // заполняем карту клетками проходимыми
	        for (BaseModel object : ice) {
	        	int x = (int) object.getX();
	        	int z = (int) object.getZ();
	            map[x][z] = 1;
			}
	        
	        // заполняем карту клетками НЕпроходимыми
	        if(box == null) return map;
	        for (BaseModel object : box) {
	        	int x = (int) object.getX();
	        	int z = (int) object.getZ();
	            map[x][z] = 2;
			}
	        return map;
	}
	
	
	public Graph getGraph() {
		return new Graph(createMap(), (int) worldData.maxX, (int) worldData.maxZ);
	}
		
	public void update(float delta){
		tweenManager.update(delta);
	}
	
	public BaseModel getIceInPosition(int x, int z){
		for (BaseModel object : ice) {
			final Vector3 position = object.getPosistion();
			if(x == (int) position.x && z == (int) position.z) return object;	
		}
		return null;
	}
	
	public BaseModel getBoxInPosition(int x, int z){
		for (BaseModel object : box) {
			final Vector3 position = object.getPosistion();
			if(x == (int) position.x && z == (int) position.z) return object;	
		}
		return null;
	}
	
	public boolean cheakWin() {
		boolean flag = false;
		
		int i = 0;
		for (Box box : this.box) {
			if(box.fsm.getGlobalState() == BoxState.GLOABAL_STATE_WITH_MARK) i++;
		}
		
		if(i == box.size) flag = true;
		
		return flag;
	}

}
