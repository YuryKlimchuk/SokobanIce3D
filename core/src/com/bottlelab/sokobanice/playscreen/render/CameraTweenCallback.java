package com.bottlelab.sokobanice.playscreen.render;

import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.playscreen.controller.ControllerManager;
import com.bottlelab.sokobanice.playscreen.controller.ControllerManagerState;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class CameraTweenCallback implements TweenCallback {

	ControllerManager contManager;
	
	
	public CameraTweenCallback(ControllerManager _contManager) {
		Gdx.app.log(this.getClass().toString(), "CameraTweenCallback()");
		contManager = _contManager;
	}
	
	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		Gdx.app.log(this.getClass().toString(), "onEvent(), TweenType = " + arg0);
		if(arg0 == TweenCallback.COMPLETE) {
			contManager.fsm.changeState(ControllerManagerState.ALL_ENABLE);
		}
	}

}
