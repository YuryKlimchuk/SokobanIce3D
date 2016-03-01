package com.bottlelab.sokobanice.playscreen.world;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.bottlelab.sokobanice.GameManager;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.WorldData;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;
import com.bottlelab.sokobanice.playscreen.world.models.box.Box;
import com.bottlelab.sokobanice.playscreen.world.models.hero.Hero;
import com.bottlelab.sokobanice.utils.Constants;


// Builder заполняющий поля класса World
public class WorldBuilder implements Disposable{
	
	// инфа для построение класса World считанная из json
	WorldData data;
	TweenManager tweenManager;
	Model sceneModel, scene2Model, heroModel;
	World world;
	public WorldBuilder(WorldData _data, World _world, TweenManager _tweenManager){
		data = _data;
		world = _world;
		tweenManager = _tweenManager;
		sceneModel = ((GameManager) Gdx.app.getApplicationListener()).resManager.getSceneModel();
		scene2Model = ((GameManager) Gdx.app.getApplicationListener()).resManager.getScene2Model();
		heroModel = ((GameManager) Gdx.app.getApplicationListener()).resManager.getHeroModel();
	}
	
	public Array<BaseModel> buildIce(){
		ModelInstance ice = new ModelInstance(sceneModel, Constants.ICE);
		Array<BaseModel> models = new Array<BaseModel>();
		for (WorldUnitData unitData : data.ice) {
			BaseModel model = new BaseModel(ice, Constants.ICE, unitData);
			model.setAngleY(MathUtils.random(0, 10) * 90f);
			models.add(model);
		} 
		return models;
	}
	
	public Array<BaseModel> buildIceMark(){
		ModelInstance iceMark = new ModelInstance(sceneModel, Constants.ICE_MARK);
		Array<BaseModel> models = new Array<BaseModel>();
		for (WorldUnitData unitData : data.iceMark) {
			BaseModel model = new BaseModel(iceMark, Constants.ICE_MARK, unitData);
			model.setAngleY(MathUtils.random(0, 10) * 90f);
			models.add(model);
		}
		return models;
	}
	
	public Array<Box> buildBox(){
		ModelInstance box = new ModelInstance(sceneModel, Constants.BOX);
		Array<Box> models = new Array<Box>();
		for (WorldUnitData unitData : data.box) {
			Box model = new Box(box, Constants.BOX, unitData, data.iceMark, world.hero, world);
			models.add(model);
		}
		return models;
	}
	
	public BaseModel buildIceberg(){
		ModelInstance iceberg = new ModelInstance(scene2Model, Constants.ICEBERG);
		return new BaseModel(iceberg, Constants.ICEBERG, new WorldUnitData(world.worldData.maxX/2, 0, world.worldData.maxX/2, 0));
	}
	
	public BaseModel buildOcean(){
		ModelInstance ocean = new ModelInstance(sceneModel, Constants.OCEAN);
		return new BaseModel(ocean, Constants.OCEAN, new WorldUnitData(world.worldData.maxX/2, 0, world.worldData.maxX/2, 0));
	}
	
	public BaseModel buildGround(){
		ModelInstance ground = new ModelInstance(sceneModel, Constants.GROUND);
		return new BaseModel(ground, Constants.GROUND, new WorldUnitData(world.worldData.maxX/2, 0, world.worldData.maxX/2, 0));
	}
	
	public Hero buildHero(){
		ModelInstance hero = new ModelInstance(heroModel);
		return new Hero(hero, Constants.HERO, data.hero, world, tweenManager);
	}
	
	
	
	@Override
	public void dispose() {}

}
