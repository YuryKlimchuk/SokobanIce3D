package com.bottlelab.sokobanice.playscreen.world.models.box;

import com.bottlelab.sokobanice.playscreen.world.World;
import com.bottlelab.sokobanice.playscreen.world.models.hero.HeroState;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class BoxTwenCallBack implements TweenCallback {
	
	World world;
	
	public BoxTwenCallBack(World _world) {
		world = _world;
	}

	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		world.hero.tweenManager.killAll();
		if(world.hero.fsm.getCurrentState() == HeroState.MOVING_WITH_BOX) 
			world.hero.fsm.changeState(HeroState.STAY);
		
		for (Box box : world.box) 
			if(box.fsm.getCurrentState() == BoxState.MOVE)
				box.fsm.changeState(BoxState.STAY);
	}

}
