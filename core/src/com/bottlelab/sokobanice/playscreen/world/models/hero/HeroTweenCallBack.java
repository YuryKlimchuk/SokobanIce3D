package com.bottlelab.sokobanice.playscreen.world.models.hero;

import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.playscreen.world.models.BaseModel;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class HeroTweenCallBack implements TweenCallback{
	
	private Hero hero;
	public boolean stopAnim = false;
	public BaseModel model = null;
	
	public HeroTweenCallBack(Hero _hero) {
		hero = _hero;
	}
	
	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		//Gdx.app.log(null, "cb1.onEvent(), arg0 = " + arg0);
		//Gdx.app.log(null, "stopAnim = " + stopAnim);
		switch (arg0) {
		case TweenCallback.COMPLETE:
			//Gdx.app.log(null, "TweenCallback.COMPLETE");
			hero.fsm.changeState(HeroState.STAY);
			break;
		case TweenCallback.END:
			//Gdx.app.log(null, "TweenCallback.END");
			if(stopAnim) {
				
				
				if(model != null && hero.calculatePath(model)) {
					hero.tweenManager.killAll();
					stopAnim = false;
					model = null;
					hero.fsm.changeState(HeroState.MOVING);
				}
				
			}
			break;
		}
		
		
	}

}
