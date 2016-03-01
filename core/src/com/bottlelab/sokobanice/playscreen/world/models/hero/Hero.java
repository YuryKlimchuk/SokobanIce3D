package com.bottlelab.sokobanice.playscreen.world.models.hero;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.Node;
import com.bottlelab.sokobanice.playscreen.controller.PathFind.PathFinder;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;
import com.bottlelab.sokobanice.playscreen.world.models.WorldUnitData;

public class Hero extends BaseModel {
	
	public StateMachine<Hero> fsm = new DefaultStateMachine<Hero>(this, HeroState.STAY);
	public DefaultGraphPath<Node> path = new DefaultGraphPath<Node>();
	PathFinder pathFinder = new PathFinder();
	public TweenManager tweenManager;
	public HeroTweenCallBack cb;
	public AnimationController animController;
	public HeroAnimationListener animListener;
	
	public Hero(ModelInstance _instance, String _name, WorldUnitData data, World _world, TweenManager _tweenManager) {
		super(_instance, _name, data);
		world = _world;
		tweenManager = _tweenManager;
		cb = new HeroTweenCallBack(this);
		animController = new AnimationController(instance);
		animListener = new HeroAnimationListener();
		
		/*
		for (int i = 0; i < _instance.animations.size; i++) {
			Gdx.app.log(this.getClass().toString(), _instance.animations.get(i).id);
		}
		*/
		
	}
	
	public boolean calculatePath(BaseModel model){
		
		//Gdx.app.log(this.getClass().toString(), "calculatePath()");
		
		int[][] map = world.createMap();
		
		path.nodes.addAll(
				pathFinder.findPath(
						map, 
						(int) world.worldData.maxX, 
						(int) world.worldData.maxZ, 
						world.hero.getPosistion(), 
						model.getPosistion()).nodes
		);
		
		
		
		if(path.nodes.size == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void draw(ModelBatch mBatch, Environment env) {
		fsm.update();
		animController.update(Gdx.graphics.getDeltaTime());
		super.draw(mBatch, env);
	}	

}
