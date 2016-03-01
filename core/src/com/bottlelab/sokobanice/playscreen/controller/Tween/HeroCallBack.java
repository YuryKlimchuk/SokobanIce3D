package com.bottlelab.sokobanice.playscreen.controller.Tween;

import com.badlogic.gdx.Gdx;
import com.bottlelab.sokobanice.playscreen.world.models.hero.Hero;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

public class HeroCallBack implements TweenCallback {
	
	Hero hero;
	
	public HeroCallBack(Hero _hero) {
		hero = _hero;
	}

	@Override
	public void onEvent(int arg0, BaseTween<?> arg1) {
		Gdx.app.log(this.getClass().toString(), "onEvent() : getAngleY : " + hero.getAngleY());
		//hero.setMoveFlag(false);
	}

}
