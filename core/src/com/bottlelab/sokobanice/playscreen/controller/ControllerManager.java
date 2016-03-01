package com.bottlelab.sokobanice.playscreen.controller;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.bottlelab.sokobanice.playscreen.controller.gui.StageGUI;
import com.bottlelab.sokobanice.playscreen.render.CameraTweenCallback;
import com.bottlelab.sokobanice.playscreen.render.Render;
import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.utils.Constants;

public class ControllerManager {
	
	InputMultiplexer inputs;
	CameraInputController camController;
	//CameraController camController;
	ClickController clickController;
	StageGUI stage;
	boolean isWin = false;
	World world;
	
	public DefaultStateMachine<ControllerManager> fsm = new DefaultStateMachine<ControllerManager>(this, ControllerManagerState.ALL_DISABLE);

	
	
	public ControllerManager(World _world, Render _render) {
		world = _world;
		inputs = new InputMultiplexer();
		camController = new CameraInputController(_render.customCamera.camera);
		
		clickController = new ClickController(_world, _render);
		//camController = new CameraController(_render.customCamera.camera);
		
		stage = new StageGUI(_world, _render, this);
		
		/*
		inputs.addProcessor(stage);
		inputs.addProcessor(clickController);
		inputs.addProcessor(camController);
		Gdx.input.setInputProcessor(inputs);
		*/
		
		Gdx.input.setInputProcessor(null);
		
		CameraTweenCallback camCB = new CameraTweenCallback(this);
		Tween
			.to(_render.customCamera, Constants.POS_XYZ, 2f)
			.target(10, 20, -10)
			.ease(TweenEquations.easeNone)
			.setCallback(camCB)
			.setCallbackTriggers(TweenCallback.COMPLETE)
			.start(world.tweenManager);
		
		
		
	}
	
	public void update(float delta){
		//camController.update();
		stage.draw();
		stage.act(delta);
		stage.update(delta);
		
		if(world.cheakWin() && !isWin) {
			stage.showWingroup();
			isWin = true;
		}
			
	}

}
